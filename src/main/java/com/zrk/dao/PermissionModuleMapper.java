package com.zrk.dao;

import com.zrk.model.PermissionModule;
import org.apache.ibatis.annotations.Param;

public interface PermissionModuleMapper {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(PermissionModule record);

    int insertSelective(PermissionModule record);

    PermissionModule selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(PermissionModule record);

    int updateByPrimaryKey(PermissionModule record);

    /**
     * 根据名称和父id校验是否存在该权限模块
     * @param parentId
     * @param name
     * @param id
     * @return
     */
    Integer findModuleByNameAndParentId(Long parentId, String name, Integer id);
}