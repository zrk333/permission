package com.zrk.service.impl;

import com.zrk.dao.RoleMapper;
import com.zrk.exception.InvalidParamException;
import com.zrk.model.Role;
import com.zrk.model.web.ResultStatus;
import com.zrk.request.RoleRequest;
import com.zrk.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
        if(checkExist(request.getName(),null)){
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
        if(checkExist(request.getName(),request.getId())){
            throw new InvalidParamException("该角色名称已被使用");
        }
        Role roleOld = roleMapper.selectByPrimaryKey(request.getId());
        if(roleOld == null){
            throw new InvalidParamException("未获取到待更新角色");
        }
        Role roleNew = buildDO4Update(request);
        roleNew.setUpdateUserId(1L);
        roleMapper.updateByPrimaryKeySelective(roleNew);
        return new ResultStatus();
    }

    @Override
    public ResultStatus deleteRole(Long id) {
        if(roleMapper.deleteByPrimaryKey(id) > 0){
            return new ResultStatus();
        }
        return new ResultStatus(ResultStatus.GlobalStatus.ERROR,"删除角色失败");
    }

    @Override
    public ResultStatus getRoleList() {
        List<Role> roleList = roleMapper.getRoleList();
        if(!CollectionUtils.isEmpty(roleList)){
            ResultStatus resultStatus = new ResultStatus();
            resultStatus.setData(roleList);
            return resultStatus;
        }
        return new ResultStatus(ResultStatus.GlobalStatus.RESULT_EMPTY);
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

    private Role buildDO4Update(RoleRequest request) {
        return Role.builder()
                .id(request.getId())
                .name(request.getName())
                .type(request.getType())
                .status(request.getStatus())
                .remark(request.getRemark())
                .createTime(new Date())
                .updateTime(new Date())
                .build();
    }
}
