package com.zrk.dao;

import com.zrk.model.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    /**
     * 根据名称和父id校验是否存在该部门
     * @param parentId
     * @param name
     * @param id
     * @return
     */
    Integer findDepartmentByNameAndParentId(@Param("parentId") Long parentId, @Param("name") String name, @Param("id") Long id);

    /**
     * 获取子层级部门
     * @param levelPrefix
     * @return
     */
    List<Department> getChildDepartmentByLevel(@Param("levelPrefix") String levelPrefix);

    /**
     * 批量更新层级
     * @param departmentList
     */
    void batchUpdateLevel(@Param("list") List<Department> departmentList);
}