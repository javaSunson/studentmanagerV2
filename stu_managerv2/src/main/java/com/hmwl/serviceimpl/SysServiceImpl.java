package com.hmwl.serviceimpl;

import com.hmwl.dao.SysMapper;
import com.hmwl.pojo.Sys_Auth;
import com.hmwl.pojo.Sys_Role;
import com.hmwl.pojo.Sys_User;
import com.hmwl.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysServiceImpl implements SysService {
    @Autowired
    private SysMapper sysMapper;
    @Override
    public Sys_User findUserByName(String username) {
        Sys_User user = new Sys_User();
        user.setUsername(username);
        Sys_User loginUser = sysMapper.findUserByName(username);
        System.out.println(loginUser);
        return loginUser;
    }
    //菜单
    @Override
    public  Map<Sys_Auth,List<Sys_Auth>> getAllAuths() {
        List<Sys_Auth> list = sysMapper.getAllAuths();
        Map<Sys_Auth,List<Sys_Auth>> map = new LinkedHashMap<>();
        for (Sys_Auth auth:list) {
            if(auth.getAuth_layer() == 1){
                List<Sys_Auth> childrens = new ArrayList<>();
                for (Sys_Auth a:list){
                    if(a.getAuth_layer() == 2 && a.getParent_id() == auth.getAuth_id() ){
                        childrens.add(a);
                    }
                }
//
                map.put(auth,childrens);
            }
        }
        return map;
    }

    @Override
    public List<Sys_Role> getaAllRolesByUser(String username) {
        return sysMapper.getaAllRolesByUser(username);
    }

    @Override
    public List<Sys_Auth> getAllAuthsByUser(String username) {
        return sysMapper.getAllAuthsByUser(username);
    }

    @Override
    public Integer checkUserValid(String username) {
        return sysMapper.checkUserValid(username);
    }
}
