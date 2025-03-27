package org.one.afternoon.service.impl;

import org.one.afternoon.domain.Admin;
import org.one.afternoon.mapper.AdminMapper;
import org.one.afternoon.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> findAll() {
        return adminMapper.findAll();
    }

    @Override
    public List<Admin> queryOne(Admin admin) {
        return adminMapper.queryOne(admin);
    }

    @Override
    public boolean insert(Admin admin) {
        return adminMapper.insert(admin);
    }

    @Override
    public boolean deleteById(int id) {
        return adminMapper.deleteById(id);
    }

    @Override
    public boolean update(Admin admin) {
        return adminMapper.update(admin);
    }
    @Override
    public Admin findByUsername(String username) {
        return adminMapper.findAdminByUsername(username);
    }

    @Override
    public boolean login(Admin admin) throws NoSuchAlgorithmException {
        MessageDigest md5 = null;
        // 生成普通的MD5密码
        md5 = MessageDigest.getInstance("MD5");
        char[] charArray = admin.getPassword().toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        admin.setPassword(hexValue.toString());
        Admin getAdmin = adminMapper.findAdminByUsername(admin.getUsername());
        if (getAdmin == null) {
            //用户不存在！
            return false;
        }
        if (!getAdmin.getPassword().equals(admin.getPassword())) {
            //用户名或者密码错误！
            return false;
        }
        // 设定登录成功消息
        adminMapper.updateOnline(1, admin.getUsername());

        return true;
    }

    @Override
    public boolean logout(String username) {
        adminMapper.updateOnline(0, username);
        return true;
    }

    @Override
    public Integer queryTdy(Integer time) {
        return adminMapper.findTdy(time);
    }

    @Override
    public Integer queryNew(Integer time) {
        return adminMapper.findNew(time);
    }
}
