package com.zzm.demo.mapper;

import com.zzm.demo.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);



    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}