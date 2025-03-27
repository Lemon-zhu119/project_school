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
public class ActivityRegistration {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String registrationCount;
    private String registrationTime;
    private String name;
    private String parentUsername;
    private String parentName;
    private String date;
    private String location;
    private String classes;
    private String studentName;
    private String studentId;
    private Integer status;
    private String reviewResponse;
}
