package com.example.demo.dao;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    UserMapper userMapper;

    public User login(User user) {
        return userMapper.login(user);
    }
}
