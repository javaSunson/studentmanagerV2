package com.hmwl.service;

import com.hmwl.pojo.Sys_Auth;
import com.hmwl.pojo.Sys_Role;
import com.hmwl.pojo.Sys_User;

import java.util.List;
import java.util.Map;

public interface SysService {
    Sys_User findUserByName(String username);

    //查询菜单
    Map<Sys_Auth,List<Sys_Auth>> getAllAuths();
    List<Sys_Role> getaAllRolesByUser(String username);
    //查询当前用户具有的所有权限
    List<Sys_Auth> getAllAuthsByUser(String username);
    //查询当前是不是可用
    Integer checkUserValid(String username);
}
