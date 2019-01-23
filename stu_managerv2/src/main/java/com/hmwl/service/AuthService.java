package com.hmwl.service;

import com.alibaba.fastjson.JSONObject;
import com.hmwl.pojo.Sys_Auth;
import com.hmwl.pojo.Sys_Role_Auth;

import java.util.List;
import java.util.Map;

public interface AuthService {
    //查询所有权限
    JSONObject getAllAuths();
    //查询所有权限通过角色进行过滤
    JSONObject getAllAuthsFilterAuth(Integer role_ID);
    //添加或者修改权限
    boolean addOrUpdateAuth(Sys_Auth auth);
    //开启或者关闭权限
    Integer toggleAuth(Map map);
    //开启或者关闭权限byRole_Id
    //开启或者关闭权限
    Integer toggleAuthByRole(Map map);

    //统计权限个数，按照角色过滤
    Integer countAuthFilterRole(Integer role_ID);

    //根据角色id查询它所拥有的角色
    Map<Integer,String> getAuthByRoleID(Integer Role_Id);
    //根据权限id,查询权限
    Sys_Auth getAuthByAuthID(Integer Role_ID);
    //角色赋权v2
    JSONObject getAllAuthsByRole(Integer role_ID);
    //查询关系表,根据role_id查询到auths
    List<Sys_Role_Auth> getRoleAuthBYRoleID(Integer role_id);
    //给角色增加权限
    Integer addRoleAuth(Map map);

}
