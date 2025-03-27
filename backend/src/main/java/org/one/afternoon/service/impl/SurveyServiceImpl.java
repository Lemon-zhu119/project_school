package org.one.afternoon.service.impl;

import org.one.afternoon.domain.Survey;
import org.one.afternoon.mapper.SurveyMapper;
import org.one.afternoon.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyMapper surveyMapper;

    @Override
    public List<Survey> findAll() {
        return surveyMapper.findAll();
    }

    @Override
    public List<Survey> queryOne(Survey survey) {
        return surveyMapper.queryOne(survey);
    }

    @Override
    public boolean insert(Survey survey) {
        return surveyMapper.insert(survey);
    }

    @Override
    public boolean deleteById(int id) {
        return surveyMapper.deleteById(id);
    }

    @Override
    public boolean update(Survey survey) {
        return surveyMapper.update(survey);
    }

    @Override
    public Survey findSurveyById(int id) {
        return surveyMapper.findSurveyById(id);
    }

    @Override
    public Long getLasId() {
        return surveyMapper.getLastId();
    }


}
