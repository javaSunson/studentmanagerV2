package com.hmwl.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hmwl.dao.Stu_ScoreMapper;
import com.hmwl.pojo.Stu_Score;
import com.hmwl.service.Stu_ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class Stu_ScoreServiceImpl implements Stu_ScoreService {
    @Autowired
    private Stu_ScoreMapper scoreMapper;


    @Override
    public JSONObject getAllScore() {
            List<Stu_Score> scores = scoreMapper.getAllStuScore();
            String s = JSON.toJSONString(scores);
            JSONArray array = JSONArray.parseArray(s);
            JSONObject obj = new JSONObject();
            obj.put("data",array);
            obj.put("code",0);
            obj.put("msg","ok");
            obj.put("count",score_Count());
            return obj;

    }

    @Override
    public Integer score_Count() {
        return scoreMapper.score_Count();
    }

    @Override
    public Integer checkScoreExist(String num) {
        Stu_Score stu_score = scoreMapper.checkScoreExist(num);
        if(stu_score != null){
            return 1;
        }
        return 0;
    }

    @Override
    public Integer insertScore(Stu_Score stu_score) {
        System.out.println("<<<<<<<<<<service层被调用++++++即将传入的数据:"+stu_score);
        Integer res = scoreMapper.insertScore(stu_score);
        return res;
    }

    @Override
    public Integer insertScoresBetch() {
        return null;
    }

    @Override
    public Integer insertScoresByInfo() {
        return null;
    }

    @Override
    public Integer deleteScoreSingle(String stu_num) {
        Integer res = scoreMapper.deleteScoreSingle(stu_num);
        return res;
    }

    @Override
    public Integer changeScore(Stu_Score stu_score) {
        Integer res = scoreMapper.changeScore(stu_score);
        return res;
    }

    @Override
    public Integer batchInsertScore(List<Stu_Score> stu_scores) {
        Integer res = scoreMapper.batchInsertScore(stu_scores);
        return res;
    }
}
