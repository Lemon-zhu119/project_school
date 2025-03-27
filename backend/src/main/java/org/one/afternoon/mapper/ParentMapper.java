package org.one.afternoon.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.one.afternoon.domain.Parent;
import org.one.afternoon.domain.User;

import java.util.List;

@Mapper
public interface ParentMapper {

    List<Parent> findAll();

    List<Parent> queryOne(Parent parent);

    boolean insert(Parent parent);

    boolean deleteById(int id);

    boolean update(Parent parent);

    //查找是否存在用户
    @Select("select * from parent where username=#{username}")
    Parent findParentByUsername(String username);

    //修改登录态
    @Update("update parent set online=#{status} where username=#{username}")
    void updateOnline(int status,String username);

    //查找今日新增
    @Select("select COUNT(*) from parent where DAY(create_at)=#{time}")
    int findTdy(int time);

    //查找今日修改
    @Select("select COUNT(*) from parent where DAY(update_at)=#{time}")
    int findNew(int time);

    Integer getLastId();
}
