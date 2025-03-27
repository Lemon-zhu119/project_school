package org.one.afternoon.controller;

import org.one.afternoon.common.PasswordUtil;
import org.one.afternoon.common.Result;
import org.one.afternoon.domain.Course;
import org.one.afternoon.domain.LeaveRequest;
import org.one.afternoon.domain.LeaveRequestRecord;
import org.one.afternoon.domain.Teacher;
import org.one.afternoon.mapper.LeaveRequestMapper;
import org.one.afternoon.service.LeaveRequestService;
import org.one.afternoon.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.one.afternoon.common.Result.success;


@RestController
@RequestMapping(value = "/leaveRequest")
public class LeaveRequestController {
    @Autowired
    private LeaveRequestService leaveRequestService;
    @Autowired
    private RedisService redisService;
    @Autowired
    LeaveRequestMapper leaveRequestMapper;
    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public Result<List<LeaveRequest>> findAllLeaveRequest() {
//        List<Course> courses=(List<Course>) redisService.getValue("courses");
        List<LeaveRequest> leaveRequests = leaveRequestService.findAll();
//        if (courses==null) {
//            courses = courseService.findAll();
//            redisService.setValue("courses",courses);
//        }
        return Result.success(leaveRequests);
    }

    @CrossOrigin // 跨域访问
    @PostMapping("/findById")
    @ResponseBody
    public Result<List<LeaveRequest>> findById(@RequestParam int index) {
        List<LeaveRequest> all = leaveRequestService.findById(index);
        return success(all);
    }
    @CrossOrigin // 跨域访问
    @PostMapping(value = "/queryOne")
    @ResponseBody
    public Result<List<LeaveRequest>> query(@RequestBody LeaveRequest leaveRequest) {
        List<LeaveRequest> all = leaveRequestService.query(leaveRequest);
        return success(all);
    }
    @CrossOrigin
    @PostMapping("/insertOne")
    @ResponseBody
    public Result<String> insert(@RequestBody LeaveRequestRecord leaveRequestRecord) throws NoSuchAlgorithmException {
        leaveRequestRecord.setId(leaveRequestMapper.getLastLeaveRequestId()+1);
        boolean save = leaveRequestService.insert(leaveRequestRecord);
        redisService.setValue("leaveRequests",null);
        if (save) {
            return Result.success("成功添加用户");
        }
        return Result.error("添加用户失败");
    }
    @CrossOrigin
    @PostMapping("/updateOne")
    @ResponseBody
    public Result<String> update(@RequestBody LeaveRequestRecord leaveRequestRecord) throws NoSuchAlgorithmException {
        boolean save = leaveRequestService.update(leaveRequestRecord);
        redisService.setValue("leaveRequests",null);
        if (save) {
            return Result.success("成功更新状态");
        }
        return Result.error("更新状态失败");
    }
    @CrossOrigin
    @PostMapping("/deleteOne")
    @ResponseBody
    public Result<String> deleteOne(@RequestParam Integer index) {
        boolean save=leaveRequestService.deleteById(index);
        if(save){
            return Result.success("删除成功");}
        else {
            return Result.error("删除失败");
        }
    }
    @CrossOrigin
    @DeleteMapping("/deleteBatch")
    public Result<String> deleteBatch(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            leaveRequestService.deleteById(id);
        }
        return Result.success("批量删除成功");
    }
}
