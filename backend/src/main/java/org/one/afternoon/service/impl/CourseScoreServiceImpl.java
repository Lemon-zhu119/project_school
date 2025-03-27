package org.one.afternoon.service.impl;

import org.one.afternoon.domain.CourseScore;
import org.one.afternoon.mapper.CourseScoreMapper;
import org.one.afternoon.service.CourseScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseScoreServiceImpl implements CourseScoreService {
    @Autowired
    private CourseScoreMapper courseScoreMapper;

    @Override
    public List<CourseScore> findAll() {
        return courseScoreMapper.findAll();
    }

    @Override
    public List<CourseScore> queryOne(CourseScore courseScore) {
        return courseScoreMapper.queryOne(courseScore);
    }

    @Override
    public boolean insert(CourseScore courseScore) {
        return courseScoreMapper.insert(courseScore);
    }

    @Override
    public boolean deleteById(int id) {
        return courseScoreMapper.deleteById(id);
    }

    @Override
    public boolean update(CourseScore courseScore) {
        return courseScoreMapper.update(courseScore);
    }

    @Override
    public List<CourseScore> findCourseScoreById(int id) {
        return courseScoreMapper.findCourseScoreById(id);
    }

    @Override
    public Integer getLastId() {
        return courseScoreMapper.getLastId();
    }


}
