package com.zrk.service.impl;

import com.zrk.dao.PermissionsMapper;
import com.zrk.exception.InvalidParamException;
import com.zrk.model.Permissions;
import com.zrk.model.web.ResultStatus;
import com.zrk.request.PermissionRequest;
import com.zrk.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/9
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionsMapper permissionsMapper;

    @Override
    public ResultStatus addPermission(PermissionRequest request) {
        if(checkExist(request.getPermModuleId(), request.getName(),null)){
            throw new InvalidParamException("该权限模块下该权限名称已被使用");
        }
        Permissions permissions = buildDO4Add(request);
        permissions.setCode(generateCode());
        permissions.setCreateUserId(1L);
        permissions.setUpdateUserId(1L);
        permissionsMapper.insertSelective(permissions);
        return new ResultStatus();
    }

    @Override
    public ResultStatus editPermission(PermissionRequest request) {
        if(checkExist(request.getPermModuleId(), request.getName(),request.getId())){
            throw new InvalidParamException("该权限模块下该权限名称已被使用");
        }
        Permissions permissionsOld = permissionsMapper.selectByPrimaryKey(request.getId());
        if(permissionsOld == null){
            throw new InvalidParamException("未获取到待更新权限");
        }
        Permissions permissionsNew = buildDO4Update(request);
        permissionsNew.setUpdateUserId(1L);
        permissionsMapper.updateByPrimaryKeySelective(permissionsNew);
        return new ResultStatus();
    }

    @Override
    public ResultStatus deletePermission(Long id) {
        if(permissionsMapper.deleteByPrimaryKey(id) > 0){
            return new ResultStatus();
        }
        return new ResultStatus(ResultStatus.GlobalStatus.ERROR,"删除权限失败");
    }

    private Boolean checkExist( Integer permModuleId, String name, Long id) {
        return permissionsMapper.findPermissionByModuleAndName(permModuleId,name,id) > 0;
    }

    public String generateCode() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date()) + "_" + (int)(Math.random() * 100);
    }

    private Permissions buildDO4Add(PermissionRequest request) {
        return Permissions.builder()
                .name(request.getName())
                .permModuleId(request.getPermModuleId())
                .seq(request.getSeq())
                .status(request.getStatus())
                .remark(request.getRemark())
                .createTime(new Date())
                .updateTime(new Date())
                .build();
    }

    private Permissions buildDO4Update(PermissionRequest request) {
        return Permissions.builder()
                .id(request.getId())
                .name(request.getName())
                .permModuleId(request.getPermModuleId())
                .seq(request.getSeq())
                .status(request.getStatus())
                .remark(request.getRemark())
                .createTime(new Date())
                .updateTime(new Date())
                .build();
    }
}
