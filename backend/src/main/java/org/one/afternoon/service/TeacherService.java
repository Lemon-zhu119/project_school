package org.one.afternoon.service;


import org.one.afternoon.domain.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {

    List<Teacher> findAll();

    List<Teacher> queryOne(Teacher teacher);

    boolean insert(Teacher teacher);

    boolean deleteById(int id);

    boolean update(Teacher teacher);

    Teacher findTeacherById(int id);

    Integer getLastId();
}
