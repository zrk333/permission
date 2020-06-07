package com.zrk.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.zrk.dao.DepartmentMapper;
import com.zrk.dto.DepartmentDTO;
import com.zrk.model.Department;
import com.zrk.model.web.ResultStatus;
import com.zrk.service.TreeService;
import com.zrk.util.LevelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/6
 */
@Slf4j
@Service
public class TreeServiceImpl implements TreeService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public ResultStatus getDepartmentTree() {
        List<Department> departmentList = departmentMapper.getAllDepartment();
        if(CollectionUtils.isEmpty(departmentList)){
            log.info("获取部门树为空");
        }
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        departmentList.forEach(d -> departmentDTOList.add(convertDOToDTO(d)));
        List<DepartmentDTO> departmentTree = makeDepartmentTree(departmentDTOList);
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setData(departmentTree);
        return resultStatus;
    }

    /**
     * 生成部门树
     * @param departmentDTOList
     * @return
     */
    private List<DepartmentDTO> makeDepartmentTree(List<DepartmentDTO> departmentDTOList) {
        Multimap<String,DepartmentDTO> level2DepartmentMap = ArrayListMultimap.create();
        departmentDTOList.forEach(d -> level2DepartmentMap.put(d.getLevel(),d));
        //取出第一层级部门
        List<DepartmentDTO> rootDepartmentList = departmentDTOList.stream().filter( d -> LevelUtil.ROOT.equals(d.getLevel())).collect(Collectors.toList());
        //排序 按照seq升序
        Collections.sort(rootDepartmentList, departmentDTOComparator);
        //生成部门树
        transformDepartmentTree(rootDepartmentList,LevelUtil.ROOT,level2DepartmentMap);
        return rootDepartmentList;
    }

    /**
     * 转化为部门树
     * @param rootDepartmentList
     * @param level
     * @param level2DepartmentMap
     */
    private void transformDepartmentTree(List<DepartmentDTO> rootDepartmentList, String level, Multimap<String, DepartmentDTO> level2DepartmentMap) {
        rootDepartmentList.forEach(d -> transformDepartmentTreeF(d,level,level2DepartmentMap));
    }

    private void transformDepartmentTreeF(DepartmentDTO departmentDTO, String level, Multimap<String, DepartmentDTO> level2DepartmentMap) {
        String parentLevel = LevelUtil.calculateLevel(level,departmentDTO.getId());
        List<DepartmentDTO> departmentDTOList = (List<DepartmentDTO>)level2DepartmentMap.get(parentLevel);
        if(!CollectionUtils.isEmpty(departmentDTOList)){
            //排序
            Collections.sort(departmentDTOList, departmentDTOComparator);
            //设置子部门
            departmentDTO.setDepartmentList(departmentDTOList);
            //递归处理子部门
            transformDepartmentTree(departmentDTOList,parentLevel,level2DepartmentMap);
        }
    }

    Comparator<DepartmentDTO> departmentDTOComparator = new Comparator<DepartmentDTO>() {
        @Override
        public int compare(DepartmentDTO o1, DepartmentDTO o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    private DepartmentDTO convertDOToDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(department,departmentDTO);
        return departmentDTO;
    }
}
