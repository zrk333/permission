package com.zrk.model.web;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/8
 */
@Data
public class PageResult<T> implements Serializable{

    private List<T> data = new ArrayList<T>();

    private Integer total = 0;
}
