package com.hmwl.util;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {
    public static void uploadFile(byte [] file,String filePath,String fileName) throws Exception{
        System.out.println("文件大小:"+file.length);
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        System.out.println("已经执行!");
        out.flush();
        out.close();
    }
}
