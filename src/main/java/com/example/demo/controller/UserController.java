package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.tool.Tool;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
//    @RequestMapping("/back.action")
//    public String back() {
//        return "back";
//    }

    //点击列表一跳转至列表页面
//    @RequestMapping("/userListPage.action")
//    public String userListPage() {
//        return "userlist.html";
//    }

    //不带分页的列表
    @RequestMapping("/userList.action")
    @ResponseBody
    public Map<String, Object> userList() {
        List<User> userList = userService.getUserList();
        return Tool.testLayui(userList, 0, 0);
    }


    @RequestMapping("/userList2")
    @ResponseBody
    public Map<String, Object> UserList2(int page, int limit, User user) {
        HashMap<String, Object> map = new HashMap<>();
        int pagestart = (page - 1) * limit;
        map.put("pagestart", pagestart);
        map.put("size", limit);
        map.put("name", user.getName());//查询条件
        map.put("address", user.getAddress());
        List<User> userList = userService.getUserList2(map);
        Integer pagecount = userService.userCount();
        Map<String, Object> returnTable = Tool.testLayui(userList, page, limit);
        returnTable.put("count", pagecount);
        return returnTable;
    }


    @RequestMapping("/updateUserList")
    @ResponseBody
    public int updateUserList(User user) {
        return userService.updateUserList(user);
    }

    @RequestMapping("/delete")
    public @ResponseBody
    int delete(String ids) {
        boolean d = ids.endsWith(",");
        if (d) {
            ids = ids.substring(0, ids.length() - 1);
        }
        String[] all = ids.split(",");

        int result = 0;
        for (String id : all) {
            result = userService.delete(Integer.parseInt(id));
        }
        return result;
    }

//    @RequestMapping("/add.action")
//    public String add() {
//        return "add.html";
//    }

    @RequestMapping("/addUser")
    @ResponseBody
    public int addUser(@RequestBody User user) throws IOException {
        Map<String, Object> map = new HashMap<>();
        int add = userService.add(user);
        return add;

    }

    @RequestMapping("/uploadFile")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile pictureFile, Integer id) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String filname = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
        filname = filname + "." + extension;
        String path = "D:\\upload";
        User user = new User();
        user.setHeadpath(filname);
        user.setId(id);
        int i = userService.updateHeadPath(user);
        if (i > 0) {       //当数据库有记录时才上传文件
            pictureFile.transferTo(new File("D:\\upload\\" + filname));
        }
        File dir = new File(path, filname);
        if (dir.exists()) {    //当有文件时上传记录
            i = userService.updateHeadPath(user);
        }
        if (dir.exists() && i < 1) {     //数据库有记录但是文件上传失败
            dir.delete();
            map.put("msg", "上传失败");
            map.put("code", 1);
        } else if (!dir.exists() && i > 0) {    //数据库无记录但是文件上传成功
            user.setHeadpath(null);
            userService.updateHeadPath(user);
            dir.delete();
            map.put("msg", "上传失败");
            map.put("code", 1);
        } else if (!dir.exists() && i < 1) {      //数据库无记录文件也未上传成功
            dir.delete();
            map.put("msg", "上传失败");
            map.put("code", 1);
        } else {             //数据库有记录文件也上传成功
            dir.mkdirs();
            map.put("msg", "上传成功");
            map.put("code", 0);
            map.put("src", "http://localhost:8086/pic/" + filname);
        }
        return map;
    }
}
