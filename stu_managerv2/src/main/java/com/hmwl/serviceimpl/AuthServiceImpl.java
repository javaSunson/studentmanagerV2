package com.hmwl.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hmwl.dao.AuthMapper;
import com.hmwl.pojo.Sys_Auth;
import com.hmwl.pojo.Sys_Role_Auth;
import com.hmwl.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;
    @Override
    public JSONObject getAllAuths() {
        List<Map<String, Object>> list = authMapper.getAllAuths();
        for (Map<String,Object> map:list) {
            Integer type = (Integer) map.get("auth_type");
            map.put("auth_type",type==0?"菜单":"资源");
            Integer valid = (Integer) map.get("is_valid");
            map.put("is_valid",valid == 0?"启用":"禁用");
        }
        String s = JSON.toJSONString(list);
        Integer count = authMapper.countAuth();
        JSONArray array = JSONArray.parseArray(s);
        JSONObject obj = new JSONObject();
        obj.put("data",array);
        obj.put("code",0);
        obj.put("msg","ok");
        obj.put("count",count);
        System.out.println("解析格式:===="+obj.toJSONString());
        return obj;
    }

    @Override
    public JSONObject getAllAuthsFilterAuth(Integer role_ID) {
        List<Sys_Role_Auth> sra = getRoleAuthBYRoleID(role_ID);
        System.out.println("❀❀❀❀❀❀❀❀❀❀❀"+sra+"❀❀❀❀❀❀❀❀❀❀❀❀❀❀❀");

//        Integer auth_id = sra.get(0).getAuth_id();
        List<Map<String, Object>> list = new ArrayList<>();
        for (Sys_Role_Auth temp:sra) {
            Integer auth_id = temp.getAuth_id();
            list.addAll(authMapper.getAllAuthsFilterAuth(auth_id));   //将每次查询的结果追加到当前结果集中
        }

        String s = JSON.toJSONString(list);
//        Integer count = authMapper.countAuth();
        Integer count = countAuthFilterRole(role_ID);
        JSONArray array = JSONArray.parseArray(s);
        JSONObject obj = new JSONObject();
        obj.put("data",array);
        obj.put("code",0);
        obj.put("msg","ok");
        obj.put("count",count);
        System.out.println("解析格式:===="+obj.toJSONString());
        return obj;
    }

    ///添加或者修改权限
    @Override
    public boolean addOrUpdateAuth(Sys_Auth auth) {
        if(auth.getAuth_id() == null){
            //添加
            Integer addRes = authMapper.addAuth(auth);
            return addRes == 1;
        }else{
            //修改
            Integer editRes = authMapper.editAuth(auth);
            return editRes == 1;
        }
    }

    @Override
    public Integer toggleAuth(Map map) {
        return authMapper.toggleAuth(map);
    }

    @Override
    public Integer toggleAuthByRole(Map map) {
        return authMapper.toggleAuthByRole(map);
    }

    @Override
    public Integer countAuthFilterRole(Integer role_ID) {
        return authMapper.countAuthFilterRole(role_ID);
    }

    @Override
    public Map<Integer, String> getAuthByRoleID(Integer Role_Id) {
        return authMapper.getAuthByRoleID(Role_Id);
    }

    @Override
    public Sys_Auth getAuthByAuthID(Integer Auth_ID) {
        return authMapper.getSingleAuthByRoleID(Auth_ID);
    }

    @Override
    public JSONObject getAllAuthsByRole(Integer role_ID) {
        //先查询对应的role_auth
        List<Sys_Role_Auth> roles = getRoleAuthBYRoleID(role_ID);
        //遍历求得的所有auth_id ,求出所有的auths
        List<Sys_Auth> lists = new ArrayList<>();
        for (Sys_Role_Auth sra:roles) {
            Integer auth_id = sra.getAuth_id();
            Sys_Auth auth =  getAuthByAuthID(auth_id);
            lists.add(auth);
        }
        String s = JSON.toJSONString(lists);
        Integer count = lists.size();
        JSONArray array = JSONArray.parseArray(s);
        JSONObject obj = new JSONObject();
        obj.put("data",array);
        obj.put("code",0);
        obj.put("msg","ok");
        obj.put("count",count);
        System.out.println("解析格式:===="+obj.toJSONString());
        return obj;
    }

    @Override
    public List<Sys_Role_Auth> getRoleAuthBYRoleID(Integer role_id) {
        return authMapper.getRoleAuthBYRoleID(role_id);
    }

    @Override
    public Integer addRoleAuth(Map map) {
        return authMapper.addRoleAuth(map);
    }
}
