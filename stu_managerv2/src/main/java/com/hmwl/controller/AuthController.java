package com.hmwl.controller;

import com.hmwl.pojo.Sys_Auth;
import com.hmwl.service.AuthService;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@Api(value = "AuthController",description = "授权管理")
@Controller
public class AuthController {
    @Autowired
    private AuthService authService;
    //获取所有权限
    @RequestMapping(value = "/getJsonAuths",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getJsonAuths(){
        return authService.getAllAuths().toJSONString();
    }
    //获取指定role 的权限
    @RequestMapping(value = "/getJsonAuthsByRole",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getJsonAuthsByRole(HttpServletRequest request){
        String str = request.getParameter("role_id");
        System.out.println("----->---->----->"+str);
        Integer role_id = -1;
        if(str.length()>0){
            role_id = Integer.parseInt(str);
        }
        return authService.getAllAuthsFilterAuth(role_id).toJSONString();
    }
    //编辑权限
    @RequiresPermissions("编辑权限")
    @RequestMapping(value = "addOrUpdateAuth", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addOrUpdateAuth(Sys_Auth sys_auth){
        boolean res = authService.addOrUpdateAuth(sys_auth);
        return res?"ok":"error";
    }

    //启用和禁用权限
    @RequiresPermissions("编辑权限")
    @RequestMapping(value = "auth_toggle",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String auth_Toggle(HttpServletRequest req){
        String status = req.getParameter("valid");
        String auth_id = req.getParameter("auth_id");
        Integer valid = -1;
        if("启用".equals(status)){
            valid = 1;//如果当前是启用状态,赋值1,然后改变状态
        }else{
            valid = 0;////如果当前是禁用状态,赋值1,然后改变状态
        }
        Map<String, Object> map = new HashMap<>();
        map.put("auth_id",auth_id);
        map.put("is_valid",valid);
        System.err.println(map);
        Integer res = authService.toggleAuth(map);
        return res == 1 ? "ok":"error";
    }
    //角色的赋权和取消
    @RequiresPermissions("编辑权限")
    @RequestMapping(value = "role_auth_toggle",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String role_Auth_Toggle(HttpServletRequest req){
        String status = req.getParameter("valid");
        String auth_id = req.getParameter("auth_id");
        String role_id = req.getParameter("role_id");  //当前权限要被冻结,指定字段变0即可
        Integer valid = -1;
        if("启用".equals(status)){
            valid = 1;//如果当前是启用状态,赋值1,然后改变状态
        }else{
            valid = 0;////如果当前是禁用状态,赋值1,然后改变状态
        }
        Map<String, Object> map = new HashMap<>();
        map.put("auth_id",auth_id);
        map.put("is_valid",valid);
        map.put("auth_id",auth_id);
        System.err.println(map);
        Integer res = authService.toggleAuth(map);
        return res == 1 ? "ok":"error";
    }
    //给角色添加权限
    @RequestMapping("/role_add_auth")
    @ResponseBody
    public String role_add_auth(HttpServletRequest request){
        String role_id = request.getParameter("role_id");
        String auth_id = request.getParameter("auth_id");
        System.out.println("❀❀❀打印下获取的role_id和auth_id❀❀❀"+auth_id+role_id);
        if(role_id == null || auth_id == null){
            return "error";//两大皆空,返回一个error
        }
        Integer.parseInt(role_id);
        Integer.parseInt(auth_id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("role_id",role_id);
        map.put("auth_id",auth_id);
        Integer res = authService.addRoleAuth(map);
        return (res!=null&&res>0)?"ok":"error";
    }
}
