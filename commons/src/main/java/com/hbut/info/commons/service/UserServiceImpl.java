package com.hbut.info.commons.service;

import com.hbut.info.commons.dao.UserMapper;
import com.hbut.info.commons.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/4/12.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userMapper.deleteUser(user);
    }
}
