package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    HttpServletRequest request;

    @RequestMapping("/login")
    @ResponseBody
    public User login(User user) {
        HttpSession session = request.getSession();
        User loginUser = userService.login(user);
        if (loginUser != null){
            session.setAttribute("user",loginUser);
//            return "success";
        } else{
//            return "false";
        }
        return loginUser;
    }


}
