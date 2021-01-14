package com.netkreker.crackermarket.services;

import com.netkreker.crackermarket.models.user.User;

import java.util.List;


public interface UserService {
    List<User> findAll();
    User findById(String id);
    User findByUsername(String username);
    User findByEmail(String email);
    void persist(User user);
    void delete(User user);
}
