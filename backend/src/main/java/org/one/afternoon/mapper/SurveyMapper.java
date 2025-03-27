package org.one.afternoon.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.one.afternoon.domain.Survey;

import java.util.List;

@Mapper
public interface SurveyMapper {

    List<Survey> findAll();

    List<Survey> queryOne(Survey survey);

    boolean insert(Survey survey);

    boolean deleteById(int id);

    boolean update(Survey survey);
    @Select("select * from survey where id=#{id}")
    Survey findSurveyById(int id);

    Long getLastId();
}
