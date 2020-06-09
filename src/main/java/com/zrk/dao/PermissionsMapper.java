package com.zrk.dao;

import com.zrk.model.Permissions;
import org.apache.ibatis.annotations.Param;

public interface PermissionsMapper {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(Permissions record);

    int insertSelective(Permissions record);

    Permissions selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(Permissions record);

    int updateByPrimaryKey(Permissions record);

    /**
     * 根据权限模块id和名称获取权限数量
     * @param permModuleId
     * @param name
     * @param id
     * @return
     */
    Integer findPermissionByModuleAndName(@Param("permModuleId") Integer permModuleId, @Param("name") String name, @Param("id") Long id);
}