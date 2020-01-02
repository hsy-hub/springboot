package com.example.demo.service;

import com.example.demo.pojo.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    public User login(User user);

    public List<User> getUserList();

    public Integer userCount();

    public List<User> getUserList2(HashMap map);

    public int updateUserList(User user);

    public int delete(Integer id);

    public int add(User user);

    public User select(User user);

    public int updateHeadPath(User user);
}
