package com.enablue.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 文件下载
 * 王成
 */
@WebServlet("/downLoadServlet")
public class DownLoadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 读取文件到inputStream流中
         */
        //post请求乱码处理
        request.setCharacterEncoding("UTF-8");
        //获取文件名
        String filename = request.getParameter("filename");
        //设置头信息:content-disposition,修改打开文件形式:以附件的形式打开 并且修改文件名为filename
        response.setHeader("content-disposition","attchement;filename="+filename);
        //设置下载路径
        String realPath = getServletContext().getRealPath("/download/"+filename);
        //获取输入流
        FileInputStream inputStream = new FileInputStream(new File(realPath));
        //创建一个outputStream流把文件写入到浏览器中
        ServletOutputStream outputStream = response.getOutputStream();
        //流拷贝
        int len=0;
        byte buf [] =new byte[1024];
        while((len=inputStream.read(buf))!=-1){
            outputStream.write(buf,0,len);
        }
        //释放资源
        outputStream.close();
        inputStream.close();

    }
}
