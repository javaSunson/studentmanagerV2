package com.hmwl.util;

import com.hmwl.model.Model;
import com.sargeraswang.util.ExcelUtil.ExcelLogs;
import com.sargeraswang.util.ExcelUtil.ExcelUtil;
import lombok.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Map;


public class EasyTest {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\Martin\\Desktop\\student.xlsx");
        FileInputStream in = new FileInputStream(file);
        ExcelLogs logs = new ExcelLogs();
        Collection<Model> importExcel = ExcelUtil.importExcel(Model.class, in, "yyyy/MM/dd HH:mm:ss", logs, 0);
        for (Model m : importExcel){
            System.out.println(m);
        }
    }
}
