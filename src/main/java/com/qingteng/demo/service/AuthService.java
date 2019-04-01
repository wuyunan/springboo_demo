package com.qingteng.demo.service;

import com.qingteng.demo.entity.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}