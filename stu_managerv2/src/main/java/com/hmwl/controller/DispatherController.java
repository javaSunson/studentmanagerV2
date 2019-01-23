package com.hmwl.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Api(value = "DispatchController",description = "用来分发请求")
@Controller
public class DispatherController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
    @RequiresPermissions("权限管理")
    @RequestMapping("/auth/list")
    public String authList(){
        return "admin-auth";
    }
//    @RequestMapping("/stu/infos")
//    public String stuInfos(){
//        return "student-info";
//    }
//    @RequestMapping("/role/list")
//    public String roleList(){
//        return "admin-role";
//    }     //这个地方出现在rolecontroller里边了
//    @RequestMapping("/user/list")
//    public String userList(){
//        return "admin-user";
//    }//这里直接写到user controller里边了
    @RequestMapping("/admin-edit")
    public String adminEdit(){
        //之前的edit没动.使用的复制出来的authedit
        return "admin-authedit";
    }
    @RequestMapping("/admin-authedit")
    public String authEdit(){
        return "admin-authedit";
    }
    @RequestMapping("/role/grant")
    public String toRoleGrant(){
        return "role-auth";
    }
    /**下面主要是跳转到学生管理的转发器*/
    @RequestMapping("/stu/input")
    public String toInputStudentPage(){
        return "input-stu-info";
    }
    @RequestMapping("/stu/alert")
    public String alertStudent(){
        return "student-info-alert";
    }
    @RequestMapping("/stu/del")
    public String delStudent(){
        return "student-info-del";
    }
    @RequestMapping("/score/input")
    public String inputScore(){
        return "input-stu-score";
    }
    //跳转到修改信息的页面
    @RequestMapping("/student-info-edit")
    public String StuInfoEdit(){
        return "student-info-edit";
    }
    //删除学生成绩
    @RequestMapping("/score/del")
    public String delScores(){
        return "student-score-del";
    }
    //修改学生成绩
    @RequestMapping("/score/alter")
    public String toAlertScores(){
        return "student-score-alert";
    }
    //跳转到修改的界面
    @RequestMapping("/student-score-edit")
    public String tostudent_score_edit(){
        return "student-score-edit";
    }
    //跳转到根据已存在的信息录入成绩
    @RequestMapping("score/inputbyinfo")
    public String toInputByInfoPage(){
        return "student-score_input_exist";
    }

    @RequestMapping("scoreinputbyinfo")
    public String scoreinputbyinfo(){
        return "score-input-byinfo";
    }
    @RequestMapping("/stu/betch")
    public String betchOpts(){
        return "importByExcel";
    }
    @RequestMapping("/score/betch")
    public String betchScore(){
        return "importScoreByExcel";
    }
    @RequestMapping("/test/upload")
    public String toUpload(){
        return "upload";
    }
    //跳转到为角色增加权限的操作界面
    @RequestMapping("/add-auth4role")
    public ModelAndView add_Auth4role(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("add-auth4role");
        String role_id = request.getParameter("role_id");
        System.out.println("❀❀❀❀❀❀❀当前role_id="+role_id+"❀❀❀❀❀❀❀❀❀❀");
        mv.addObject("role_id",role_id);
        return mv;
    }
    //跳转到修改用户的界面
    @RequestMapping("/sys_user_edit")
    public String toSys_User_Edit(){
        return "sys_user_edit";
    }
    //跳转到新增用户的界面
    @RequestMapping("/add-user-account")
    public String add_User_Account(){
        return "add_user";
    }

}
