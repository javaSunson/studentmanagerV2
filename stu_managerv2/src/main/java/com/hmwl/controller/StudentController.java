package com.hmwl.controller;


import com.alibaba.fastjson.JSONObject;
import com.hmwl.model.Model;
import com.hmwl.model.ScoreModel;
import com.hmwl.pojo.Stu_Info;
import com.hmwl.pojo.Stu_Score;
import com.hmwl.service.Stu_InfoService;
import com.hmwl.service.Stu_ScoreService;
import com.sargeraswang.util.ExcelUtil.ExcelLogs;
import com.sargeraswang.util.ExcelUtil.ExcelUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Api(value = "StudentController",
        description =
                "学生信息的CRUD"+
                "学生成绩的CRUD"+
                "以及一些批量操作"
)
@Controller
public class StudentController {
    @Autowired
    private Stu_InfoService infoService;
    @Autowired
    private Stu_ScoreService scoreService;
    //跳转到查询所有学生信息的页面
    @RequestMapping(value = "/stu/infos",produces = "text/html;charset=utf-8")
    public String toStuInfoPage(){
        return "student-info";
    }
    //回传数据的方法
    @RequestMapping(value = "/stu/getJsonData",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getJsonInfoData(){
        JSONObject allStudent = infoService.getAllStudent();
        System.out.println(">>>>>>>>>>>>>>>>>查看学生信息<<<<<<<<<<<<<<<<<<<<<<");
        return allStudent.toJSONString();
    }
    //查询成绩的方法
    //跳转到查询所有学生信息的页面
    @RequestMapping(value = "/scores/show",produces = "text/html;charset=utf-8")
    public String toStuscoresPage(){
        return "student-score-show";
    }
    //回传数据的方法
    @RequestMapping(value = "/scores/getScoreJsonData",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getJsonScoreData(){
        JSONObject scores = scoreService.getAllScore();
        System.out.println(scores);
        System.out.println(">>>>>>>>>>>>>>>>>查看学生成绩<<<<<<<<<<<<<<<<<<<<<<");
        return scores.toJSONString();
    }
    @RequestMapping("/stu/insert")
    @ResponseBody
    public String insertStuInfo(Stu_Info info){
        System.out.println("数据+"+info+"###################");
        Integer res = infoService.insertStuInfo(info);
        if(res == null || res == 0){
            return "error";
        }else{
            return "ok";
        }
    }
    @RequestMapping("/stu/check")
    @ResponseBody
    public String checkNum(HttpServletRequest req){
        String stu_num = req.getParameter("stu_num");
//        System.out.println(stu_num+"~~~~~~~~~~~~~~~~~~~~~");
        Integer res = infoService.checkNumExist(stu_num);
        System.out.println(res);
        if(res==null ||  res ==0){
            return "ok";//没查到所有能够插入
        }else{
            return "error";
        }
    }

    //删除学生信息
    @RequestMapping("/info/delTools")
    @ResponseBody
    public String deleteSingleInfo(HttpServletRequest request){
        String stu_num = request.getParameter("stu_num");//其实应该是删除具体的id,但是考虑到,需要控制学号唯一,可以这样写
        Integer res = infoService.deleteInfoByStuNum(stu_num);
        if(stu_num == null || res ==0){
            return "error";
        }else{
            return "ok";
        }
    }
    //修改学生信息
    @RequestMapping("/stu/changeStudentInfo")
    @ResponseBody
    public String changeStuInfo(Stu_Info stu_info){
        Integer res = infoService.changeStudentInfo(stu_info);
        if(res != null && res > 0){
            return "ok";
        }else{
            return "error";
        }
    }
    //录入学生成绩
    @RequestMapping("/score/insertTools")
    @ResponseBody
    public String alertScoreTools(Stu_Score stu_score) {
        System.out.println(">>>>>>>>>>>>>>>正在录入学生成绩,前端传过来的数据:" + stu_score);
        Float sum = 0.0F;
        Float ave = 0.0F;
        sum = stu_score.getScore1()+stu_score.getScore2()+stu_score.getScore3()+stu_score.getScore4()+
        stu_score.getScore5()+stu_score.getScore6()+stu_score.getScore7()+stu_score.getScore8();
        ave = sum / 8.0F;
        stu_score.setScoresum(sum);
        stu_score.setScoreave(ave);
        Integer res = scoreService.insertScore(stu_score);
        if (res != null || res != 0) {
            return "ok";
        } else {
            return "error";
        }
    }
    //检测学生信息重复
    @RequestMapping("/score/checkScoreExist")
    @ResponseBody
    public String checkExistScore(HttpServletRequest req){
        String stu_num = req.getParameter("stu_num");
        if(stu_num == null){
            return "error";
        }
        Integer res = scoreService.checkScoreExist(stu_num);
        if(res>0){
            return "ok";
        }else{
            return "error";
        }
    }
    //删除学生成绩
    @RequestMapping("/score/delTools")
    @ResponseBody
    public String deleteSingleScore(HttpServletRequest request){
        String stu_num = request.getParameter("stu_num");
        if(stu_num == null ){
            return "error";
        }
        Integer res = scoreService.deleteScoreSingle(stu_num);
        if(res>0){
            return "ok";
        }else{
            return "error";
        }
    }

    //修改学生成绩的方法
    @RequestMapping("/score/changeScore")
    @ResponseBody
    public String changeStuInfo(Stu_Score stu_score){
        if(stu_score == null){
            return "error";
        }
        stu_score.setScoresum(stu_score.getScore1() + stu_score.getScore2() + stu_score.getScore3() + stu_score.getScore4() +
                stu_score.getScore5() + stu_score.getScore6() + stu_score.getScore7() + stu_score.getScore8());
        stu_score.setScoreave(stu_score.getScoresum());
        Integer res = scoreService.changeScore(stu_score);
        if(res > 0 ){
            return "ok";
        }else{
            return "error";
        }
    }
    //批量处理信息
    @RequestMapping("/stu/infoBetchDual")
    @ResponseBody
    public String infoBetchDual(HttpServletRequest request){
        String filename = request.getParameter("filename");
        System.out.println("-----》数据文件："+filename);
//        File file = new File("C:\\Users\\Martin\\Desktop\\student.xlsx");
        File file = new File(filename);
        FileInputStream in = null;
        try{
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ExcelLogs logs = new ExcelLogs();
        if(in == null){
            return "error"; //如果file 对象 ，直接返回error
        }
        List<Stu_Info> infos = new ArrayList<>();
        Collection<Model> importExcel = ExcelUtil.importExcel(Model.class, in, "yyyy/MM/dd HH:mm:ss", logs, 0);
        for (Model m : importExcel){
            System.out.println(m);
            Stu_Info stu_info = new Stu_Info(
                    0,m.getStu_num(),
                    m.getStu_name(),
                    m.getStu_idcard(),
                    m.getStu_address(),
                    m.getStu_phone(),
                    m.getStu_img()
            );
            infos.add(stu_info);
        }
        Integer res = infoService.batchInsertStudent(infos);
        if(res == null || res == 0){
            System.out.println("批量导入失败!");
            return "error";
        }else{
            System.out.println("批量导入完成!");
            return "ok";
        }
    }

    //批量处理成绩
    @RequestMapping("/stu/scoreBetchDual")
    @ResponseBody
    public String scoreBetchDual(HttpServletRequest request){
        String filename = request.getParameter("filename");
        System.out.println("-----》数据文件："+filename);
//        File file = new File("C:\\Users\\Martin\\Desktop\\student.xlsx");
        File file = new File(filename);
        FileInputStream in = null;
        try{
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ExcelLogs logs = new ExcelLogs();
        if(in == null){
            return "error"; //如果file 对象 ，直接返回error
        }
        List<Stu_Score> scores = new ArrayList<>();
        Collection<ScoreModel> importExcel = ExcelUtil.importExcel(ScoreModel.class, in, "yyyy/MM/dd HH:mm:ss", logs, 0);
        for (ScoreModel m : importExcel){
            System.out.println(m);
            Float sum = Float.parseFloat(m.getScore1())
                    +Float.parseFloat(m.getScore2())+
                    Float.parseFloat(m.getScore3())+
                    Float.parseFloat(m.getScore4())+
                    Float.parseFloat(m.getScore5())+
                    Float.parseFloat(m.getScore6()) +
                    Float.parseFloat(m.getScore7())+
                    Float.parseFloat(m.getScore8());
            Float ave = sum / 8;
            Stu_Score stu_score = new Stu_Score(
                    0,m.getStu_num(),m.getStu_name(),
                            Float.parseFloat(m.getScore1()),
                            Float.parseFloat(m.getScore2()),
                            Float.parseFloat(m.getScore3()),
                            Float.parseFloat(m.getScore4()),
                            Float.parseFloat(m.getScore5()),
                            Float.parseFloat(m.getScore6()),
                            Float.parseFloat(m.getScore7()),
                            Float.parseFloat(m.getScore8()),
                    sum,ave);
            scores.add(stu_score);
        }
        Integer res = scoreService.batchInsertScore(scores);
        if(res == null || res == 0){
            System.out.println("批量导入失败!");
            return "error";
        }else{
            System.out.println("批量导入完成!");
            return "ok";
        }
    }

    @RequestMapping("fileImport")
    @ResponseBody
    public String fileName(HttpServletRequest request){
        String subFile = request.getParameter("filePath");
        System.out.println(subFile);
        return subFile!=null?"ok":"error";
    }


}