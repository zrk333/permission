package com.zrk.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.zrk.dao.DepartmentMapper;
import com.zrk.dao.PermissionModuleMapper;
import com.zrk.dao.PermissionsMapper;
import com.zrk.dto.DepartmentDTO;
import com.zrk.dto.PermissionModuleDTO;
import com.zrk.dto.PermissionsDTO;
import com.zrk.exception.ResultEmptyException;
import com.zrk.model.Department;
import com.zrk.model.PermissionModule;
import com.zrk.model.Permissions;
import com.zrk.model.web.ResultStatus;
import com.zrk.service.CoreService;
import com.zrk.service.TreeService;
import com.zrk.util.LevelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
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

    @Resource
    private PermissionModuleMapper permissionModuleMapper;

    @Resource
    private PermissionsMapper permissionsMapper;

    @Resource
    private CoreService coreService;

    @Override
    public ResultStatus getRoleTree(Long roleId) {
        //获取当前用户已分配的权限
        List<Permissions> userPermissionList = coreService.getCurrentUserPermissionList();
        //获取当前角色已分配的权限
        List<Permissions> rolePermissionList = coreService.getRolePermissionList(roleId);
        //获取当前系统所有的权限
        List<Permissions> allPermissionList = permissionsMapper.getAllPermissions();
        if(CollectionUtils.isEmpty(allPermissionList)){
            throw new ResultEmptyException();
        }
        Set<Long> userPermIdSet = userPermissionList.stream().map(Permissions::getId).collect(Collectors.toSet());
        Set<Long> rolePermIdSet = rolePermissionList.stream().map(Permissions::getId).collect(Collectors.toSet());
        List<PermissionsDTO> allPermissionsDTOList = new ArrayList<>();
        allPermissionList.forEach(p -> {
            PermissionsDTO permissionsDTO = convertPermissionsDOToDTO(p);
            if(userPermIdSet.contains(p.getId())){
                permissionsDTO.setHasPerm(true);
            }
            if(rolePermIdSet.contains(p.getId())){
                permissionsDTO.setChecked(true);
            }
            allPermissionsDTOList.add(permissionsDTO);
        });
        List<PermissionModuleDTO> permissionModuleDTOList = transformRolePermissionTree(allPermissionsDTOList);
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setData(permissionModuleDTOList);
        return resultStatus;
    }

    private List<PermissionModuleDTO> transformRolePermissionTree(List<PermissionsDTO> allPermissionsDTOList) {
        if(CollectionUtils.isEmpty(allPermissionsDTOList)){
            return new ArrayList<>();
        }
        List<PermissionModuleDTO> permissionModuleTree = getPermissionModuleTreeF();
        Multimap<Long,PermissionsDTO> moduleId2PermissionMap = ArrayListMultimap.create();
        allPermissionsDTOList.stream().filter(p -> p.getStatus() == 1).forEach(p -> moduleId2PermissionMap.put(p.getPermModuleId(),p));
        bindPermToModule(permissionModuleTree,moduleId2PermissionMap);
        return permissionModuleTree;
    }

    private void bindPermToModule(List<PermissionModuleDTO> permissionModuleDTOList, Multimap<Long, PermissionsDTO> moduleId2PermissionMap) {
        if(CollectionUtils.isEmpty(permissionModuleDTOList)){
            return;
        }
        for(PermissionModuleDTO permissionModule : permissionModuleDTOList){
            List<PermissionsDTO> permissionsDTOList = (List<PermissionsDTO>) moduleId2PermissionMap.get(permissionModule.getId());
            if(!CollectionUtils.isEmpty(permissionsDTOList)){
                Collections.sort(permissionsDTOList,permissionsDTOComparable);
                permissionModule.setPermissionList(permissionsDTOList);
            }
            bindPermToModule(permissionModule.getPermissionModuleList(),moduleId2PermissionMap);
        }
    }

    @Override
    public ResultStatus getDepartmentTree() {
        List<Department> departmentList = departmentMapper.getAllDepartment();
        if(CollectionUtils.isEmpty(departmentList)){
            log.info("获取部门树为空");
        }
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        departmentList.forEach(d -> departmentDTOList.add(convertDepartmentDOToDTO(d)));
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

    @Override
    public ResultStatus getPermissionModuleTree() {
        List<PermissionModuleDTO> permissionModuleTree = getPermissionModuleTreeF();
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.setData(permissionModuleTree);
        return resultStatus;
    }

    private List<PermissionModuleDTO> getPermissionModuleTreeF() {
        List<PermissionModule> permissionModuleList = permissionModuleMapper.getAllModule();
        if(CollectionUtils.isEmpty(permissionModuleList)){
            log.info("获取权限模块树为空");
            return new ArrayList<>();
        }
        List<PermissionModuleDTO> permissionModuleDTOList = new ArrayList<>();
        permissionModuleList.forEach(p -> permissionModuleDTOList.add(convertPermissionModuleDOToDTO(p)));
        return makePermissionModuleTree(permissionModuleDTOList);
    }

    /**
     * 生成权限模块树
     * @param permissionModuleDTOList
     * @return
     */
    private List<PermissionModuleDTO> makePermissionModuleTree(List<PermissionModuleDTO> permissionModuleDTOList) {
        Multimap<String,PermissionModuleDTO> level2PermissionModuleMap = ArrayListMultimap.create();
        permissionModuleDTOList.forEach(p -> level2PermissionModuleMap.put(p.getLevel(),p));
        //取出第一层级
        List<PermissionModuleDTO> rootPermissionModuleList = permissionModuleDTOList.stream().filter(p -> LevelUtil.ROOT.equals(p.getLevel())).collect(Collectors.toList());
        //排序 按照seq升序
        Collections.sort(rootPermissionModuleList, permissionModuleDTOComparator);
        //生成树
        transformPermissionModuleTree(rootPermissionModuleList,LevelUtil.ROOT,level2PermissionModuleMap);
        return rootPermissionModuleList;
    }

    /**
     * 转化为树
     * @param rootPermissionModuleList
     * @param level
     * @param level2PermissionModuleMap
     */
    private void transformPermissionModuleTree(List<PermissionModuleDTO> rootPermissionModuleList, String level, Multimap<String, PermissionModuleDTO> level2PermissionModuleMap) {
        rootPermissionModuleList.forEach(p -> transformPermissionModuleTreeF(p,level,level2PermissionModuleMap));
    }

    private void transformPermissionModuleTreeF(PermissionModuleDTO permissionModuleDTO, String level, Multimap<String, PermissionModuleDTO> level2PermissionModuleMap) {
        String parentLevel = LevelUtil.calculateLevel(level,permissionModuleDTO.getId());
        List<PermissionModuleDTO> permissionModuleDTOList = (List<PermissionModuleDTO>)level2PermissionModuleMap.get(parentLevel);
        if(!CollectionUtils.isEmpty(permissionModuleDTOList)){
            //排序
            Collections.sort(permissionModuleDTOList, permissionModuleDTOComparator);
            //设置子层级
            permissionModuleDTO.setPermissionModuleList(permissionModuleDTOList);
            //递归处理子层级
            transformPermissionModuleTree(permissionModuleDTOList,parentLevel,level2PermissionModuleMap);
        }
    }

    private PermissionsDTO convertPermissionsDOToDTO(Permissions permissions) {
        PermissionsDTO permissionsDTO = new PermissionsDTO();
        BeanUtils.copyProperties(permissions,permissionsDTO);
        return permissionsDTO;
    }

    private PermissionModuleDTO convertPermissionModuleDOToDTO(PermissionModule permissionModule) {
        PermissionModuleDTO permissionModuleDTO = new PermissionModuleDTO();
        BeanUtils.copyProperties(permissionModule,permissionModuleDTO);
        return permissionModuleDTO;
    }

    private DepartmentDTO convertDepartmentDOToDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(department,departmentDTO);
        return departmentDTO;
    }

    Comparator<PermissionModuleDTO> permissionModuleDTOComparator = new Comparator<PermissionModuleDTO>() {
        @Override
        public int compare(PermissionModuleDTO o1, PermissionModuleDTO o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    Comparator<DepartmentDTO> departmentDTOComparator = new Comparator<DepartmentDTO>() {
        @Override
        public int compare(DepartmentDTO o1, DepartmentDTO o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    Comparator<PermissionsDTO> permissionsDTOComparable = new Comparator<PermissionsDTO>() {
        @Override
        public int compare(PermissionsDTO o1, PermissionsDTO o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };
}
