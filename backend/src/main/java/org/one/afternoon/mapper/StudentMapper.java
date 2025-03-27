package org.one.afternoon.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.one.afternoon.domain.Student;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> findAll();

    List<Student> queryOne(Student student);

    boolean insert(Student student);

    boolean deleteById(int id);

    boolean update(Student student);

    //查找是否存在用户
    @Select("select * from student where id=#{id}")
    Student findStudentById(String name);
    @Select("select * from student and name LIKE CONCAT('%',#{name},'%')")
    List<Student> findStudentByName(String name);
    //修改登录态
    @Update("update student set online=#{status} where username=#{username}")
    void updateOnline(int status,String username);

    //查找今日新增
    @Select("select COUNT(*) from student where DAY(create_at)=#{time}")
    int findTdy(int time);

    //查找今日修改
    @Select("select COUNT(*) from student where DAY(update_at)=#{time}")
    int findNew(int time);

    Integer getLastId();
}
