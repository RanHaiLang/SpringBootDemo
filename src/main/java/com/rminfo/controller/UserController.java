package com.rminfo.controller;


import com.rminfo.annotation.Log;
import com.rminfo.enums.StatusEnums;
import com.rminfo.exception.GlobalException;
import com.rminfo.model.shiro1.*;
import com.rminfo.service.shiro.DeptService;
import com.rminfo.service.shiro.MenuService;
import com.rminfo.service.shiro.RoleService;
import com.rminfo.service.shiro.UserService;
import com.rminfo.util.QueryPage;
import com.rminfo.util.ResponseCode;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/25
 * Time: 14:00
 * 项目名：springboot
 * 描述：TODO
 * Description: No Description
 */
@RestController
@RequestMapping("/system/user")
@Api(value = "UserController", tags = {"用户管理模块接口"})
public class UserController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DeptService deptService;


    @GetMapping("/info")
    public ResponseCode info() {
        User user = super.getCurrentUser();
        logger.info("user={}", user.toString());

        //获取用户角色
        List<Role> roleList = roleService.findUserRole(user.getUsername());
        Set<String> roleSet = roleList.stream().map(Role::getName).collect(Collectors.toSet());

        //获取用户权限
        List<Menu> menuList = menuService.findUserPerms(user.getUsername());
        Set<String> permSet = menuList.stream().map(Menu::getPerms).collect(Collectors.toSet());

        //获取用户部门
        Dept dept = deptService.findById(user.getDeptId());

        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        map.put("avatar", user.getAvatar());
        map.put("phone", user.getPhone());
        map.put("sex", user.getSex());
        map.put("roles", roleSet);
        map.put("perms", permSet);
        map.put("dept", dept.getName());
        map.put("description", user.getDescription());
        return ResponseCode.success(map);
    }

    @GetMapping("/getMenus")
    public ResponseCode getMenus(String username) {
        return ResponseCode.success(userService.getMenus(username));
    }


    @PostMapping("/list")
    public ResponseCode queryList(QueryPage queryPage, User user) {
        return ResponseCode.success(
                super.selectByPageNumSize(queryPage, () ->
                        userService.queryList(user))
        );
    }

    @Log("添加用户")
    @PostMapping("/add")
    @RequiresPermissions("user:add")
    public ResponseCode add(@RequestBody UserWithRole user) {
        try {
            userService.add(user);
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
        if (!userService.checkName(name, id)) {
            return new ResponseCode(StatusEnums.PARAM_REPEAT);
        }
        return ResponseCode.success();
    }

    @GetMapping("findById")
    public ResponseCode findById(Long id) {
        return ResponseCode.success(userService.findById(id));
    }

    @Log("更新用户")
    @PostMapping("/update")
    @RequiresPermissions("user:update")
    public ResponseCode update(@RequestBody UserWithRole user) {
        try {
            userService.update(user);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @Log("删除用户")
    @PostMapping("/delete")
    @RequiresPermissions("user:delete")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            userService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

}
