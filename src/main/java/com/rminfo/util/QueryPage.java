package com.rminfo.util;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author tycoding
 * @date 2019-01-20
 */
@Data
@ToString
public class QueryPage implements Serializable {

    private int pageCode; //当前页
    private int pageSize; //每页显示的记录数

    public int getPageCode() {
        return pageCode;
    }

    public void setPageCode(int pageCode) {
        this.pageCode = pageCode;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
