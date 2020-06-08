package com.zrk.service.impl;

import com.zrk.dao.DepartmentMapper;
import com.zrk.exception.InvalidParamException;
import com.zrk.model.Department;
import com.zrk.model.web.ResultStatus;
import com.zrk.request.DepartmentRequest;
import com.zrk.service.DepartmentService;
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
 * @Date: 2020/6/6
 */
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public ResultStatus addDepartment(DepartmentRequest request) {
        if(checkExist(request.getParentId(),request.getName(),null)){
            throw new InvalidParamException("该层级下已存在该部门名称");
        }
        Department department = buildDO4Add(request);
        department.setLevel(LevelUtil.calculateLevel(getLevel(request.getParentId()),request.getParentId()));
        // TODO
        department.setCreateUserId(0L);
        department.setUpdateUserId(0L);
        departmentMapper.insertSelective(department);
        return new ResultStatus();
    }

    @Override
    public ResultStatus editDepartment(DepartmentRequest request) {
        if(checkExist(request.getParentId(),request.getName(),request.getId())){
            throw new InvalidParamException("该层级下已存在该部门名称");
        }
        Department departmentOld = departmentMapper.selectByPrimaryKey(request.getId());
        if(departmentOld == null){
            throw new InvalidParamException("未获取到待更新部门");
        }
        Department departmentNew = buildDO4Update(request);
        // TODO
        departmentNew.setUpdateUserId(0L);
        departmentNew.setLevel(LevelUtil.calculateLevel(getLevel(request.getParentId()),request.getParentId()));
        updateWithChild(departmentOld,departmentNew);
        return new ResultStatus();
    }

    @Override
    public ResultStatus deleteDepartment(Long id) {
        Department department = departmentMapper.selectByPrimaryKey(id);
        if(department == null){
            throw new InvalidParamException("该部门不存在");
        }
        if(departmentMapper.findDepartmentByParentId(id) > 0){
            throw new InvalidParamException("该部门存在子部门，无法删除");
        }
        departmentMapper.deleteByPrimaryKey(id);
        return new ResultStatus();
    }

    /**
     * 事务待测
     * @param departmentOld
     * @param departmentNew
     */
    @Transactional(rollbackFor = Exception.class)
    private void updateWithChild(Department departmentOld, Department departmentNew) {
        String levelPrefixOld = departmentOld.getLevel();
        String levelPrefixNew = departmentNew.getLevel();
        if(!levelPrefixOld.equals(levelPrefixNew)){
            List<Department> departmentList = departmentMapper.getChildDepartmentByLevel(levelPrefixOld);
            if(!CollectionUtils.isEmpty(departmentList)){
                departmentList.forEach(d -> changeLevel(d,levelPrefixOld,levelPrefixNew));
                departmentMapper.batchUpdateLevel(departmentList);
            }
        }
        departmentMapper.updateByPrimaryKey(departmentNew);
    }

    private void changeLevel(Department department, String levelPrefixOld, String levelPrefixNew) {
        String level = department.getLevel();
        if(StringUtils.isNotEmpty(level) && level.indexOf(levelPrefixOld) == 0){
            department.setLevel(levelPrefixNew + level.substring(levelPrefixOld.length()));
        }
    }

    /**
     * 获取level
     * @param id
     * @return
     */
    private String getLevel(Long id) {
        Department department = departmentMapper.selectByPrimaryKey(id);
        return department == null ? null : department.getLevel();
    }

    private Department buildDO4Update(DepartmentRequest request) {
        return Department.builder()
                .id(request.getId())
                .name(request.getName())
                .parentId(request.getParentId())
                .seq(request.getSeq())
                .remark(request.getRemark())
                .updateTime(new Date())
                .build();
    }

    private Department buildDO4Add(DepartmentRequest request) {
        return Department.builder()
                .name(request.getName())
                .parentId(request.getParentId())
                .seq(request.getSeq())
                .remark(request.getRemark())
                .createTime(new Date())
                .updateTime(new Date())
                .build();
    }

    /**
     * 校验同一层级下是否存在该部门
     * @param parentId
     * @param name
     * @param id
     * @return
     */
    private Boolean checkExist(Long parentId, String name, Long id) {
        return departmentMapper.findDepartmentByNameAndParentId(parentId,name,id) > 0;
    }
}
