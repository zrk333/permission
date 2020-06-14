package com.zrk.service.impl;

import com.zrk.config.webmvc.RequestHolder;
import com.zrk.dao.PermissionsMapper;
import com.zrk.dao.RolePermissionMapper;
import com.zrk.dao.RoleUserMapper;
import com.zrk.dao.UserMapper;
import com.zrk.model.Permissions;
import com.zrk.model.User;
import com.zrk.service.CoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/14
 */
@Slf4j
@Service
public class CoreServiceImpl implements CoreService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PermissionsMapper permissionsMapper;

    @Resource
    private RoleUserMapper roleUserMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Permissions> getCurrentUserPermissionList() {
        Long userId = RequestHolder.getCurrentUser().getId();
        return getUserPermissionList(userId);
    }

    private List<Permissions> getUserPermissionList(Long userId) {
        if(isSuperAdmin()){
            return permissionsMapper.getAllPermissions();
        }
        List<Long> userRoleIdList = roleUserMapper.getRoleIdListByUserId(userId);
        if(CollectionUtils.isEmpty(userRoleIdList)){
            return null;
        }
        List<Long> userPermissionIdList = rolePermissionMapper.getPermissionIdListByRoleIds(userRoleIdList);
        if(CollectionUtils.isEmpty(userPermissionIdList)){
            return null;
        }
        return permissionsMapper.getPermissionByIds(userPermissionIdList);
    }

    @Override
    public List<Permissions> getRolePermissionList(Long roleId) {
        List<Long> userPermissionIdList = rolePermissionMapper.getPermissionIdListByRoleIds(Collections.singletonList(roleId));
        if(CollectionUtils.isEmpty(userPermissionIdList)){
            return null;
        }
        return permissionsMapper.getPermissionByIds(userPermissionIdList);
    }

    private boolean isSuperAdmin() {
        Long userId = RequestHolder.getCurrentUser().getId();
        User user = userMapper.selectByPrimaryKey(userId);
        //暂定超级管理员规则
        if(user != null && user.getUserName().contains("Admin")){
            return true;
        }
        return false;
    }
}
