package com.zrk.request;

import lombok.Data;

/**
 * @Description: 分页参数
 * @Author: zrk
 * @Date: 2020/5/28
 */
@Data
public class PageRequest {

    public PageRequest(){
        this.page = 1;
        this.pageSize = 10;
    }

    /**
     * 页面大小
     */
    private Integer pageSize;

    /**
     * 当面页码
     */
    private Integer page;

    public Integer getFrom(){
        return (this.page > 0 ? this.page - 1 : 0) * this.pageSize;
    }

    public Integer getCurrentPage(){
        return this.page > 0 ? this.page - 1 : 0;
    }
}
