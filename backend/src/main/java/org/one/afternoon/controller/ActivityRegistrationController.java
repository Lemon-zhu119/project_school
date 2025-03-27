package org.one.afternoon.controller;

import org.one.afternoon.common.Result;
import org.one.afternoon.domain.Activity;
import org.one.afternoon.domain.ActivityRegistration;
import org.one.afternoon.domain.RegistrationRecord;
import org.one.afternoon.service.ActivityRegistrationService;
import org.one.afternoon.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.one.afternoon.common.Result.success;


@RestController
@RequestMapping(value = "/activityRegistration")
public class ActivityRegistrationController {
    @Autowired
    private ActivityRegistrationService activityRegistrationService;
    @Autowired
    private RedisService redisService;


    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public Result<List<ActivityRegistration>> findAllActivity() {
//        List<Activity> activities=(List<Activity>) redisService.getValue("activities");
        List<ActivityRegistration> activityRegistrations =activityRegistrationService.findAll();
//        if (activities==null) {
//            activities = activityService.findAll();
//            redisService.setValue("activities",activities);
//        }
        return Result.success(activityRegistrations);
    }

    @CrossOrigin // 跨域访问
    @PostMapping(value = "/queryOne")
    @ResponseBody
    public Result<List<ActivityRegistration>> queryOne(@RequestBody ActivityRegistration activityRegistration) {
        List<ActivityRegistration> all = activityRegistrationService.queryOne(activityRegistration);
        return success(all);
    }
    @CrossOrigin
    @PostMapping("/insertOne")
    @ResponseBody
    public Result<String> insert(@RequestBody RegistrationRecord registrationRecord) throws NoSuchAlgorithmException {
        registrationRecord.setId(activityRegistrationService.getLastRegistrationId()+1);
        boolean save = activityRegistrationService.insert(registrationRecord);
        redisService.setValue("activities",null);
        if (save) {
            return Result.success("成功添加用户");
        }
        return Result.error("添加用户失败");
    }
    @CrossOrigin
    @PostMapping("/deleteOne")
    @ResponseBody
    public Result<String> delete(@RequestParam Integer index) {
        activityRegistrationService.deleteById(index);
        return Result.success("删除成功");
    }
    @CrossOrigin
    @DeleteMapping("/deleteBatch")
    public Result<String> deleteBatch(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            activityRegistrationService.deleteById(id);
        }
        return Result.success("批量删除成功");
    }
    @CrossOrigin
    @PostMapping("/updateOne")
    @ResponseBody
    public Result<String> update(@RequestBody ActivityRegistration activityRegistration) {
        boolean update = activityRegistrationService.update(activityRegistration);
        if (update) {
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }
}
