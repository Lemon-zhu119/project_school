package org.one.afternoon.service;
import org.one.afternoon.domain.SurveyFeedback;
import org.one.afternoon.domain.SurveyFeedbackRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SurveyFeedbackRecordService {
    Integer getLastSurveyRecordId();
    boolean addSurveyFeedbackRecord(SurveyFeedbackRecord surveyFeedbackRecord);
    List<SurveyFeedback> queryOne(SurveyFeedback surveyFeedback);
    List<SurveyFeedback> findAll();
    SurveyFeedback findById(int id);

    boolean update(SurveyFeedbackRecord surveyFeedbackRecordRecord);

    boolean deleteById(Integer index);
}
