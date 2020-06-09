package com.zrk.service.impl;

import com.zrk.dao.RoleMapper;
import com.zrk.exception.InvalidParamException;
import com.zrk.model.Role;
import com.zrk.model.web.ResultStatus;
import com.zrk.request.RoleRequest;
import com.zrk.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/10
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public ResultStatus addRole(RoleRequest request) {
        if(checkExist(request.getName(),request.getId())){
            throw new InvalidParamException("该角色名称已被使用");
        }
        Role role = buildDO4Add(request);
        role.setCreateUserId(1L);
        role.setUpdateUserId(1L);
        roleMapper.insertSelective(role);
        return new ResultStatus();
    }

    @Override
    public ResultStatus editRole(RoleRequest request) {
        return null;
    }

    @Override
    public ResultStatus deleteRole(Long id) {
        return null;
    }

    private Boolean checkExist(String name, Long id) {
        return roleMapper.findRoleByName(name,id) > 0;
    }

    private Role buildDO4Add(RoleRequest request) {
        return Role.builder()
                .name(request.getName())
                .type(request.getType())
                .status(request.getStatus())
                .remark(request.getRemark())
                .createTime(new Date())
                .updateTime(new Date())
                .build();
    }
}
