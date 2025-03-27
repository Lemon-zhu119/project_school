package org.one.afternoon.service;


import org.one.afternoon.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<Student> findAll();

    List<Student> queryOne(Student student);

    boolean insert(Student student);

    boolean deleteById(int id);

    boolean update(Student student);

    Integer getLastId();
}
