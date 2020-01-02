package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface UserMapper {
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
