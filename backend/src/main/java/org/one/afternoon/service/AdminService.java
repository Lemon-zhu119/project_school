package org.one.afternoon.service;


import org.one.afternoon.domain.Admin;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public interface AdminService {

    List<Admin> findAll();

    List<Admin> queryOne(Admin admin);

    boolean insert(Admin admin);

    boolean deleteById(int id);

    boolean update(Admin admin);

    boolean login(Admin admin) throws NoSuchAlgorithmException;

    boolean logout(String username);

    Integer queryTdy(Integer time);

    Integer queryNew(Integer time);
    Admin findByUsername(String username);
}
