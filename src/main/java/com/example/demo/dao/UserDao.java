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

    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    public Integer userCount() {
        return userMapper.userCount();
    }

    public List<User> getUserList2(HashMap map) {
        return userMapper.getUserList2(map);
    }

    public int updateUserList(User user) {
        return userMapper.updateUserList(user);
    }

    public int delete(Integer id) {
        return userMapper.delete(id);
    }

    public int add(User user) {
        return userMapper.add(user);
    }

    public User select(User user) {
        return userMapper.select(user);
    }

    public int updateHeadPath(User user) {
        return userMapper.updateHeadPath(user);
    }
}
