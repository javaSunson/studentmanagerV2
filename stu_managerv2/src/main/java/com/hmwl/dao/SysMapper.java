package com.hmwl.dao;

import com.hmwl.pojo.Sys_Auth;
import com.hmwl.pojo.Sys_Role;
import com.hmwl.pojo.Sys_User;

import java.util.List;

public interface SysMapper {
    //根据用户名查找用户
    Sys_User findUserByName(String username);
    //查询所有的权限菜单
    List<Sys_Auth> getAllAuths();
    //查询用户所具有的所有角色
    List<Sys_Role> getaAllRolesByUser(String username);
    //查询当前用户具有的所有权限
    List<Sys_Auth> getAllAuthsByUser(String username);
    //查询当前是不是可用
    Integer checkUserValid(String username);
}
