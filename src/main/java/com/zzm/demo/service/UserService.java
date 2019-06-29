package com.zzm.demo.service;

import com.zzm.demo.entity.User;

/**
 * @ClassName UserService
 * @Author ZhiMing
 * @Date 2019-04-2016:46
 **/
public interface UserService {

//    List<User> findAllUser();
//
//    User selectUser(Integer id);
//
//    void update(User user);
    User selectByPrimaryKey(Integer id);


    int insert(User user);

}
