package org.one.afternoon.controller;

import org.one.afternoon.common.Result;
import org.one.afternoon.domain.Student;
import org.one.afternoon.mapper.StudentMapper;
import org.one.afternoon.mapper.UserMapper;
import org.one.afternoon.service.StudentService;
import org.one.afternoon.service.RedisService;
import org.one.afternoon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 响应用户
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UserService userService;
    @CrossOrigin
    @GetMapping(value = "/all")
    @ResponseBody
    public Result<List<Student>> findAllStudent() {
//        List<Student> students=(List<Student>) redisService.getValue("students");
        List<Student> students=null;
        if (students==null) {
            students = studentService.findAll();
            System.out.print(students);
            redisService.setValue("students",students);
        }
        return Result.success(students);
    }


    //复合查询
    @CrossOrigin
    @PostMapping("/queryOne")
    @ResponseBody
    public Result<List<Student>> queryStudent(@RequestBody Student student) {
        return Result.success(studentService.queryOne(student));
    }

    @CrossOrigin
    @PostMapping("/findOne")
    @ResponseBody
    public Result<Student> findOne(@RequestParam String index) {
        return Result.success(studentMapper.findStudentById(index));
    }
    @CrossOrigin
    @PostMapping("/insertOne")
    @ResponseBody
    public Result<String> insertOne(@RequestBody Student student) {
        student.setId(studentService.getLastId()+1);
        boolean save= studentService.insert(student);
        redisService.setValue("students",null);
        if (save) {
            return Result.success("成功添加用户");
        }
        return Result.error("添加用户失败");
    }

    @CrossOrigin
    @PostMapping("/deleteOne")
    @ResponseBody
    public Result<String> deleteOne(@RequestParam Integer index) {
        studentService.deleteById(index);
        return Result.success("删除成功");
    }
    @CrossOrigin
    @DeleteMapping("/deleteBatch")
    public Result<String> deleteBatch(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            studentService.deleteById(id);
        }
        return Result.success("批量删除成功");
    }


    @CrossOrigin
    @PostMapping("/updateOne")
    @ResponseBody
    public Result<String> update(@RequestBody Student student) {
        boolean update = studentService.update(student);
        if (update) {
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }
}
