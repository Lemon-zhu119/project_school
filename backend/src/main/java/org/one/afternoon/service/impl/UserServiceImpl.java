package org.one.afternoon.service.impl;

import org.one.afternoon.domain.User;
import org.one.afternoon.mapper.UserMapper;
import org.one.afternoon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Integer getLastId() {
        return userMapper.getLastUserId();
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<User> queryOne(User user) {
        return userMapper.queryOne(user);
    }

    @Override
    public boolean insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public boolean deleteById(int id) {
        return userMapper.deleteById(id);
    }

    @Override
    public boolean update(User user) {
        return userMapper.update(user);
    }

    @Override
    public boolean login(User user) throws NoSuchAlgorithmException {
        MessageDigest md5 = null;
        // 生成普通的MD5密码
        md5 = MessageDigest.getInstance("MD5");
        char[] charArray = user.getPassword().toCharArray();
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
        user.setPassword(hexValue.toString());
        User getUser = userMapper.findUserByUsername(user.getUsername());
        if (getUser == null) {
            //用户不存在！
            return false;
        }
        if (!getUser.getPassword().equals(user.getPassword())) {
            //用户名或者密码错误！
            return false;
        }
        // 设定登录成功消息
        userMapper.updateOnline(1, user.getUsername());

        return true;
    }

    @Override
    public boolean logout(String username) {
        userMapper.updateOnline(0, username);
        return true;
    }

    @Override
    public Integer queryTdy(Integer time) {
        return userMapper.findTdy(time);
    }

    @Override
    public Integer queryNew(Integer time) {
        return userMapper.findNew(time);
    }


}
