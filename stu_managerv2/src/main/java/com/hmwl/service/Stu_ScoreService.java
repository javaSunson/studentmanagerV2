package com.hmwl.service;

import com.alibaba.fastjson.JSONObject;
import com.hmwl.pojo.Stu_Score;

import java.util.List;

public interface Stu_ScoreService {
    //按条件成绩
    JSONObject getAllScore();
    //查询总共的条数
    Integer score_Count();
    //校验成绩是否存在
    Integer checkScoreExist(String num);
    //录入一条成绩
    Integer insertScore(Stu_Score stu_score);
    //批量录入成绩
    Integer insertScoresBetch();
    //根据已有信息进行成绩录入
    Integer insertScoresByInfo();
    //删除一条成绩记录
    Integer deleteScoreSingle(String stu_num);
    //修改一条学生成绩
    Integer changeScore(Stu_Score stu_score);
    //批量录入学生成绩
    Integer batchInsertScore(List<Stu_Score> stu_scores);
}
