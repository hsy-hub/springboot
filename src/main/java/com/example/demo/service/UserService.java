package com.example.demo.service;

import com.example.demo.pojo.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    public User login(User user);
    public List<User> getUserList(HashMap map);
    Integer userCount();
}
