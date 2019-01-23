package com.hmwl.service;

import com.alibaba.fastjson.JSONObject;
import com.hmwl.pojo.Dept;

import java.util.List;

public interface DeptService {
    //查询所有部门
     List<Dept> getDepts();
    //返回json的查询方式
    JSONObject getAllUsersJson();
    //查询当前一共有多少个部门
    Integer countDept();
}
