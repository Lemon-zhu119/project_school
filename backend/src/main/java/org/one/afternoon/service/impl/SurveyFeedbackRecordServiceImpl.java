package org.one.afternoon.service.impl;
import org.one.afternoon.domain.SurveyFeedback;
import org.one.afternoon.domain.SurveyFeedbackRecord;
import org.one.afternoon.mapper.SurveyFeedbackRecordMapper;
import org.one.afternoon.service.SurveyFeedbackRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyFeedbackRecordServiceImpl implements SurveyFeedbackRecordService {
    @Autowired
    private SurveyFeedbackRecordMapper surveyFeedbackRecordMapper;

    @Override
    public Integer getLastSurveyRecordId() {
        // 实现方法逻辑
        return surveyFeedbackRecordMapper.getLastSurveyFeedbackRecordId();
    }

    @Override
    public boolean addSurveyFeedbackRecord(SurveyFeedbackRecord surveyRecord ) {
        // 实现方法逻辑
        return surveyFeedbackRecordMapper.insert(surveyRecord);
    }

    @Override
    public List<SurveyFeedback> queryOne(SurveyFeedback surveyFeedback) {
        // 实现方法逻辑
        return surveyFeedbackRecordMapper.queryOne(surveyFeedback);
    }

    @Override
    public List<SurveyFeedback> findAll() {
        // 实现方法逻辑
        return surveyFeedbackRecordMapper.findAll();
    }
    @Override
    public SurveyFeedback findById(int id){
        return surveyFeedbackRecordMapper.findById(id);
    }

    @Override
    public boolean update(SurveyFeedbackRecord surveyFeedbackRecordRecord) {
        return surveyFeedbackRecordMapper.update(surveyFeedbackRecordRecord);
    }

    @Override
    public boolean deleteById(Integer index) {
        return surveyFeedbackRecordMapper.deleteById(index);
    }
}