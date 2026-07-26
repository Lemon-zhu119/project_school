package org.one.afternoon.controller;


import org.one.afternoon.common.PasswordUtil;
import org.one.afternoon.common.Result;
import org.one.afternoon.domain.ChangePasswordRequest;
import org.one.afternoon.domain.Parent;
import org.one.afternoon.domain.Wear;
import org.one.afternoon.mapper.WearMapper;
import org.one.afternoon.service.ParentService;
import org.one.afternoon.service.RedisService;
import org.one.afternoon.service.WearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;


/**
 * 响应用户
 */
@RestController
@RequestMapping(value = "/wear")
public class WearController {

    @Autowired
    private WearService wearService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private WearMapper wearMapper;

    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public Result<List<Wear>> findAllWear() {
        List<Wear> wears=(List<Wear>) redisService.getValue("wears");
        if (wears==null) {
            wears = wearService.findAll();
            redisService.setValue("wears",wears);
        }
        return Result.success(wears);
    }

    @CrossOrigin
    @PostMapping("/insert")
    @ResponseBody
    public Result<String> insert(@RequestBody Wear wear) throws NoSuchAlgorithmException {
        int save = wearService.insert(wear);
        redisService.setValue("wears",null);
        if (save != 0) {
            return Result.success("成功添加磨损对象");
        }
        return Result.error("添加用户失败");
    }

    @CrossOrigin
    @PostMapping("/batchInsert")
    @ResponseBody
    public Result<String> batchInsert(@RequestBody List<Wear> wears) {
        System.out.println(wears);
        boolean save = wearService.batchInsert(wears);

        redisService.setValue("wears", null);

        if (save) {
            return Result.success("成功导入 " + wears.size() + " 条数据");
        }

        return Result.error("批量导入失败");
    }

    @CrossOrigin
    @PostMapping("/deleteOne")
    @ResponseBody
    public Result<String> deleteOne(@RequestParam Integer index) {
        wearService.deleteById(index);
        parentService.deleteById(index);
        redisService.setValue("wears",null);
        return Result.success("删除成功");
    }

    @CrossOrigin
    @DeleteMapping("/deleteBatch")
    public Result<String> deleteBatch(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            wearService.deleteById(id);
        }
        return Result.success("批量删除成功");
    }
    @CrossOrigin
    @PostMapping("/update")
    @ResponseBody
    public Result<String> updateOne(@RequestBody Parent parent) {
        boolean update = parentService.update(parent);
        if (update) {
            redisService.setValue("wears",null);
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }
}
