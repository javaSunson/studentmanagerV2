package com.hmwl.dao;

import com.hmwl.pojo.Sys_Auth;
import com.hmwl.pojo.Sys_Role_Auth;


import java.util.List;
import java.util.Map;

public interface AuthMapper {
    //查询所有权限
    List<Map<String,Object>> getAllAuths();
    //统计权限个数
    Integer countAuth();
    //统计权限个数，但是按照role_id过滤
    Integer countAuthFilterRole(Integer role_ID);
    //添加权限
    Integer addAuth(Sys_Auth auth);
    //修改权限
    Integer editAuth(Sys_Auth auth);
    //启用或者禁用权限
    Integer toggleAuth(Map map);
    //启用或者禁用权限通过角色
    Integer toggleAuthByRole(Map map);
    //根据角色id查询它所具有的权限
    Map<Integer,String> getAuthByRoleID(Integer Role_ID);

    Sys_Auth getSingleAuthByRoleID(Integer Auth_ID);

    //查询所有权限通过用户过滤
    List<Map<String,Object>> getAllAuthsFilterAuth(Integer role_ID);

    List<Sys_Role_Auth> getRoleAuthBYRoleID(Integer role_ID);
    //给角色增加权限
    Integer addRoleAuth(Map map);
}
