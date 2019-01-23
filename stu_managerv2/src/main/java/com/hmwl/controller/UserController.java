package com.hmwl.controller;

import com.alibaba.fastjson.JSONObject;
import com.hmwl.pojo.Sys_User;
import com.hmwl.service.UserService;
import com.hmwl.util.Shiro_Utils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "UserController",description = "查询所有用户等操作")
//这里主要写用户操作相关的代码
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/user/list")
    public String toUserList(){
//        return "sys_user";
        return "sys_user_V2";
    }
    @RequestMapping("/user/showlist")
    @ResponseBody
    public ModelAndView getAllUsers(){
        List<Sys_User> allUsers = userService.getAllUsers();
        ModelAndView mv = new ModelAndView("admin-user");
        mv.addObject("users", allUsers);
        System.err.println("----->查询所有账户"+allUsers);
        return mv;
    }
    @RequestMapping("/user/getJsonUser")
    @ResponseBody
    public String getJsonUser(){
        JSONObject allUsersJson = userService.getAllUsersJson();
        return allUsersJson.toJSONString();
    }
    @RequestMapping("/add_user_ajax")
    @ResponseBody
    public String addUser(String username,String password,
                          String sex,Integer age,String email,
                          String phone,String dept,String is_valid
    ){
        System.out.println("❀❀❀❀❀❀接收到的参数为：❀❀❀❀❀❀❀❀"+
                username+"-"+password+"-"+sex+"-"+age+"-"+email+"-"+
                phone+"-"+dept+"-"+is_valid);
        Integer valid = -1;
        if(is_valid.equals("on")){
            valid = 0;
        }else{
            valid = 1;
        }
//        以用户的名称作为颜值加密,次数限定3
        String encodingPWD = Shiro_Utils.M5dEncoding(password,username,3);
        Sys_User user = new Sys_User(-1,username,encodingPWD,sex,age,email,phone,Integer.parseInt(dept),new Date(),valid);
        Integer res = userService.addUser(user);
        return res>0?"ok":"error";
    }
    //删除一个用户
    @RequestMapping("/user/delUser")
    @ResponseBody
    public String userDelUser(HttpServletRequest request){
        String user_id = request.getParameter("user_id");
        if(user_id == null){
            return "error";
        }
        Integer res = userService.delUser(Integer.parseInt(user_id));
        return res>0?"ok":"error";
    }
    @RequestMapping("/user/toggleUser")
    @ResponseBody
    //开关用户可用性
    public String  toggleUser(Integer user_id,String is_valid){
        System.out.println("❀❀❀❀❀❀❀❀❀❀接收到参数"+user_id+"-"+is_valid);
        Integer status = -1;
        if("on".equals(is_valid)||"0".equals(is_valid)){
            //当前是开启状态,所以要禁用
            status = 1;
        }else{
            status = 0;
        }
      Map<String,Object> map = new HashMap<>();
      map.put("user_id",user_id);
      map.put("is_valid",status);
      Integer res = userService.toggleUser(map);
      return res>0?"ok":"error";
    }
    @RequestMapping("/user/changeUser")
    @ResponseBody
    public String changeUser(Integer user_id,String username,String password,
                             Integer age,String sex,String email,String phone,String dept_id,String register_date,String is_valid){
        //当前有效性和时间没改,因为,layui在渲染的时候,重新渲染了 字段的值,用layui自带的data.filed参数是获取不到的,
        //想处理的话,需要手动去使用jq的form序列话等方式获取
        //其实是第一个页面没传值过来
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id",user_id);
        map.put("username",username);
        map.put("password",password);
        map.put("age",age);
        map.put("sex",sex);
        map.put("email",email);
        map.put("phone",phone);
        map.put("dept_id",dept_id);
        Integer res = userService.changeUser(map);
        return res>0?"ok":"error";
    }
    //跳转到用户授权页
    @RequestMapping("/user_newauth")
    public ModelAndView toUser_NewAuth(HttpServletRequest request){
        String user_id = request.getParameter("user_id");
        Integer id = -1;
        id = Integer.parseInt(user_id);
        System.out.println("❀❀❀❀❀当前获取到的user_id:"+user_id);
        ModelAndView mv = new ModelAndView("user_role_manager");
        mv.addObject("user_id", id);
        return mv;
    }
    //为用户授权

    @RequestMapping("/user/findRoleByUser")
    @ResponseBody
    public String findRoleByUser(HttpServletRequest request){
        String user_id = request.getParameter("user_id");
        System.out.println("❀❀❀❀❀前端传过来的用户编号:"+user_id);
        Integer id = -1;
        id = Integer.parseInt(user_id);
        JSONObject obj = userService.getJsonRolesByUserID(id);
        System.out.println("❀❀❀❀❀❀❀❀❀❀获取到角色信息了:"+obj.toJSONString());
        return obj.toJSONString();
    }
    //删除用户所具有的权限
    @RequestMapping("/user/delrole")
    @ResponseBody
    public String userDelrole(HttpServletRequest request){
        String role_id = request.getParameter("role_id");
        if(role_id == null){
            return "error";
        }
        Integer res = userService.delUserRoleByRoleID(Integer.parseInt(role_id));
        return res>0?"ok":"error";
    }
    @RequestMapping("/user/addNewRole")
    @ResponseBody
    public String addNewRole(HttpServletRequest request){
        String user_id = request.getParameter("user_id");
        String role_id = request.getParameter("role_id");
        System.out.println("❀❀❀❀❀接收到的用户id和角色id"+user_id+"-"+role_id);
        if(user_id == null || role_id == null){
            return "error";
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id",Integer.parseInt(user_id));
        map.put("role_id",Integer.parseInt(role_id));
        Integer res = userService.addUserRole(map);
        return res>0?"ok":"error";
    }
    @RequestMapping("/add_role2role")
    public ModelAndView add_role2role(HttpServletRequest request){
        String user_id = request.getParameter("user_id");
        ModelAndView mv = new ModelAndView("add_role2role");
        mv.addObject("user_id",user_id);
        return mv;
    }
}
