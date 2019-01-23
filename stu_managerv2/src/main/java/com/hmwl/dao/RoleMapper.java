package com.hmwl.dao;

import com.hmwl.pojo.RolePower;
import com.hmwl.pojo.Sys_Role;

import java.util.List;

public interface RoleMapper {
    //查询当前所有的角色信息
//    JSONObjectObject getAllAuths();
    List<Sys_Role> getAllRoles();
    //查询所有角色们,给角色赋权使用
//    List<Map<String,Object>> getJSonRoles();

    //查询角色对应的权限
    List<RolePower> getRole_Auth();
    //统计当前有多少用户
    Integer countRoles();

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
