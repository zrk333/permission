package com.zrk.dao;

import com.zrk.model.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    /**
     * 根据角色ids获取权限id列表
     * @param userRoleIdList
     * @return
     */
    List<Long> getPermissionIdListByRoleIds(@Param("roleIdList") List<Long> userRoleIdList);

    /**
     * 根据角色id删除权限关系
     * @param roleId
     */
    void deleteByRoleId(@Param("roleId") Long roleId);

    /**
     * 批量插入角色权限关系
     * @param rolePermissionList
     */
    void batchInsert(@Param("list") List<RolePermission> rolePermissionList);
}