package org.one.afternoon.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SurveyFeedbackRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer surveyId;
    private String time;
    private String feedbackContent;
    private String parentId;
    private String studentId;
    private Integer status;
}
