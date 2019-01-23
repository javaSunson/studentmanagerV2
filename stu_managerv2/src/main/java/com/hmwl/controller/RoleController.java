package com.hmwl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hmwl.pojo.Sys_Auth;
import com.hmwl.pojo.Sys_Role;
import com.hmwl.service.RoleService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(value = "RoleController",description = "角色控制器,RBAC模型-shiro实现的核心")

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    /*****跳转控制器****/
    //跳转到add_newrole
    @RequestMapping("add_newrole")
    public String add_NewRole(){
        return "add_newrole";
    }
    //跳转到角色修改
    @RequestMapping("/role/changeinfo")
    public String roleChangeInfo(){
        return "alert_existrole";
    }
    //跳转到角色授权
    @RequestMapping("/grant_newrole")
    public ModelAndView grant_newrole(HttpServletRequest request){
        String id = request.getParameter("role_id");
        Integer role_id = -1;
        if(id != null){
            role_id = Integer.parseInt(id);
        }
        ModelAndView mv = new ModelAndView("grant_newrole");
        mv.addObject("role_id",role_id);
        return mv;
    }
    /*********/

    @RequestMapping(value = "/role/list",produces = "text/html;charset=utf-8")
    @ResponseBody
    public ModelAndView getJsonAuths(){
        List<Sys_Role> allRoles = roleService.getAllRoles();
//        String data = JSON.toJSONString(allRoles);
//        ModelAndView mv = new ModelAndView("admin-role");
        ModelAndView mv = new ModelAndView("role_managerv2");
        System.out.println(allRoles);
        mv.addObject("roles",allRoles);
        return mv;
    }
    @RequestMapping(value = "/getJsonRoles",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getJsonRoles(){
        return roleService.getJsonRoles();
    }

    @RequestMapping(value = "/getJsonFormat")
    @ResponseBody
    public String getJsonFormat(){
        List<Map<Sys_Role, List<Sys_Auth>>> chang = roleService.getRolesAndAuths();
        String s = JSON.toJSONString(chang);
//        Integer count = roleService.countRoles();
//        JSONArray array = JSONArray.parseArray(s);
        JSONObject obj = new JSONObject();
        obj.put("data",s);
        System.err.println(obj.toString());
        return obj.toJSONString();
    }

    //第二种实现对角色管理的方案
    @RequestMapping("/role/rolev2")
    @ResponseBody
    public String rolev2(){
        JSONObject obj = roleService.getAllRolesReturnJson();
        return obj.toJSONString();
    }

    //增加角色的方法
    @RequestMapping("/role/addrole")
    @ResponseBody
    public String addRole(Sys_Role sys_role){
        if(sys_role == null){
            return "error";
        }
        Integer res = roleService.addRole(sys_role);
        return res>0?"ok":"error";
    }

    //判断角色名是否存在.
    @RequestMapping("/role/checkExist")
    @ResponseBody
    public String isExistRole(HttpServletRequest request){
//        Integer role_id = Integer.parseInt(request.getParameter("role_id"));//通常没问题
        String role_id = request.getParameter("role_id");
        if(role_id == null){
            return "error";
        }
        Integer id = Integer.parseInt(role_id);
        Integer res = roleService.checkRole(id);
        return  res > 0 ?"error":"ok"; //存在就返回一个error
    }
    @RequiresPermissions("删除角色")
    //删除当前角色  **敏感操作，需要进行权限控制*
    @RequestMapping("/role/del")
    @ResponseBody
    public String delRole(HttpServletRequest request){
        String str = request.getParameter("role_id");
        if(str == null){
            return "error";
        }
        Integer role_id= Integer.parseInt(str);
        Integer res = roleService.deleteRole(role_id);
        return (res!=null&&res>0)?"ok":"error";//三目运算符
    }
    //修改角色信息
    @RequiresPermissions("编辑角色")
    @RequestMapping("/role/changerole")
    @ResponseBody
    public String changeRole(Sys_Role role){
        if(role == null){
            return "error";
        }
        Integer res = roleService.updateRole(role);
        return (res!=null&&res>0)?"ok":"error";//三目运算符
    }
    //禁用或者启用角色信息
    @RequestMapping("/role/disOrEn")
    @ResponseBody
    public String disOrEnabledRole(Sys_Role role){
        System.err.println("");
        if(role == null){
            return "error";
        }
        Integer res = roleService.disableRole(role);
        return (res!=null&&res>0)?"ok":"error";//三目运算符
    }
}
