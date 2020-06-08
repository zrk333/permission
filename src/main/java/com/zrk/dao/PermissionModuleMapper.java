package com.zrk.dao;

import com.zrk.model.PermissionModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionModuleMapper {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(PermissionModule record);

    int insertSelective(PermissionModule record);

    PermissionModule selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(PermissionModule record);

    int updateByPrimaryKey(PermissionModule record);

    /**
     * 根据名称和父id校验是否存在该权限模块
     * @param parentId
     * @param name
     * @param id
     * @return
     */
    Integer findModuleByNameAndParentId(@Param("parentId") Long parentId, @Param("name") String name, @Param("id") Long id);

    /**
     * 获取子层级权限模块
     * @param levelPrefix
     * @return
     */
    List<PermissionModule> getChildModuleByLevel(@Param("levelPrefix") String levelPrefix);

    /**
     * 批量更新层级
     * @param permissionModuleList
     */
    void batchUpdateLevel(@Param("list") List<PermissionModule> permissionModuleList);
}