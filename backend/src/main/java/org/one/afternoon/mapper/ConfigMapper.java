package org.one.afternoon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.one.afternoon.domain.Config;

import java.util.List;

@Mapper
public interface ConfigMapper extends BaseMapper<Config> {

    List<Config> findAll();

    int insert(Config config);

    boolean deleteById(int listingId);

    boolean update(Config config);
}
