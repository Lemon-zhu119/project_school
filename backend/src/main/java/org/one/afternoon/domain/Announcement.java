package org.one.afternoon.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Announcement {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String time;
}
