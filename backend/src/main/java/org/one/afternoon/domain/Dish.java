package org.one.afternoon.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Dish {
    private Long id;
    private String name;
    private String date;
    private int favouriteCount;
    private String content;
    private String picture;
}
