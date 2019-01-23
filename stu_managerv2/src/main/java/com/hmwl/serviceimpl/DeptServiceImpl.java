package com.hmwl.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hmwl.dao.DeptMapper;
import com.hmwl.pojo.Dept;
import com.hmwl.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> getDepts() {
        return deptMapper.getDepts();
    }

    @Override
    public JSONObject getAllUsersJson() {
        List<Dept> depts = deptMapper.getDepts();
        System.out.println("❀❀❀❀❀打印下获取到的部门:"+depts);
        String s = JSON.toJSONString(depts);
        JSONArray array = JSONArray.parseArray(s);
        JSONObject obj = new JSONObject();
        obj.put("data",array);
        obj.put("code",0);
        obj.put("msg","ok");
        System.out.println("❀❀❀❀❀当前数据库中有:"+countDept()+"个用户");
        obj.put("count",countDept());
        return obj;
    }

    @Override
    public Integer countDept() {
        return deptMapper.countDept();
    }
}
