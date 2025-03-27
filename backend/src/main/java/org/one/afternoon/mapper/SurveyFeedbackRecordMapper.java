package org.one.afternoon.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.one.afternoon.domain.SurveyFeedback;
import org.one.afternoon.domain.SurveyFeedbackRecord;
import java.util.List;
@Mapper
public interface SurveyFeedbackRecordMapper {
    Integer getLastSurveyFeedbackRecordId();
    public boolean insert(SurveyFeedbackRecord SurveyFeedbackRecord);
    List<SurveyFeedback> queryOne(SurveyFeedback surveyFeedback);
    List<SurveyFeedback> findAll();

    SurveyFeedback findById(int id);

    boolean update(SurveyFeedbackRecord surveyFeedbackRecordRecord);

    boolean deleteById(Integer index);
}
