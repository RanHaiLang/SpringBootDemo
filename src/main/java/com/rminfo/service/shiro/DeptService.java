package com.rminfo.service.shiro;


import com.rminfo.model.shiro1.Dept;
import com.rminfo.model.shiro1.Tree;

import java.util.List;

/**
 * @author tycoding
 * @date 2019-02-02
 */
public interface DeptService extends BaseService<Dept> {

    List<Tree<Dept>> tree();

    List<Dept> queryList(Dept dept);

    Dept findById(Long id);

    void add(Dept dept);

    boolean checkName(String name, String id);

    void update(Dept dept);

    void delete(List<Long> ids);
}
