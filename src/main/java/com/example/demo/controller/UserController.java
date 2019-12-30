package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.tools.Tool;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//不带分页
//    @RequestMapping("/userList")
//    @ResponseBody
//    public List<User> userList(HashMap map) {
//        List<User> userList = userService.getUserList(map);
//        return Tool.testLayui(userList, 0, 0);
//    }

//带分页
    @RequestMapping("/userList2.action")
    @ResponseBody
    public Map<String, Object> UserList2(int page, int limit, User user) {
        HashMap<String, Object> map = new HashMap<>();
        int pagestart = (page - 1) * limit;
        map.put("pagestart", pagestart);
        map.put("size", limit);
        map.put("name", user.getLoginName());//查询条件
        map.put("email", user.getEmail());
        List<User> userList = userService.getUserList(map);
        Integer pagecount = userService.userCount();
        Map<String, Object> returnTable = Tool.testLayui(userList, page, limit);
        returnTable.put("count", pagecount);
        return returnTable;

}
