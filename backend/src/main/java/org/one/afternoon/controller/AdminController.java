package org.one.afternoon.controller;


import org.one.afternoon.common.PasswordUtil;
import org.one.afternoon.common.Result;
import org.one.afternoon.domain.ChangePasswordRequest;
import org.one.afternoon.domain.Parent;
import org.one.afternoon.domain.Student;
import org.one.afternoon.domain.Admin;
import org.one.afternoon.mapper.ParentMapper;
import org.one.afternoon.mapper.StudentMapper;
import org.one.afternoon.mapper.AdminMapper;
import org.one.afternoon.service.RedisService;
import org.one.afternoon.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;



@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private AdminMapper adminMapper;


    @CrossOrigin
    @GetMapping("/all")
    @ResponseBody
    public Result<List<Admin>> findAllAdmin() {
//        List<Admin> admins=(List<Admin>) redisService.getValue("admins");
//        if (admins==null) {
        List<Admin> admins = adminService.findAll();
//            redisService.setValue("admins",admins);
//        }
        return Result.success(admins);
    }


    //复合查询
    @CrossOrigin
    @PostMapping("/queryOne")
    @ResponseBody
    public Result<List<Admin>> queryAdmin(@RequestBody Admin admin) {
        return Result.success(adminService.queryOne(admin));
    }
    @CrossOrigin
    @PostMapping("/findOne")
    @ResponseBody
    public Result<Admin> findOne(@RequestBody Admin admin) {
        return Result.success(adminMapper.findAdminByUsername(admin.getUsername()));
    }
    @CrossOrigin
    @PostMapping("/changePassword")
    @ResponseBody
    public Result<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws NoSuchAlgorithmException {
        Admin admin=adminMapper.findAdminByUsername(changePasswordRequest.getUsername());
        changePasswordRequest.setPassword(PasswordUtil.md5Encrypt(changePasswordRequest.getPassword()));
        changePasswordRequest.setNewPassword(PasswordUtil.md5Encrypt(changePasswordRequest.getNewPassword()));
        if(admin.getPassword().equals(changePasswordRequest.getPassword())){
            if (admin.getPassword().equals(changePasswordRequest.getNewPassword())){
                return Result.error("新密码与旧密码不能相同");
            }else{
                admin.setPassword(changePasswordRequest.getNewPassword());
                boolean save = adminService.update(admin);
                if (save) {
                    redisService.setValue("admins",null);
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
    @PostMapping("/insertOne")
    @ResponseBody
    public Result<String> insert(@RequestBody Admin admin) throws NoSuchAlgorithmException {
        admin.setId(adminMapper.getLastAdminId()+1);
        admin.setPassword(PasswordUtil.md5Encrypt(admin.getPassword()));
        boolean save = adminService.insert(admin);
        redisService.setValue("admins",null);
        if (save) {
            return Result.success("成功添加用户");
        }
        return Result.error("添加用户失败");
    }

    @CrossOrigin
    @DeleteMapping("/deleteOne")
    @ResponseBody
    public Result<String> delete(@RequestParam Integer index) {
        adminService.deleteById(index);
        redisService.setValue("admins",null);
        return Result.success("删除成功");
    }


    @CrossOrigin
    @DeleteMapping("/deleteBatch")
    @ResponseBody
    public Result<String> deleteBatch(@RequestBody List<Integer> ids) {
        for(Integer id : ids){
            adminService.deleteById(id);
        }
        redisService.setValue("admins",null);
        return Result.success("删除成功");
    }


    @CrossOrigin
    @PostMapping("/updateOne")
    @ResponseBody
    public Result<String> update(@RequestBody Admin admin) {
        boolean update = adminService.update(admin);
        if (update) {
            redisService.setValue("admins",null);
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }


    @CrossOrigin
    @PostMapping("/login")
    @ResponseBody
    public Result<Object> login(@RequestBody Admin admin) throws NoSuchAlgorithmException {
        boolean login = adminService.login(admin);
        Admin getAdmin=adminService.findByUsername(admin.getUsername());
        if (login) {
            return Result.success(getAdmin);
        } else {
            return Result.error("登录失败");
        }
    }

    @CrossOrigin
    @PostMapping("/logout")
    @ResponseBody
    public Result<String> logout(@RequestParam String username) {
        boolean logout = adminService.logout(username);
        if (logout) {
            return Result.success("登出成功");
        } else {
            return Result.error("登出失败");
        }
    }
}
