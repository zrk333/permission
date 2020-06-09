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
        return null;
    }

    @Override
    public ResultStatus deletePermission(Long id) {
        return null;
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
}
