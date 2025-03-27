package org.one.afternoon.service.impl;

import org.one.afternoon.domain.Parent;
import org.one.afternoon.domain.Student;
import org.one.afternoon.mapper.ParentMapper;
import org.one.afternoon.mapper.StudentMapper;
import org.one.afternoon.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class ParentServiceImpl implements ParentService {
    @Autowired
    private ParentMapper parentMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Parent> findAll() {
        return parentMapper.findAll();
    }

    @Override
    public List<Parent> queryOne(Parent parent) {
        return parentMapper.queryOne(parent);
    }

    @Override
    public boolean insert(Parent parent) {
        Student student=studentMapper.findStudentById(parent.getSid());
        student.setPUsername(parent.getUsername());
        studentMapper.update(student);
        return parentMapper.insert(parent);
    }



    @Override
    public boolean update(Parent parent) {
        Student student=studentMapper.findStudentById(parent.getSid());
        student.setPUsername(parent.getUsername());
        studentMapper.update(student);
        return parentMapper.update(parent);
    }

    @Override
    public Parent findByUsername(String username) {
        return parentMapper.findParentByUsername(username);
    }

    @Override
    public Integer getLastId() {
        return parentMapper.getLastId();
    }

    @Override
    public boolean deleteById(Integer index) {
        return parentMapper.deleteById(index);
    }


}
