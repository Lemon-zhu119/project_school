package org.one.afternoon.service.impl;

import org.one.afternoon.domain.Student;
import org.one.afternoon.mapper.StudentMapper;
import org.one.afternoon.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public List<Student> queryOne(Student student) {
        return studentMapper.queryOne(student);
    }

    @Override
    public boolean insert(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public boolean deleteById(int id) {
        return studentMapper.deleteById(id);
    }

    @Override
    public boolean update(Student student) {
        return studentMapper.update(student);
    }

    @Override
    public Integer getLastId() {
        return studentMapper.getLastId();
    }


}
