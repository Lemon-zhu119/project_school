package org.one.afternoon.controller;


import org.one.afternoon.common.PasswordUtil;
import org.one.afternoon.common.Result;
import org.one.afternoon.domain.ChangePasswordRequest;
import org.one.afternoon.domain.Parent;
import org.one.afternoon.domain.Student;
import org.one.afternoon.domain.User;
import org.one.afternoon.mapper.ParentMapper;
import org.one.afternoon.mapper.StudentMapper;
import org.one.afternoon.mapper.UserMapper;
import org.one.afternoon.service.ParentService;
import org.one.afternoon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.one.afternoon.service.RedisService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


/**
 * 响应用户
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private UserMapper userMapper;

    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public Result<List<User>> findAllUser() {
//        List<User> users=(List<User>) redisService.getValue("users");
//        if (users==null) {
            List<User> users = userService.findAll();
//            redisService.setValue("users",users);
//        }
        return Result.success(users);
    }


    //复合查询
    @CrossOrigin
    @PostMapping("/queryOne")
    @ResponseBody
    public Result<List<User>> queryUser(@RequestBody User user) {
        return Result.success(userService.queryOne(user));
    }
    @CrossOrigin
    @PostMapping("/findOne")
    @ResponseBody
    public Result<User> findOne(@RequestBody User user) {
        return Result.success(userMapper.findUserByUsername(user.getUsername()));
    }
    @CrossOrigin
    @PostMapping("/changePassword")
    @ResponseBody
    public Result<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws NoSuchAlgorithmException {
        User user=userMapper.findUserByUsername(changePasswordRequest.getUsername());
        changePasswordRequest.setPassword(PasswordUtil.md5Encrypt(changePasswordRequest.getPassword()));
        changePasswordRequest.setNewPassword(PasswordUtil.md5Encrypt(changePasswordRequest.getNewPassword()));
        if(user.getPassword().equals(changePasswordRequest.getPassword())){
            if (user.getPassword().equals(changePasswordRequest.getNewPassword())){
                return Result.error("新密码与旧密码不能相同");
            }else{
                user.setPassword(changePasswordRequest.getNewPassword());
                boolean save = userService.update(user);
                if (save) {
                    redisService.setValue("users",null);
                    return Result.success("成功修改密码");
                }else{
                    return Result.error("修改失败");
                }
            }
        }else {
            return Result.error("密码错误,请重新输入");
        }
    }
    @CrossOrigin
    @PostMapping("/insert")
    @ResponseBody
    public Result<String> insert(@RequestBody User user) throws NoSuchAlgorithmException {
        user.setId(userService.getLastId()+1);
        user.setPassword(PasswordUtil.md5Encrypt(user.getPassword()));
        boolean save = userService.insert(user);
        redisService.setValue("users",null);
        if (save) {
            return Result.success("成功添加用户");
        }
        return Result.error("添加用户失败");
    }

    @CrossOrigin
    @PostMapping("/deleteOne")
    @ResponseBody
    public Result<String> deleteOne(@RequestParam Integer index) {
        userService.deleteById(index);
        parentService.deleteById(index);
        redisService.setValue("users",null);
        return Result.success("删除成功");
    }

    @CrossOrigin
    @DeleteMapping("/deleteBatch")
    public Result<String> deleteBatch(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            userService.deleteById(id);
        }
        return Result.success("批量删除成功");
    }
    @CrossOrigin
    @PostMapping("/update")
    @ResponseBody
    public Result<String> updateOne(@RequestBody Parent parent) {
        boolean update = parentService.update(parent);
        if (update) {
            redisService.setValue("users",null);
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }


    @CrossOrigin
    @PostMapping("/login")
    @ResponseBody
    public Result<Object> login(@RequestBody User user) throws NoSuchAlgorithmException {
        boolean login=userService.login(user);
        if (login) {
            return Result.success(parentService.findByUsername(user.getUsername()));
        } else {
            return Result.error("登录失败");
        }
    }

    @CrossOrigin
    @PostMapping("/logout")
    @ResponseBody
    public Result<String> logout(@RequestParam String username) {
        boolean logout = userService.logout(username);
        if (logout) {
            return Result.success("登出成功");
        } else {
            return Result.error("登出失败");
        }
    }
}
