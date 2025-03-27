package org.one.afternoon.service;


import org.one.afternoon.domain.Parent;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public interface ParentService {

    List<Parent> findAll();

    List<Parent> queryOne(Parent parent);

    boolean insert(Parent parent);



    boolean update(Parent parent);
    Parent findByUsername(String username);

    Integer getLastId();

    boolean deleteById(Integer index);
}
