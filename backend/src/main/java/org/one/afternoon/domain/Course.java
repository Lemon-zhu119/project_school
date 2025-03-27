package org.one.afternoon.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

/**
 * 对应用户表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Course {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String teacherId;
    private String courseTeacherName;
    private int status;
    private String content;
    private String time;
    private String picture;
    private String location;
}
