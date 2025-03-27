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
public class CourseScore {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String studentId;
    private String courseId;
    private String  courseName;
    private double score;
}
