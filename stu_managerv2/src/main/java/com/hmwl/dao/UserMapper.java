package com.hmwl.dao;

import com.alibaba.fastjson.JSONObject;
import com.hmwl.pojo.Sys_Role;
import com.hmwl.pojo.Sys_User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //查询所有用户
    List<Sys_User> getAllUsers();
    //根据部门id查询部门
    String getDept_Name();
    //查询有多少用户
    Integer countUser();
    //插入一条用户记录
    Integer addUser(Sys_User sys_user);
    //删除一条用户记录
    Integer delUser(Integer user_id);
    //开关用户可用性
    Integer toggleUser(Map map);
    //修改用户
    Integer changeUser(Map map);
    //根据user_id 查询用户所具有的权限
    List<Sys_Role> getUserRole(Integer user_id);
    //根据user_id查询有多少个角色
    Integer countRoleByUserID(Integer user_id);
    //根据role_id 删除sys_user_role中user对应的角色信息
    Integer delUserRoleByRoleID(Integer role_id);
    //为用户添加角色
    Integer addUserRole(Map map);
}
