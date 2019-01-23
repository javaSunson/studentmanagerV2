package com.hmwl.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hmwl.dao.RoleMapper;
import com.hmwl.pojo.RolePower;
import com.hmwl.pojo.Stu_Info;
import com.hmwl.pojo.Sys_Auth;
import com.hmwl.pojo.Sys_Role;
import com.hmwl.service.AuthService;
import com.hmwl.service.RoleService;
import io.swagger.annotations.Api;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Service
public class RoleSeviceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AuthService authService;

    @Override
    public List<Sys_Role> getAllRoles() {
        List<Sys_Role> allRoles = roleMapper.getAllRoles();
        return allRoles;
    }

    //下边这种方法给角色授权使用
//    @Override
    public String getJsonRoles() {
        List<Sys_Role> roles = getAllRoles();
        Map<Sys_Role, Object> map = new LinkedHashMap<>();
        for (Sys_Role role : roles) {
            Integer role_id = role.getRole_id();
            Map<Integer, String> rmap = authService.getAuthByRoleID(role_id);
            map.put(role, rmap);
        }
        return JSON.toJSONString(map);
    }

    @Override
    public List<RolePower> getRole_Auth() {
        return roleMapper.getRole_Auth();
    }

    @Override
    public List<Map<Sys_Role, List<Sys_Auth>>> getRolesAndAuths() {
////        先查询全部的角色,可以直接调用
//        List<Sys_Role> roles = roleMapper.getAllRoles();
//        Map<Sys_Role, List<Sys_Auth>> maps = new HashMap<>();
//        for (Sys_Role role : roles) {
//            Integer role_id = role.getRole_id();
//            List<Sys_Auth> auths = authService.getAuthByRoleIDReturnList(role_id);
//            maps.put(role, auths);
//        }
//        List<Map<Sys_Role, List<Sys_Auth>>> lists = new ArrayList<>();
//        lists.add(maps);
////        System.out.println(lists);
//        return lists;
        return null;  //这个方法,在layer页面不好展示,废弃
    }

    @Override
    public Integer countRoles() {
        return roleMapper.countRoles();
    }
    //角色管理v2
    @Override
    public JSONObject getAllRolesReturnJson() {
        List<Sys_Role> allRoles = getAllRoles();
        String s = JSON.toJSONString(allRoles);
        Integer count = countRoles();
        JSONArray array = JSONArray.parseArray(s);
        JSONObject obj = new JSONObject();
        obj.put("data", array);
        obj.put("code", 0);
        obj.put("msg", "ok");
        obj.put("count", count);
        return obj;
    }

    @Override
    public Integer addRole(Sys_Role sys_role) {
        return  roleMapper.addRole(sys_role);
    }

    @Override
    public Integer checkRole(Integer role_id) {
        return roleMapper.checkRole(role_id);
    }

    @Override
    public Integer deleteRole(Integer role_id) {
        return roleMapper.deleteRole(role_id);
    }

    @Override
    public Integer updateRole(Sys_Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public Integer disableRole(Sys_Role role) {
        return roleMapper.disableRole(role);
    }

}
