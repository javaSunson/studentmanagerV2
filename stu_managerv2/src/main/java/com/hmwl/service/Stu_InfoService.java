package com.hmwl.service;

import com.alibaba.fastjson.JSONObject;
import com.hmwl.pojo.Stu_Info;

import java.util.List;

public interface Stu_InfoService {
    //按条件查询用户
    List<Stu_Info> getAllStuInfo();
    //返回json的查询方式
    JSONObject getAllStudent();
    //查询总共的条数
    Integer stu_Count();
    //查询是否有重复的学号
    Integer checkNumExist(String stu_num);
    //录入单条学生信息
    Integer insertStuInfo(Stu_Info info);
    //删除一条学生信息记录
    Integer deleteInfoByStuNum(String stu_num);
    //修改一条学生信息记录
    Integer changeStudentInfo(Stu_Info stu_info);
    //批量插入学生信息
    Integer batchInsertStudent(List<Stu_Info> list);
}
