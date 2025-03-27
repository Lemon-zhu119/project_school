package org.one.afternoon.service.impl;

import org.one.afternoon.domain.Teacher;
import org.one.afternoon.mapper.TeacherMapper;
import org.one.afternoon.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> findAll() {
        return teacherMapper.findAll();
    }

    @Override
    public List<Teacher> queryOne(Teacher teacher) {
        return teacherMapper.queryOne(teacher);
    }

    @Override
    public boolean insert(Teacher teacher) {
        return teacherMapper.insert(teacher);
    }

    @Override
    public boolean deleteById(int id) {
        return teacherMapper.deleteById(id);
    }

    @Override
    public boolean update(Teacher teacher) {
        return teacherMapper.update(teacher);
    }

    @Override
    public Teacher findTeacherById(int id) {
        return teacherMapper.findTeacherById(id);
    }

    @Override
    public Integer getLastId() {
        return teacherMapper.getLastId();
    }


}
