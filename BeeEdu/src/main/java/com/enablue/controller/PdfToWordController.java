package com.enablue.controller;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * pdf转word
 */
@RestController
public class PdfToWordController {
    /*@RequestMapping("transcription/toWord")
    public HashMap<String,Object> toWord(String fileName){
        HashMap<String, Object> result = new HashMap<>();
        if (fileName==null ||fileName.equals("null")){
            result.put("code",-1);
            result.put("msg","文件名不能为空");
            return result;
        }
        // 获得request对象,response对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取上传文件路径
        String localFile =request.getServletContext().getRealPath("/upload");
        //设置要读取的文件
        String readFile = localFile+File.separator+ fileName;
        //设置文件前缀名
        String prefix=fileName.substring(0, fileName.lastIndexOf("."));
        //设置存放路径
        String storagePath = request.getServletContext().getRealPath("/result")+File.separator+prefix;
        //创建文件夹
        File file = new File(storagePath);
        if(!file.exists()){//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        System.out.println("file = " + file);

        Boolean aBoolean = PdfToWord(new File(readFile), storagePath);
        if (aBoolean){
            //设置压缩文件名
            String zipFile = prefix+".zip";
            System.out.println("zipFile = " + zipFile);
            zipMultiFile(storagePath,request.getServletContext().getRealPath("/result")+File.separator+zipFile,true);

            result.put("code", 1);
            result.put("msg","转换成功");
            result.put("filename",zipFile);
        }else {
            result.put("code", -1);
            result.put("msg","转换失败");
        }
        return result;
    }

    *//**
     * 获取PDF文件总页码数
     * @param PDFfile
     * @return
     * @throws IOException
     *//*
    public  int getNumberOfPages(File PDFfile) throws Exception {
        return  PDDocument.load(PDFfile).getNumberOfPages();
    }

    *//**
     * PDF转换Word
     * @param file
     * @param storagePath
     * @throws IOException
     *//*
    public  Boolean PdfToWord(File file, String storagePath){
        //获取文件前缀名
        String fileName = file.getName();
        System.out.println("fileName = " + fileName);
        String prefix = fileName.substring(0, fileName.lastIndexOf("."));
        System.out.println("prefix = " + prefix);

        PdfDocument pdf = new PdfDocument();
        PdfDocument tempPdf = null;
        //读取pdf文件
        pdf.loadFromFile(String.valueOf(file));
        try {
            //获取pdf文件总页数
            int pages = getNumberOfPages(file);
            //如果页数少于十直接转换word
            if (pages < 11){
                //保存为Word格式
                pdf.saveToFile(prefix+".docx", FileFormat.DOCX);
            }else {
                //pdf切割
                int count=0;
                for(int i = 0;i < pages;){
                     tempPdf = new PdfDocument();
                    PdfPageBase page;
                    //
                    for(int j = i;j<i+10;j++) {
                        page = tempPdf.getPages().add(pdf.getPages().get(j).getSize(), new PdfMargins(0));
                        pdf.getPages().get(j).createTemplate().draw(page, new Point2D.Float(0,0));
                    }
                    //保存为word文档
                    tempPdf.saveToFile(storagePath+File.separator+prefix+" - "+count+".docx",FileFormat.DOCX);
                    count++;
                    i+=10;
                    //如果剩余页数不足十页
                    if (i+10>=pages){
                        for(int j = i;j<pages;j++) {
                            page = tempPdf.getPages().add(pdf.getPages().get(j).getSize(), new PdfMargins(0));
                            pdf.getPages().get(j).createTemplate().draw(page, new Point2D.Float(0,0));
                        }
                        //保存为word文档
                        tempPdf.saveToFile(storagePath+File.separator+prefix+" - "+count+".docx",FileFormat.DOCX);
                        tempPdf.close();
                        return true;
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            pdf.close();
            if (tempPdf!=null){
                tempPdf.close();
            }
        }
        return true;
    }
    *//**
     * 压缩整个文件夹中的所有文件，生成指定名称的zip压缩包
     * @param filepath 文件所在目录
     * @param zippath 压缩后zip文件名称
     * @param dirFlag zip文件中第一层是否包含一级目录，true包含；false没有
     * 2015年6月9日
     *//*
    public static void zipMultiFile(String filepath ,String zippath, boolean dirFlag) {
        try {
            File file = new File(filepath);// 要被压缩的文件夹
            File zipFile = new File(zippath);
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for(File fileSec:files){
                    if(dirFlag){
                        recursionZip(zipOut, fileSec, file.getName() + File.separator);
                    }else{
                        recursionZip(zipOut, fileSec, "");
                    }
                }
            }
            zipOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void recursionZip(ZipOutputStream zipOut, File file, String baseDir) throws Exception{
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File fileSec:files){
                recursionZip(zipOut, fileSec, baseDir + file.getName() + File.separator);
            }
        }else{
            byte[] buf = new byte[1024];
            InputStream input = new FileInputStream(file);
            zipOut.putNextEntry(new ZipEntry(baseDir + file.getName()));
            int len;
            while((len = input.read(buf)) != -1){
                zipOut.write(buf, 0, len);
            }
            input.close();
        }
    }*/
}
