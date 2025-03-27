package org.one.afternoon.controller;


import org.one.afternoon.common.Result;
import org.one.afternoon.domain.Course;
import org.one.afternoon.mapper.CourseMapper;
import org.one.afternoon.mapper.StudentMapper;
import org.one.afternoon.mapper.UserMapper;
import org.one.afternoon.service.CourseService;
import org.one.afternoon.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private RedisService redisService;
    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public Result<List<Course>> findAllCourse() {
//        List<Course> courses=(List<Course>) redisService.getValue("courses");
        List<Course> courses = courseService.findAll();
//        if (courses==null) {
//            courses = courseService.findAll();
//            redisService.setValue("courses",courses);
//        }
        return Result.success(courses);
    }


    //复合查询
    @CrossOrigin
    @PostMapping("/queryOne")
    @ResponseBody
    public Result<List<Course>> queryCourse(@RequestBody Course course) {
        return Result.success(courseService.queryOne(course));
    }
    @CrossOrigin
    @PostMapping("/findOne")
    @ResponseBody
    public Result<Course> findCourseById(@RequestParam int index) {
        return Result.success(courseService.findCourseById(index));
    }

    @CrossOrigin
    @PostMapping("/insertOne")
    @ResponseBody
    public Result<String> insert(@RequestBody Course course) {
        boolean save = courseService.insert(course);
        redisService.setValue("courses",null);
        if (save) {
            return Result.success("成功添加");
        }
        return Result.error("添加失败");
    }

    @CrossOrigin
    @PostMapping("/deleteOne")
    @ResponseBody
    public Result<String> delete(@RequestParam Integer index) {
        courseService.deleteById(index);
        return Result.success("删除成功");
    }
    @CrossOrigin
    @DeleteMapping("/deleteBatch")
    public Result<String> deleteBatch(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            courseService.deleteById(id);
        }
        return Result.success("批量删除成功");
    }

    @CrossOrigin
    @PostMapping("/updateOne")
    @ResponseBody
    public Result<String> update(@RequestBody Course course) {
        boolean update = courseService.update(course);
        if (update) {
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }
}
