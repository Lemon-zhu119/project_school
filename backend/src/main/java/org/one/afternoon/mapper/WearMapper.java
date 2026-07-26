package org.one.afternoon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.one.afternoon.domain.Wear;

import java.util.List;

@Mapper
public interface WearMapper extends BaseMapper<Wear> {

    List<Wear> findAll();

    int insert(Wear wear);

    boolean deleteById(int listingId);

    boolean update(Wear wear);
}
