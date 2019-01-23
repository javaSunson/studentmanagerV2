package com.hmwl.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hmwl.dao.Stu_InfoMapper;
import com.hmwl.pojo.Stu_Info;
import com.hmwl.service.Stu_InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Stu_InfoServiceImpl implements Stu_InfoService {
    @Autowired
    private Stu_InfoMapper stu_infoMapper;
    @Override
    public List<Stu_Info> getAllStuInfo() {
        List<Stu_Info> allStuInfo = stu_infoMapper.getAllStuInfo();
        return allStuInfo;
    }
    @Override
    public JSONObject getAllStudent() {
        List<Stu_Info> infos = stu_infoMapper.getAllStuInfo();
        String s = JSON.toJSONString(infos);
        JSONArray array = JSONArray.parseArray(s);
        JSONObject obj = new JSONObject();
        obj.put("data",array);
        obj.put("code",0);
        obj.put("msg","ok");
        obj.put("count",stu_Count());
        return obj;
    }

    @Override
    public Integer stu_Count() {
        Integer count = stu_infoMapper.stu_Count();
        return count;
    }

    @Override
    public Integer checkNumExist(String stu_num) {
        Integer result = stu_infoMapper.checkNumExist(stu_num);
        return result;
    }

    @Override
    public Integer insertStuInfo(Stu_Info info) {
        Integer result = stu_infoMapper.insertStuInfo(info);
        return result;
    }

    @Override
    public Integer deleteInfoByStuNum(String stu_num) {
        Integer res = stu_infoMapper.deleteInfoByStuNum(stu_num);
        return res;
    }

    @Override
    public Integer changeStudentInfo(Stu_Info stu_info) {
        Integer res = stu_infoMapper.changeStudentInfo(stu_info);
        return res;
    }

    @Override
    public Integer batchInsertStudent(List<Stu_Info> list) {
        Integer res = stu_infoMapper.batchInsertStudent(list);
        return res;
    }


}
