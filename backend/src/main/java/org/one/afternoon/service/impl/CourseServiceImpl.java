package org.one.afternoon.service.impl;

import org.one.afternoon.domain.Course;
import org.one.afternoon.mapper.CourseMapper;
import org.one.afternoon.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findAll() {
        return courseMapper.findAll();
    }

    @Override
    public List<Course> queryOne(Course course) {
        return courseMapper.queryOne(course);
    }

    @Override
    public boolean insert(Course course) {
        return courseMapper.insert(course);
    }

    @Override
    public boolean deleteById(int id) {
        return courseMapper.deleteById(id);
    }

    @Override
    public boolean update(Course course) {
        return courseMapper.update(course);
    }

    @Override
    public Course findCourseById(int id) {
        return courseMapper.findCourseById(id);
    }


}
