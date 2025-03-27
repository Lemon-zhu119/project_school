package org.one.afternoon.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class LeaveRequest {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer courseId;
    private String date;
    private String course;
    private String reason;
    private Integer teacherId;
    private String teacher;
    private String parentUsername;
    private String parentName;
    private String sId;
    private String studentName;
    private String classes;
    private String reviewResponse;
    private Integer status;
}
