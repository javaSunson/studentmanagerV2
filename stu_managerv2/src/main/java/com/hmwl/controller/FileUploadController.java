package com.hmwl.controller;

import com.hmwl.model.Model;
import com.hmwl.util.FileUtil;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
@Api(value = "FileUploadController",description = "文件上传控制器,本来想使用EasyExcel,但是使用过程中有些问题,为了" +
        "excel-util 1.2.1")
public class FileUploadController {

    @RequestMapping(value = "/tool/upload",method = RequestMethod.POST)
//    @ResponseBody
    public ModelAndView uploadFile(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        String contentType = file.getContentType(); //获取文件类型
        String filename = file.getOriginalFilename(); //图片名字
        System.out.println("获取到用户名了"+filename);
//        String filePath = path+"/static/upload";
//        String filePath = "G:\\2019JanTerm\\数据\\信息\\";
        File filePath= null;
        try {
            filePath = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File upload=new File(filePath.getAbsolutePath(),"static/upload/");
        System.out.println("当前文件路径:"+upload);
        String absolutePath = upload.getAbsolutePath();
        try{
            FileUtil.uploadFile(file.getBytes(),absolutePath,filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("redirect:/stu/infoBetchDual");
        mv.addObject("filename",absolutePath+filename);
        return mv;
    }
    @RequestMapping(value = "/tool/uploadScore",method = RequestMethod.POST)
//    @ResponseBody
    public ModelAndView uploadScore(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        String contentType = file.getContentType(); //获取文件类型
        String filename = file.getOriginalFilename(); //图片名字
        System.out.println("获取到文件名了"+filename);

//        String filePath = path+"/static/upload";
//        String filePath = "G:\\2019JanTerm\\数据\\信息\\";
        File filePath= null;
        try {
            filePath = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File upload=new File(filePath.getAbsolutePath(),"static/upload/");
        System.out.println("当前文件路径:"+upload);
        String absolutePath = upload.getAbsolutePath();
        try{
            FileUtil.uploadFile(file.getBytes(),absolutePath,filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("redirect:/stu/scoreBetchDual");
        mv.addObject("filename",absolutePath+filename);
        return mv;
    }
}
