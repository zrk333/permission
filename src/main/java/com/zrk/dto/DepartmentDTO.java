package com.zrk.dto;

import com.zrk.model.Department;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/6
 */
@Data
public class DepartmentDTO extends Department {

    /**
     * 子部门
     */
    private List<DepartmentDTO> departmentList;
}
