package com.zrk.dao;

import com.zrk.model.PermissionModule;

public interface PermissionModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PermissionModule record);

    int insertSelective(PermissionModule record);

    PermissionModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PermissionModule record);

    int updateByPrimaryKey(PermissionModule record);
}