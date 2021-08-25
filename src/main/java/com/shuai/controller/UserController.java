package com.shuai.controller;

import com.shuai.enity.User;
import com.shuai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("list")
    public List<User> queryUserAll(){
        List<User> list=this.userService.findAll();
        return list;
    }
}
