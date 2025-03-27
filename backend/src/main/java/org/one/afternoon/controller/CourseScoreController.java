package org.one.afternoon.controller;


import org.one.afternoon.common.Result;
import org.one.afternoon.domain.CourseScore;
import org.one.afternoon.mapper.CourseScoreMapper;
import org.one.afternoon.mapper.StudentMapper;
import org.one.afternoon.mapper.UserMapper;
import org.one.afternoon.service.CourseScoreService;
import org.one.afternoon.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping(value = "/courseScore")
public class CourseScoreController {

    @Autowired
    private CourseScoreService courseScoreService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private CourseScoreMapper courseScoreMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserMapper userMapper;

    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public Result<List<CourseScore>> findAllCourseScore() {
//        List<CourseScore> courseScores=(List<CourseScore>) redisService.getValue("courseScores");
        List<CourseScore> courseScores = courseScoreService.findAll();
//        if (courseScores==null) {
//            courseScores = courseScoreService.findAll();
//            redisService.setValue("courseScores",courseScores);
//        }
        return Result.success(courseScores);
    }


    //复合查询
    @CrossOrigin
    @PostMapping("/queryOne")
    @ResponseBody
    public Result<List<CourseScore>> queryCourseScore(@RequestBody CourseScore courseScore) {
        return Result.success(courseScoreService.queryOne(courseScore));
    }
    @CrossOrigin
    @PostMapping("/findOne")
    @ResponseBody
    public Result<List<CourseScore>> findCourseScoreById(@RequestParam int index) {
        return Result.success(courseScoreService.findCourseScoreById(index));
    }

    @CrossOrigin
    @PostMapping("/insertOne")
    @ResponseBody
    public Result<String> insert(@RequestBody CourseScore courseScore) {
        courseScore.setId(courseScoreService.getLastId()+1);
        boolean save = courseScoreService.insert(courseScore);
        redisService.setValue("courseScores",null);
        if (save) {
            return Result.success("成功添加");
        }
        return Result.error("添加失败");
    }

    @CrossOrigin
    @PostMapping("/deleteOne")
    @ResponseBody
    public Result<String> delete(@RequestParam Integer index) {
        courseScoreService.deleteById(index);
        return Result.success("删除成功");
    }
    @CrossOrigin
    @DeleteMapping("/deleteBatch")
    public Result<String> deleteBatch(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            courseScoreService.deleteById(id);
        }
        return Result.success("批量删除成功");
    }

    @CrossOrigin
    @PostMapping("/updateOne")
    @ResponseBody
    public Result<String> update(@RequestBody CourseScore courseScore) {
        boolean update = courseScoreService.update(courseScore);
        if (update) {
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }
}
