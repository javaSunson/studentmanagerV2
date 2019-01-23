package com.hmwl.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;

@Component
public class MyCredentialMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //用户输入的密码
        String pwd1 = new String(token.getPassword());
        Md5Hash hash = new Md5Hash(pwd1,token.getUsername(),3);
        String s = hash.toString();

        //查询出来的
        Object credentials = authenticationInfo.getCredentials();
        return this.equals(s,credentials);
    }
}
