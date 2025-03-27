package org.one.afternoon.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.one.afternoon.domain.Admin;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<Admin> findAll();

    List<Admin> queryOne(Admin admin);

    boolean insert(Admin admin);

    boolean deleteById(int id);

    boolean update(Admin admin);

    //查找是否存在用户
    @Select("select * from admin where username=#{username}")
    Admin findAdminByUsername(String username);
    Integer getLastAdminId();

    //修改登录态
    @Update("update admin set online=#{status} where username=#{username}")
    void updateOnline(int status,String username);

    //查找今日新增
    @Select("select COUNT(*) from admin where DAY(create_at)=#{time}")
    int findTdy(int time);

    //查找今日修改
    @Select("select COUNT(*) from admin where DAY(update_at)=#{time}")
    int findNew(int time);
}
