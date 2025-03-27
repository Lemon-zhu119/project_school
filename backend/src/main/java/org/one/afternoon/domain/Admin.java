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
public class Admin {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    @TableField(fill = FieldFill.INSERT) //设置自动生成
    private Date createAt;
    @TableField(fill = FieldFill.INSERT_UPDATE) //设置自动修改/生成
    private Date updateAt;
    private Integer online;
    private String picture;
    private String name;
    private String number;
    private String email;

}
