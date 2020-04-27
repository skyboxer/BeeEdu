package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.CommonReturnValue;
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
    public void uploadFileImg(MultipartHttpServletRequest request) {
        //String savePath = "/home/data/ROOT1/image";
        String savePath = "E://image";
        File file = new File(savePath);
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("创建目录或文件");
            file.mkdir();
        }
        MultipartFile multipartFile = request.getFile("file");
        String fileName = multipartFile.getName()+new Date().toString();
        file.renameTo(new File(savePath + "/" + fileName));
        try {
         multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
