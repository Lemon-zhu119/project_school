package org.one.afternoon.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 对应磨损表
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wear {

    @TableId(value = "market_bucket_group_id")
    @JsonProperty("market_bucket_group_id")
    private String marketBucketGroupId;

    private String listingId;
    @JsonProperty("market_hash_name")
    private String marketHashName;
//    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal min;

    private BigDecimal max;
    public BigDecimal getMin() {
        if (min == null) {
            return null;
        }

        if (min.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return min.stripTrailingZeros();
    }

    public BigDecimal getMax() {
        if (max == null) {
            return null;
        }

        if (max.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return max.stripTrailingZeros();
    }
}
