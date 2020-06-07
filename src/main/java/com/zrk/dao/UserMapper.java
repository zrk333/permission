package com.zrk.dao;

import com.zrk.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据手机号查找用户数量
     * @param phone
     * @param userId
     * @return
     */
    Integer findUserByPhone(@Param("phone") String phone, @Param("userId") Long userId);

    /**
     * 根据邮箱查找用户数量
     * @param email
     * @param userId
     * @return
     */
    Integer findUserByEmail(@Param("email") String email, @Param("userId") Long userId);

    /**
     * 获取指定部门下用户数量
     * @param deptId
     * @return
     */
    Integer findUserByDeptId(@Param("deptId") Long deptId);

    /**
     * 获取指定部门下用户列表
     * @param deptId
     * @param from
     * @param size
     * @return
     */
    List<User> getUserListByDeptId(@Param("deptId") Long deptId, @Param("from") Integer from, @Param("size") Integer size);
}