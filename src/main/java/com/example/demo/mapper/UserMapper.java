package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from user where loginName=#{loginName} and password=#{password}")
    public User login(User user);

    @Select("select * from user")
    public List<User> getUserList(HashMap map);


    @Select(" select count(*) from user")
    Integer userCount();



}
