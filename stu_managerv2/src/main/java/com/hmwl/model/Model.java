package com.hmwl.model;

import com.sargeraswang.util.ExcelUtil.ExcelCell;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Model {
    @ExcelCell(index = 0)
    private String stu_num;
    @ExcelCell(index = 1)
    private String stu_name;
    @ExcelCell(index = 2)
    private String stu_idcard;
    @ExcelCell(index = 3)
    private String stu_address;
    @ExcelCell(index = 4)
    private String stu_phone;
    @ExcelCell(index = 5)
    private String stu_img;



}