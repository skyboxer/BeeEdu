package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.CommonReturnValue;
import com.enablue.util.IfOs;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * @author cn_xjk
 * 上传文件
 */
@RestController
@RequestMapping("uploadFileController")
public class UploadFileController{
    @Autowired
    private CommonReturnValue commonReturnValue;

    @RequestMapping("uploadFileImg")
    public JSONObject uploadFileImg(MultipartFile file,HttpServletRequest request) {
        //String path = IfOs.ifOsPath("E:\\image","/home/data/ROOT1/image") ;
        String path = IfOs.ifOsResourceValue("exploit.download.img.path","server.download.img.path","config/global");
        int code = -1;
        String msg = "上传出错";
        if(file.isEmpty()){
            return commonReturnValue.CommonReturnValue(code,msg);
        }
        String dataTime = String.valueOf(System.currentTimeMillis());
        String fileName = dataTime+file.getOriginalFilename();
        File filePath = new File(path, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }
        // 写入文件
        try {
            file.transferTo(filePath);
            code = 0;
            msg = "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commonReturnValue.CommonReturnValue(code,msg,fileName);
    }

    public static void main(String[] args) {
        String s = String.valueOf(System.currentTimeMillis());
        System.out.println(s);
    }
}
