package com.zrk.service.impl;

import com.zrk.config.webmvc.RequestHolder;
import com.zrk.dao.PermissionModuleMapper;
import com.zrk.exception.InvalidParamException;
import com.zrk.model.PermissionModule;
import com.zrk.model.web.ResultStatus;
import com.zrk.request.PermissionModuleRequest;
import com.zrk.service.PermissionModuleService;
import com.zrk.util.LevelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
        permissionModule.setCreateUserId(RequestHolder.getCurrentUser().getId());
        permissionModule.setUpdateUserId(RequestHolder.getCurrentUser().getId());
        permissionModuleMapper.insertSelective(permissionModule);
        return new ResultStatus();
    }

    @Override
    public ResultStatus editModule(PermissionModuleRequest request) {
        if(checkExist(request.getParentId(),request.getName(),request.getId())){
            throw new InvalidParamException("该层级下已存在该权限模块名称");
        }
        PermissionModule permissionModuleOld = permissionModuleMapper.selectByPrimaryKey(request.getId());
        if(permissionModuleOld == null){
            throw new InvalidParamException("未获取到待更新权限模块");
        }
        PermissionModule permissionModuleNew = buildDO4Update(request);
        permissionModuleNew.setUpdateUserId(RequestHolder.getCurrentUser().getId());
        permissionModuleNew.setLevel(LevelUtil.calculateLevel(getLevel(request.getParentId()),request.getParentId()));
        updateWithChild(permissionModuleOld,permissionModuleNew);
        return new ResultStatus();
    }

    /**
     * @param permissionModuleOld
     * @param permissionModuleNew
     */
    @Transactional(rollbackFor = Exception.class)
    private void updateWithChild(PermissionModule permissionModuleOld, PermissionModule permissionModuleNew) {
        String levelPrefixOld = permissionModuleOld.getLevel();
        String levelPrefixNew = permissionModuleNew.getLevel();
        if(!levelPrefixOld.equals(levelPrefixNew)){
            List<PermissionModule> permissionModuleList = permissionModuleMapper.getChildModuleByLevel(levelPrefixOld);
            if(!CollectionUtils.isEmpty(permissionModuleList)){
                permissionModuleList.forEach(p -> changeLevel(p,levelPrefixOld,levelPrefixNew));
                permissionModuleMapper.batchUpdateLevel(permissionModuleList);
            }
        }
        permissionModuleMapper.updateByPrimaryKey(permissionModuleNew);
    }

    private void changeLevel(PermissionModule permissionModule, String levelPrefixOld, String levelPrefixNew) {
        String level = permissionModule.getLevel();
        if(StringUtils.isNotEmpty(level) && level.indexOf(levelPrefixOld) == 0){
            permissionModule.setLevel(levelPrefixNew + level.substring(levelPrefixOld.length()));
        }
    }

    @Override
    public ResultStatus deleteModule(Long id) {
        PermissionModule permissionModule = permissionModuleMapper.selectByPrimaryKey(id);
        if(permissionModule == null){
            throw new InvalidParamException("该权限模块不存在");
        }
        if(permissionModuleMapper.findModuleByParentId(id) > 0){
            throw new InvalidParamException("该权限模块存在子模块，无法删除");
        }
        permissionModuleMapper.deleteByPrimaryKey(id);
        return new ResultStatus();
    }

    private String getLevel(Long parentId) {
        PermissionModule permissionModule = permissionModuleMapper.selectByPrimaryKey(parentId);
        return permissionModule == null ? null : permissionModule.getLevel();
    }

    private PermissionModule buildDO4Update(PermissionModuleRequest request) {
        return PermissionModule.builder()
                .id(request.getId())
                .name(request.getName())
                .parentId(request.getParentId())
                .seq(request.getSeq())
                .status(request.getStatus())
                .remark(request.getRemark())
                .updateTime(new Date())
                .build();
    }

    private PermissionModule buildDO4Add(PermissionModuleRequest request) {
        return PermissionModule.builder()
                .name(request.getName())
                .parentId(request.getParentId())
                .seq(request.getSeq())
                .status(request.getStatus())
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
    private Boolean checkExist(Long parentId, String name, Long id) {
        return permissionModuleMapper.findModuleByNameAndParentId(parentId,name,id) > 0;
    }
}
