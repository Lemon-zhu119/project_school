package org.one.afternoon.mapper;

import org.apache.ibatis.annotations.*;
import org.one.afternoon.domain.User;

import java.util.List;

@Mapper
public interface UserMapper{

    List<User> findAll();

    List<User> queryOne(User user);

    boolean insert(User user);

    boolean deleteById(int id);

    boolean update(User user);

    //查找是否存在用户
    @Select("select * from user where username=#{username}")
    User findUserByUsername(String username);
    Integer getLastUserId();

    //修改登录态
    @Update("update user set online=#{status} where username=#{username}")
    void updateOnline(int status,String username);

    //查找今日新增
    @Select("select COUNT(*) from user where DAY(create_at)=#{time}")
    int findTdy(int time);

    //查找今日修改
    @Select("select COUNT(*) from user where DAY(update_at)=#{time}")
    int findNew(int time);
}
