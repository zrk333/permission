package com.zrk.service.impl;

import com.zrk.dao.DepartmentMapper;
import com.zrk.exception.InvalidParamException;
import com.zrk.model.Department;
import com.zrk.model.web.ResultStatus;
import com.zrk.request.DepartmentRequest;
import com.zrk.service.DepartmentService;
import com.zrk.util.LevelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
        if(checkExist(request.getParentId(),request.getName(),request.getId())){
            throw new InvalidParamException("该层级下已存在该部门名称");
        }
        Department department = buildDO4Add(request);
        department.setLevel(LevelUtil.calculateLevel(getLevel(request.getParentId()),request.getParentId()));
        // TODO
        department.setCreateUserId(0);
        department.setUpdateUserId(0);
        departmentMapper.insertSelective(department);
        return new ResultStatus();
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
     * 校验统一层级下是否存在该部门
     * @param parentId
     * @param name
     * @param id
     * @return
     */
    private Boolean checkExist(Long parentId, String name, Long id) {
        return departmentMapper.findDepartmentByNameAndParentId(parentId,name,id) > 0;
    }
}
