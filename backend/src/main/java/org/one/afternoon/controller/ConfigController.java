package org.one.afternoon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.one.afternoon.common.Result;
import org.one.afternoon.domain.Parent;
import org.one.afternoon.domain.Config;
import org.one.afternoon.mapper.ConfigMapper;
import org.one.afternoon.service.ParentService;
import org.one.afternoon.service.RedisService;
import org.one.afternoon.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;


/**
 * 响应用户
 */
@RestController
@RequestMapping(value = "/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private ConfigMapper configMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public Result<List<Config>> findAllConfig() {
        List<Config> configs=(List<Config>) redisService.getValue("configs");
        if (configs==null) {
            configs = configService.findAll();
            redisService.setValue("configs",configs);
        }
        return Result.success(configs);
    }

    @CrossOrigin
    @GetMapping("/queryOne")
    @ResponseBody
    public Result<Object> queryOne(@RequestParam("userId") Integer userId) {
        Object config = redisService.hashGet("config", userId);

        if (config == null) {
            // Redis没有
            Config entity = configService.findByUserId(userId);

            if (entity == null) {
                return Result.error("不存在");
            }

            // 放入Redis
            redisService.hashPut("config",
                    userId,
                    entity.getConfig());

            return Result.success(entity.getConfig());
        }

        return Result.success(config);
    }

    @CrossOrigin
    @PostMapping("/insert")
    @ResponseBody
    public Result<String> insert(@RequestBody Map<String,Object> body) throws Exception {
        Config config=new Config();

        config.setUserId((Integer)body.get("userId"));

        config.setConfig(
                objectMapper.writeValueAsString(body.get("config"))
        );
        int save = configService.insert(config);
        // 清空redis
        redisService.delete("configs");
        redisService.delete("config:" + config.getUserId());
        if (save != 0) {
            return Result.success("成功添加用户");
        }
        return Result.error("添加用户失败");
    }

    @CrossOrigin
    @PostMapping("/batchInsert")
    @ResponseBody
    public Result<String> batchInsert(@RequestBody List<Config> configs) {
        System.out.println(configs);
        boolean save = configService.batchInsert(configs);

        // 清空redis
        redisService.delete("configs");

        if (save) {
            return Result.success("成功导入 " + configs.size() + " 条数据");
        }

        return Result.error("批量导入失败");
    }

    @CrossOrigin
    @PostMapping("/deleteOne")
    @ResponseBody
    public Result<String> deleteOne(@RequestParam Integer index) {
        configService.deleteById(index);
        parentService.deleteById(index);
        redisService.setValue("configs",null);
        return Result.success("删除成功");
    }

    @CrossOrigin
    @DeleteMapping("/deleteBatch")
    public Result<String> deleteBatch(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            configService.deleteById(id);
        }
        return Result.success("批量删除成功");
    }
    @CrossOrigin
    @PostMapping("/update")
    @ResponseBody
    public Result<String> updateOne(@RequestBody Parent parent) {
        boolean update = parentService.update(parent);
        if (update) {
            redisService.setValue("configs",null);
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }

    @PostMapping("/save")
    public Result<String> save(
            @RequestBody Map<String,Object> body
    ) throws Exception{


        Config config = new Config();


        config.setUserId(
                (Integer) body.get("userId")
        );


        config.setConfig(
                objectMapper.writeValueAsString(
                        body.get("config")
                )
        );


        boolean result =
                configService.saveConfig(config);



        if(result){
            // 放入Redis
            redisService.hashPut("config",
                    config.getUserId(),
                    config.getConfig());
            return Result.success("配置同步成功");

        }


        return Result.error("配置同步失败");

    }
}
