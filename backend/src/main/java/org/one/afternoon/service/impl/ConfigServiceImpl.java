package org.one.afternoon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.one.afternoon.domain.Config;
import org.one.afternoon.mapper.ConfigMapper;
import org.one.afternoon.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService  {
    @Autowired
    private ConfigMapper configMapper;

    @Override
    public List<Config> findAll() {
        return configMapper.findAll();
    }


    @Override
    public int insert(Config config) {
        return configMapper.insert(config);
    }

    @Override
    public boolean deleteById(int id) {
        return configMapper.deleteById(id);
    }

    @Override
    public boolean update(Config config) {
        return configMapper.update(config);
    }

    @Override
    public boolean batchInsert(List<Config> configs) {
        return saveBatch(configs);
    }

    @Override
    public Config findByUserId(Integer userId) {
        return configMapper.selectById(userId);
    }

    @Override
    public boolean saveConfig(Config config) {


        Config old = this.getOne(
                new LambdaQueryWrapper<Config>()
                        .eq(Config::getUserId, config.getUserId())
        );


        if(old == null){

            //第一次保存
            return this.save(config);

        }else{

            //更新
            config.setUserId(old.getUserId());

            return this.updateById(config);

        }

    }
}
