package org.one.afternoon.controller;
import org.one.afternoon.common.Result;
import org.one.afternoon.domain.Activity;
import org.one.afternoon.mapper.ActivityMapper;
import org.one.afternoon.service.ActivityService;
import org.one.afternoon.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.one.afternoon.common.Result.success;


@RestController
@RequestMapping(value = "/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private RedisService redisService;
    @Autowired
    ActivityMapper activityMapper;


    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public Result<List<Activity>> findAllActivity() {
//        List<Activity> activities=(List<Activity>) redisService.getValue("activities");
        List<Activity> activities = activityService.findAll();
//        if (activities==null) {
//            activities = activityService.findAll();
//            redisService.setValue("activities",activities);
//        }
        return Result.success(activities);
    }
    @CrossOrigin
    @PostMapping("/deleteOne")
    @ResponseBody
    public Result<String> delete(@RequestParam Integer index) {
        activityService.deleteById(index);
        return Result.success("删除成功");
    }
    @CrossOrigin
    @DeleteMapping("/deleteBatch")
    public Result<String> deleteBatch(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            activityService.deleteById(id);
        }
        return Result.success("批量删除成功");
    }
    @CrossOrigin // 跨域访问
    @PostMapping("/findOne")
    @ResponseBody
    public Result<Activity> findById(@RequestParam int index) {
        Activity all = activityService.findById(index);
        return success(all);
    }
    @CrossOrigin // 跨域访问
    @PostMapping(value = "/queryOne")
    @ResponseBody
    public Result<List<Activity>> queryOne(@RequestBody Activity activity) {
        List<Activity> all = activityService.queryOne(activity);
        return success(all);
    }
    @CrossOrigin
    @PostMapping("/insertOne")
    @ResponseBody
    public Result<String> insert(@RequestBody Activity activity){
        activity.setId(activityService.getLastActivityId()+1);
        boolean save = activityService.insert(activity);
        redisService.setValue("activities",null);
        if (save) {
            return Result.success("成功添加用户");
        }
        return Result.error("添加用户失败");
    }
    @CrossOrigin
    @PostMapping("/updateOne")
    @ResponseBody
    public Result<String> update(@RequestBody Activity activity) {
        boolean update = activityService.update(activity);
        if (update) {
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }
}
