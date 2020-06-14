package com.zrk.service.impl;

import com.zrk.config.webmvc.RequestHolder;
import com.zrk.dao.RolePermissionMapper;
import com.zrk.exception.ResultEmptyException;
import com.zrk.model.RolePermission;
import com.zrk.model.web.ResultStatus;
import com.zrk.request.RolePermRequest;
import com.zrk.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/14
 */
@Slf4j
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public ResultStatus updateRolePerms(RolePermRequest request) {
        //比较权限是否有调整
        List<Long> permissionIdListOld = rolePermissionMapper.getPermissionIdListByRoleIds(Collections.singletonList(request.getRoleId()));
        if(CollectionUtils.isEmpty(permissionIdListOld)){
            throw new ResultEmptyException();
        }
        List<Long> permissionIds = request.getPermissionIds();
        if(permissionIdListOld.size() == permissionIds.size()){
            Set<Long> permissionIdSetOld = new HashSet<>(permissionIdListOld);
            Set<Long> permissionIdSetNew = new HashSet<>(permissionIds);
            permissionIdSetOld.removeAll(permissionIdSetNew);
            if (CollectionUtils.isEmpty(permissionIdSetOld)) {
                return new ResultStatus();
            }
        }
        updateRolePermsF(request.getRoleId(),request.getPermissionIds());
        return new ResultStatus();
    }

    private void updateRolePermsF(Long roleId, List<Long> permissionIds) {
        rolePermissionMapper.deleteByRoleId(roleId);
        if(CollectionUtils.isEmpty(permissionIds)){
            return;
        }
        List<RolePermission> rolePermissionList = new ArrayList<>();
        permissionIds.forEach(p -> rolePermissionList.add(buildDO4Add(roleId,p)));
        rolePermissionMapper.batchInsert(rolePermissionList);
    }

    private RolePermission buildDO4Add(Long roleId, Long permId) {
        return RolePermission.builder()
                .roleId(roleId)
                .permId(permId)
                .createUserId(RequestHolder.getCurrentUser().getId())
                .updateUserId(RequestHolder.getCurrentUser().getId())
                .createTime(new Date())
                .updateTime(new Date())
                .build();
    }
}
