package org.one.afternoon.controller;


import org.one.afternoon.common.Result;
import org.one.afternoon.domain.Teacher;
import org.one.afternoon.mapper.TeacherMapper;
import org.one.afternoon.mapper.StudentMapper;
import org.one.afternoon.mapper.UserMapper;
import org.one.afternoon.service.TeacherService;
import org.one.afternoon.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 响应用户
 */
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private RedisService redisService;

    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public Result<List<Teacher>> findAllTeacher() {
//        List<Teacher> teachers=(List<Teacher>) redisService.getValue("teachers");
        List<Teacher> teachers = teacherService.findAll();
//        if (teachers==null) {
//            teachers = teacherService.findAll();
//            redisService.setValue("teachers",teachers);
//        }
        return Result.success(teachers);
    }


    //复合查询
    @CrossOrigin
    @PostMapping("/queryOne")
    @ResponseBody
    public Result<List<Teacher>> queryTeacher(@RequestBody Teacher teacher) {
        return Result.success(teacherService.queryOne(teacher));
    }
    @CrossOrigin
    @PostMapping("/findOne")
    @ResponseBody
    public Result<Teacher> findTeacherById(@RequestParam int index) {
        return Result.success(teacherService.findTeacherById(index));
    }

    @CrossOrigin
    @PostMapping("/insertOne")
    @ResponseBody
    public Result<String> insert(@RequestBody Teacher teacher) {
        teacher.setId(teacherService.getLastId()+1);
        boolean save = teacherService.insert(teacher);
        redisService.setValue("teachers",null);
        if (save) {
            return Result.success("成功添加");
        }
        return Result.error("添加失败");
    }

    @CrossOrigin
    @PostMapping("/deleteOne")
    @ResponseBody
    public Result<String> delete(@RequestParam Integer index) {
        teacherService.deleteById(index);
        return Result.success("删除成功");
    }
    @CrossOrigin
    @DeleteMapping("/deleteBatch")
    public Result<String> deleteBatch(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            teacherService.deleteById(id);
        }
        return Result.success("批量删除成功");
    }

    @CrossOrigin
    @PostMapping("/updateOne")
    @ResponseBody
    public Result<String> update(@RequestBody Teacher teacher) {
        boolean update = teacherService.update(teacher);
        if (update) {
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }
}
