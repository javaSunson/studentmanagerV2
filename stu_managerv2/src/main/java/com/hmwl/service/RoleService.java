package com.hmwl.service;

import com.alibaba.fastjson.JSONObject;
import com.hmwl.pojo.RolePower;
import com.hmwl.pojo.Sys_Auth;
import com.hmwl.pojo.Sys_Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    //获取所有的角色信息
    List<Sys_Role> getAllRoles();
    //获取角色信息们
    String getJsonRoles();

    //查询角色对应的权限
    List<RolePower> getRole_Auth();

    //另一种方式查询和保留角色对应的权限信息
    List<Map<Sys_Role, List<Sys_Auth>>> getRolesAndAuths();
    //统计当前有多少用户
    Integer countRoles();

    public JSONObject getAllRolesReturnJson();

    //增加角色
    Integer addRole(Sys_Role sys_role);
    //校验角色是否存在
    Integer checkRole(Integer role_id);
    //删除角色信息
    Integer deleteRole(Integer role_id);
    //修改角色信息
    Integer updateRole(Sys_Role role);
    //禁用/启用权限
    Integer disableRole(Sys_Role role);

}
