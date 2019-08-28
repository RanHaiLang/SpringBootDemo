package com.rminfo.controller;

import com.rminfo.annotation.Log;
import com.rminfo.enums.StatusEnums;
import com.rminfo.exception.GlobalException;
import com.rminfo.model.shiro1.Dept;
import com.rminfo.service.shiro.DeptService;
import com.rminfo.util.QueryPage;
import com.rminfo.util.ResponseCode;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SeaRan
 * Date: 2019/7/8
 * Time: 15:28
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
@RestController
@RequestMapping("/system/dept")
@Api(value = "DeptController", tags = {"部门管理模块接口"})
public class DeptController extends BaseController{

    @Autowired
    private DeptService deptService;

    @PostMapping("/list")
    public ResponseCode queryList(QueryPage queryPage, Dept dept) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> deptService.queryList(dept)));
    }

    @GetMapping("/tree")
    public ResponseCode tree() {
        return ResponseCode.success(deptService.tree());
    }

    @GetMapping("/findById")
    public ResponseCode findById(Long id) {
        return ResponseCode.success(deptService.findById(id));
    }

    @Log("添加部门")
    @PostMapping("/add")
    @RequiresPermissions("dept:add")
    public ResponseCode add(@RequestBody Dept dept) {
        try {
            deptService.add(dept);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @GetMapping("/checkName")
    public ResponseCode checkName(String name, String id) {
        if (name.isEmpty()) {
            return new ResponseCode(StatusEnums.PARAM_ERROR);
        }
        if (!deptService.checkName(name, id)) {
            return new ResponseCode(StatusEnums.PARAM_REPEAT);
        }
        return ResponseCode.success();
    }

    @Log("更新部门")
    @PostMapping("update")
    @RequiresPermissions("dept:update")
    public ResponseCode update(@RequestBody Dept dept) {
        try {
            deptService.update(dept);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @Log("删除部门")
    @PostMapping("/delete")
    @RequiresPermissions("dept:delete")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            deptService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
