package com.fmjava.core.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


public class pageResult implements Serializable {
    private Long total;//总页数
    private List pageList;//当前页集合

    public pageResult(Long total, List pageList) {
        this.total = total;
        this.pageList = pageList;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getPageList() {
        return pageList;
    }

    public void setPageList(List pageList) {
        this.pageList = pageList;
    }
}
