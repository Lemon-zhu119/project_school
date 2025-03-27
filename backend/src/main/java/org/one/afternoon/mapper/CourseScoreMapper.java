package org.one.afternoon.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.one.afternoon.domain.CourseScore;

import java.util.List;

@Mapper
public interface CourseScoreMapper {

    List<CourseScore> findAll();

    List<CourseScore> queryOne(CourseScore courseScore);

    boolean insert(CourseScore courseScore);

    boolean deleteById(int id);

    boolean update(CourseScore courseScore);

    List<CourseScore> findCourseScoreById(int id);

    Integer getLastId();
}
