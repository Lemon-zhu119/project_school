package org.one.afternoon.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

/**
 * 对应用户表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Config {
    @TableId(value = "user_id")
    private Integer userId;
    private String config;
}
