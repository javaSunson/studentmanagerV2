package com.hmwl.model;

import com.sargeraswang.util.ExcelUtil.ExcelCell;
import lombok.*;

@Setter@Getter@ToString@NoArgsConstructor@AllArgsConstructor
public class ScoreModel {
    @ExcelCell(index = 0)
    private String stu_num;
    @ExcelCell(index = 1)
    private String stu_name;
    @ExcelCell(index = 2)
    private String score1;
    @ExcelCell(index = 3)
    private String score2;
    @ExcelCell(index = 4)
    private String score3;
    @ExcelCell(index = 5)
    private String score4;
    @ExcelCell(index = 6)
    private String score5;
    @ExcelCell(index = 7)
    private String score6;
    @ExcelCell(index = 8)
    private String score7;
    @ExcelCell(index = 9)
    private String score8;


}
