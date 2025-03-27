package org.one.afternoon.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.one.afternoon.domain.Teacher;

import java.util.List;

@Mapper
public interface TeacherMapper {

    List<Teacher> findAll();

    List<Teacher> queryOne(Teacher teacher);

    boolean insert(Teacher teacher);

    boolean deleteById(int id);

    boolean update(Teacher teacher);

    Teacher findTeacherById(int id);

    Integer getLastId();
}
