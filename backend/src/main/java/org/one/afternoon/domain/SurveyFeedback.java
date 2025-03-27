package org.one.afternoon.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 对应用户表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SurveyFeedback {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String time;
    private String parentUsername;
    private String parentName;
    private String studentName;
    private String studentId;
    private String feedbackContent;
    private Integer status;
}
