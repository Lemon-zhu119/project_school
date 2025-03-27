package org.one.afternoon.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class LeaveRequestRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer courseId;
    private String date;
    private String reason;
    private String reviewResponse;
    private String parentUsername;
    private Integer status;
}
