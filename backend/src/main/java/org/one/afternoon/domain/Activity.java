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
public class Activity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String type;
    private String picture;
    private String name;
    private String content;
    private String date;
    private String location;
    private int status;
    private int favoriteCount;
}
