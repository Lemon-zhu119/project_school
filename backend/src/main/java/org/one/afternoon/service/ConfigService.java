package org.one.afternoon.service;


import org.one.afternoon.domain.Config;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConfigService {

    List<Config> findAll();

    int insert(Config config);

    boolean deleteById(int id);

    boolean update(Config config);


    boolean batchInsert(List<Config> configs);

    Config findByUserId(Integer userId);

    boolean saveConfig(Config config);
}
