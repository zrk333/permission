package com.zrk.dao;

import com.zrk.model.RoleUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleUserMapper {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(RoleUser record);

    int insertSelective(RoleUser record);

    RoleUser selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(RoleUser record);

    int updateByPrimaryKey(RoleUser record);

    /**
     * 根据用户ids获取角色id列表
     * @param userId
     * @return
     */
    List<Long> getRoleIdListByUserId(@Param("userId") Long userId);
}