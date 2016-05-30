package com.hbut.info.igsWeb.controller;

import com.hbut.info.commons.model.User;
import com.hbut.info.commons.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/11.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 用户登录
     * @param model
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public String login(Model model,String username,String password){
        List<User> userList = new ArrayList<>();
        userList = userService.findAll();
        for (User user:userList){
            if (username.equals(user.getName()) && password.equals(user.getPassword())){
                User userSeession = new User();
                userSeession.setName(username);
                userSeession.setPassword(password);
                return "index";
            }
        }
        return "login";
    }

    /**
     * 用户登出
     * @param model
     * @return
     */
    @RequestMapping("/loginOut")
    public String loginOut(Model model){
        return "login";
    }

    /**
     * 新增用户
     * @param model
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(Model model,String username,String password){
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        userService.addUser(user);
        return "index";
    }

    /**
     * 修改用户
     * @param model
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/updateUser")
    public String updateUser(Model model,String username,String password){
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        userService.updateUser(user);
        return "index";
    }

    /**
     * 删除用户
     * @param model
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/delUser")
    public String delUser(Model model,String username,String password){
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        userService.deleteUser(user);
        return "index";
    }
}
