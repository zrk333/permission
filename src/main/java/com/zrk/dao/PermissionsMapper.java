package com.zrk.dao;

import com.zrk.model.Permissions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 获取权限模块下权限数量
     * @param permModuleId
     * @return
     */
    Integer findPermissionBYModuleId(@Param("permModuleId") Long permModuleId);

    /**
     * 获取指定权限模块下权限列表
     * @param permModuleId
     * @param from
     * @param size
     * @return
     */
    List<Permissions> getPermissionListByModuleId(@Param("permModuleId") Long permModuleId, @Param("from") Integer from, @Param("size") Integer size);
}