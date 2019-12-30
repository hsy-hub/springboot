package com.example.demo.dao;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    UserMapper userMapper;

    public User login(User user) {
        return userMapper.login(user);
    }

    public List<User> getUserList(HashMap map) {
        return userMapper.getUserList(map);
    }

    public Integer userCount() {
        return userMapper.userCount();
    }
}
