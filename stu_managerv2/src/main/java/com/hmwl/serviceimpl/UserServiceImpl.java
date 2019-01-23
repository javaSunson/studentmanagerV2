package com.hmwl.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hmwl.dao.UserMapper;
import com.hmwl.pojo.Sys_Role;
import com.hmwl.pojo.Sys_User;
import com.hmwl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Sys_User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public JSONObject getAllUsersJson() {
        List<Sys_User> allUsers = userMapper.getAllUsers();
        System.out.println("❀❀❀❀❀打印下获取到的用户:"+allUsers);
        String s = JSON.toJSONString(allUsers);
        JSONArray array = JSONArray.parseArray(s);
        JSONObject obj = new JSONObject();
        obj.put("data",array);
        obj.put("code",0);
        obj.put("msg","ok");
        System.out.println("❀❀❀❀❀当前数据库中有:"+countUser()+"个用户");
        obj.put("count",countUser());
        return obj;
    }

    @Override
    public Integer countUser() {
        return userMapper.countUser();
    }

    @Override
    public Integer addUser(Sys_User sys_user) {
        return userMapper.addUser(sys_user);
    }

    @Override
    public Integer delUser(Integer user_id) {
        return userMapper.delUser(user_id);
    }

    @Override
    public Integer toggleUser(Map map) {
        return userMapper.toggleUser(map);
    }

    @Override
    public Integer changeUser(Map map) {
        return userMapper.changeUser(map);
    }

    @Override
    public Integer countRoleByUserID(Integer user_id) {
        return userMapper.countRoleByUserID(user_id);
    }

    @Override
    public JSONObject getJsonRolesByUserID(Integer user_id) {
        List<Sys_Role> lists = userMapper.getUserRole(user_id);
        String s = JSON.toJSONString(lists);
        JSONArray array = JSONArray.parseArray(s);
        JSONObject obj = new JSONObject();
        obj.put("data",array);
        obj.put("code",0);
        obj.put("msg","ok");
        obj.put("count",countRoleByUserID(user_id));
        return obj;

    }

    @Override
    public Integer delUserRoleByRoleID(Integer role_id) {
        return userMapper.delUserRoleByRoleID(role_id);
    }

    @Override
    public Integer addUserRole(Map map) {
        return userMapper.addUserRole(map);
    }
}
