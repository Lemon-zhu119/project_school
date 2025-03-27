package org.one.afternoon.service;


import org.one.afternoon.domain.User;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public interface UserService {

    List<User> findAll();
    Integer getLastId();
    List<User> queryOne(User user);

    boolean insert(User user);

    boolean deleteById(int id);

    boolean update(User user);

    boolean login(User user) throws NoSuchAlgorithmException;

    boolean logout(String username);

    Integer queryTdy(Integer time);

    Integer queryNew(Integer time);


}
