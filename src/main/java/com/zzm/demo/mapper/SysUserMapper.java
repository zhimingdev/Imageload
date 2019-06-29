package com.zzm.demo.mapper;

import com.zzm.demo.entity.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}