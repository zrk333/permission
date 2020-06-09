package com.zrk.dao;

import com.zrk.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 根据名称查找角色
     * @param name
     * @param id
     * @return
     */
    Integer findRoleByName(@Param("name") String name, @Param("id") Long id);

    /**
     * 获取所有角色
     * @return
     */
    List<Role> getRoleList();
}