package org.one.afternoon.controller;


import org.one.afternoon.common.Result;
import org.one.afternoon.domain.Parent;
import org.one.afternoon.domain.User;
import org.one.afternoon.mapper.ParentMapper;
import org.one.afternoon.mapper.StudentMapper;
import org.one.afternoon.mapper.UserMapper;
import org.one.afternoon.service.RedisService;
import org.one.afternoon.service.ParentService;
import org.one.afternoon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 响应用户
 */
@RestController
@RequestMapping(value = "/parent")
public class ParentController {

    @Autowired
    private ParentService parentService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public Result<List<Parent>> findAllParent() {
//        List<Parent> parents=(List<Parent>) redisService.getValue("parents");
//        if (parents==null) {
         List<Parent> parents = parentService.findAll();
//            redisService.setValue("parents",parents);
//        }
        return Result.success(parents);
    }


    //复合查询
    @CrossOrigin
    @PostMapping("/queryOne")
    @ResponseBody
    public Result<List<Parent>> queryParent(@RequestBody Parent parent) {
        return Result.success(parentService.queryOne(parent));
    }

    @CrossOrigin
    @PostMapping("/insertOne")
    @ResponseBody
    public Result<String> insert(@RequestBody Parent parent) {
        parent.setId(parentService.getLastId()+1);
        boolean save = parentService.insert(parent);
        redisService.setValue("parents",null);
        if (save) {
            return Result.success("成功添加用户");
        }
        return Result.error("添加用户失败");
    }

    @CrossOrigin
    @PostMapping("/deleteOne")
    @ResponseBody
    public Result<String> delete(@RequestParam Integer index) {
        parentService.deleteById(index);
        return Result.success("删除成功");
    }


    @CrossOrigin
    @PostMapping("/updateOne")
    @ResponseBody
    public Result<String> update(@RequestBody Parent parent) {
        boolean update = parentService.update(parent);
        if (update) {
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }
}
