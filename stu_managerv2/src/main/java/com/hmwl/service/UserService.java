package com.hmwl.service;

import com.alibaba.fastjson.JSONObject;
import com.hmwl.pojo.Sys_User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //查询所有用户
    List<Sys_User> getAllUsers();
    //返回json的查询方式
    JSONObject getAllUsersJson();
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
    //根据user_id查询有多少个角色
    Integer countRoleByUserID(Integer user_id);
    //获取角色信息们
    JSONObject getJsonRolesByUserID(Integer user_id);

    Integer delUserRoleByRoleID(Integer role_id);
    //为用户添加角色
    Integer addUserRole(Map map);
}
