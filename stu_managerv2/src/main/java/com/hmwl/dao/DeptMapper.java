package com.hmwl.dao;

import com.hmwl.pojo.Dept;

import java.util.List;

public interface DeptMapper {
    //查询所有部门
    List<Dept> getDepts();
    //查询当前一共有多少个部门
    Integer countDept();
}
