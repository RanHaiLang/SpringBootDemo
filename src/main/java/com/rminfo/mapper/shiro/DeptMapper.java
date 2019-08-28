package com.rminfo.mapper.shiro;


import com.rminfo.mapper.MyMapper;
import com.rminfo.model.shiro1.Dept;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-02-02
 */
public interface DeptMapper extends MyMapper<Dept> {

    void changeTopNode(List<Long> ids);
}
