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
public class RegistrationRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String activityId;
    private String registrationTime;
    private String registrationCount;
    private String parentId;
    private String studentId;
    private String reviewResponse;
}
