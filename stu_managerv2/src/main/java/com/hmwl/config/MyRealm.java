package com.hmwl.config;

import com.hmwl.pojo.Sys_Auth;
import com.hmwl.pojo.Sys_Role;
import com.hmwl.pojo.Sys_User;
import com.hmwl.service.SysService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private SysService sysService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("----遇到标签了,进行权限验证----");
        Sys_User user = (Sys_User) principalCollection.getPrimaryPrincipal();
        List<Sys_Role> roles = sysService.getaAllRolesByUser(user.getUsername());
        Set<String> allRolesNames = new HashSet<>();
        for (Sys_Role role : roles){
            allRolesNames.add(role.getRole_name());
        }
        List<Sys_Auth> userByName = sysService.getAllAuthsByUser(user.getUsername());
        Set<String> allAuthNames = new HashSet<>();
        for (Sys_Auth auth : userByName){
            allAuthNames.add(auth.getAuth_name());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(allRolesNames);
        info.addStringPermissions(allAuthNames);
        System.err.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println(allRolesNames);
        System.out.println(allAuthNames);
        System.err.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.err.println("--------------------doGetAuthenticationInfo------------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        //根据用户名查询用户信息,得到用户信息以后,可能会得到一个user对象
        Sys_User user = sysService.findUserByName(username);
        if(user == null){
            return null;
        }
//        System.out.println("1:"+user+"2:"+password+"3:"+getAuthenticationCache());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        return info;
    }
}
