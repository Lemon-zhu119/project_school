package org.one.afternoon.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.one.afternoon.domain.Course;

import java.util.List;

@Mapper
public interface CourseMapper {

    List<Course> findAll();

    List<Course> queryOne(Course course);

    boolean insert(Course course);

    boolean deleteById(int id);

    boolean update(Course course);

    //查找是否存在用户
    @Select("select * from course where username=#{username}")
    Course findCourseByUsername(String username);

    //修改登录态
    @Update("update course set online=#{status} where username=#{username}")
    void updateOnline(int status,String username);

    //查找今日新增
    @Select("select COUNT(*) from course where DAY(create_at)=#{time}")
    int findTdy(int time);

    //查找今日修改
    @Select("select COUNT(*) from course where DAY(update_at)=#{time}")
    int findNew(int time);

    Course findCourseById(int id);
}
