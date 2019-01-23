package com.hmwl.controller;

import com.hmwl.pojo.Dept;
import com.hmwl.service.DeptService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Api(value = "DeptController",description = "部门管理")
@Controller
public class DeptController {
    @Autowired
    private DeptService deptService;
    //跳转到部门页面
    @RequestMapping("/dept/list")
    public String dept(){
        return "dept_manager";
    }
    //查询所有的部门和主要成员
    @RequestMapping("/dept/show")
    @ResponseBody
    public ModelAndView getDepts(){
        List<Dept> depts = deptService.getDepts();
        ModelAndView mv = new ModelAndView("admin-dept");
        mv.addObject("depts",depts);
        return mv;
    }
    //查询所有的部门和成员,返回json格式,方便前台渲染
    @RequestMapping("/dept/getJsonDept")
    @ResponseBody
    public String getJsonDept(){
        return deptService.getAllUsersJson().toJSONString();
    }
}
