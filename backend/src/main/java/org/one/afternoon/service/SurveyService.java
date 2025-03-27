package org.one.afternoon.service;


import org.one.afternoon.domain.Survey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SurveyService {

    List<Survey> findAll();

    List<Survey> queryOne(Survey survey);

    boolean insert(Survey survey);

    boolean deleteById(int id);

    boolean update(Survey survey);

    Survey findSurveyById(int id);

    Long getLasId();
}
