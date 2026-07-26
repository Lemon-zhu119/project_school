package org.one.afternoon.service;


import org.one.afternoon.domain.Wear;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public interface WearService {

    List<Wear> findAll();

    int insert(Wear wear);

    boolean deleteById(int id);

    boolean update(Wear wear);


    boolean batchInsert(List<Wear> wears);
}
