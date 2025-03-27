package org.one.afternoon.service;


import org.one.afternoon.domain.CourseScore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseScoreService {

    List<CourseScore> findAll();

    List<CourseScore> queryOne(CourseScore courseScore);

    boolean insert(CourseScore courseScore);

    boolean deleteById(int id);

    boolean update(CourseScore courseScore);

    List<CourseScore> findCourseScoreById(int id);

    Integer getLastId();
}
