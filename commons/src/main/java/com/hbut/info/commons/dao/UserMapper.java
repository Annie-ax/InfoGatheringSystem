package com.hbut.info.commons.dao;

import com.hbut.info.commons.model.User;

import java.util.List;

/**
 * Created by Administrator on 2016/4/12.
 */
public interface UserMapper {
    /**
     * 查询所有的用户
     * @return
     */
    List<User> findAll();

    /**
     * 增加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 删除用户
     * @param user
     */
    void deleteUser(User user);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);
}
