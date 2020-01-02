package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public User login(User user) {
        return userDao.login(user);
    }

    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }


    @Override
    public Integer userCount() {
        return userDao.userCount();
    }

    @Override
    public List<User> getUserList2(HashMap map) {
        return userDao.getUserList2(map);
    }

    @Override
    public int updateUserList(User user) {
        return userDao.updateUserList(user);
    }

    @Override
    public int delete(Integer id) {
        return userDao.delete(id);
    }

    @Override
    public int add(User user) {
        return userDao.add(user);
    }

    @Override
    public User select(User user) {
        return userDao.select(user);
    }

    @Override
    public int updateHeadPath(User user) {
        return userDao.updateHeadPath(user);
    }
}
