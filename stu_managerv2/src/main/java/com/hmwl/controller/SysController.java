package com.hmwl.controller;

import com.hmwl.pojo.Sys_Auth;
import com.hmwl.service.SysService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
@Api(value = "SysController",description = "用户登录授权等操作")
@Controller
public class SysController {
    @Autowired
    private SysService sysService;
    @RequestMapping("/userLogin")
    public ModelAndView userLogin(String username, String password, HttpSession session){
        System.out.println(username+"____"+password);
        ModelAndView modelAndView = new ModelAndView("forward:toIndex");
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            Integer valid  = sysService.checkUserValid(username);
            System.out.println("❀❀❀❀❀❀查询当前账户的可用性"+valid);
            if(valid == 1){
                subject.logout();
                modelAndView.addObject("msg","您的账户被冻结,请联系管理员");
                modelAndView.setViewName("/login");
                return modelAndView;
            }
            session.setAttribute("username",username);
        }catch (UnknownAccountException e){
            modelAndView.addObject("msg","账户不存在");
            modelAndView.addObject("preName",username);
            modelAndView.setViewName("/login");
        }catch (IncorrectCredentialsException e){
            modelAndView.addObject("msg","密码错误");
            modelAndView.setViewName("/login");
        }
        return modelAndView;
    }
    @RequestMapping("/toIndex")
    public ModelAndView toIndex(){
        ModelAndView mv = new ModelAndView("index");
        Map<Sys_Auth, List<Sys_Auth>> list = sysService.getAllAuths();
        mv.addObject("auths",list);
        return mv;
    }
    @RequestMapping("/userLogout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
