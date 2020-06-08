package com.zrk.service.impl;

import com.zrk.dao.PermissionModuleMapper;
import com.zrk.exception.InvalidParamException;
import com.zrk.model.PermissionModule;
import com.zrk.model.web.ResultStatus;
import com.zrk.request.PermissionModuleRequest;
import com.zrk.service.PermissionModuleService;
import com.zrk.util.LevelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/8
 */
@Slf4j
@Service
public class PermissionModuleServiceImpl implements PermissionModuleService {

    @Resource
    private PermissionModuleMapper permissionModuleMapper;

    @Override
    public ResultStatus addModule(PermissionModuleRequest request) {
        if(checkExist(request.getParentId(),request.getName(),null)){
            throw new InvalidParamException("该层级下已存在该权限模块名称");
        }
        PermissionModule permissionModule = buildDO4Add(request);
        permissionModule.setLevel(LevelUtil.calculateLevel(getLevel(request.getParentId()),request.getParentId()));
        // TODO
        permissionModule.setCreateUserId(0L);
        permissionModule.setUpdateUserId(0L);
        permissionModuleMapper.insertSelective(permissionModule);
        return new ResultStatus();
    }

    @Override
    public ResultStatus editModule(PermissionModuleRequest request) {
        return null;
    }

    @Override
    public ResultStatus deleteModule(Integer id) {
        return null;
    }


    private String getLevel(Long parentId) {
        PermissionModule permissionModule = permissionModuleMapper.selectByPrimaryKey(parentId);
        return permissionModule == null ? null : permissionModule.getLevel();
    }

    private PermissionModule buildDO4Add(PermissionModuleRequest request) {
        return PermissionModule.builder()
                .name(request.getName())
                .parentId(request.getParentId())
                .seq(request.getSeq())
                .remark(request.getRemark())
                .createTime(new Date())
                .updateTime(new Date())
                .build();
    }

    /**
     * 校验同一层级下是否存在该权限模块
     * @param parentId
     * @param name
     * @param id
     * @return
     */
    private Boolean checkExist(Long parentId, String name, Integer id) {
        return permissionModuleMapper.findModuleByNameAndParentId(parentId,name,id) > 0;
    }
}
