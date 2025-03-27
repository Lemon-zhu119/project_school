package org.one.afternoon.domain;
import lombok.Data;
@Data
public class Survey {
    private Long id;
    private String name;
    private String picture;
    private String content;
    private String time;
    private String fileFlag; // 用于存储文件的标识
}
