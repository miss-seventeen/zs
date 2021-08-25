package com.shuai.service.impl;

import com.shuai.dao.UserDao;
import com.shuai.enity.User;
import com.shuai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
@Autowired
    private UserDao userDao;
@Override
    public List<User> findAll(){
    List<User> list =this.userDao.findAll();
    return  list;
}
}
