package org.one.afternoon.service;


import org.one.afternoon.domain.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    List<Course> findAll();

    List<Course> queryOne(Course course);

    boolean insert(Course course);

    boolean deleteById(int id);

    boolean update(Course course);

    Course findCourseById(int id);
}
