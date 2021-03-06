package com.enablue.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author 王成
 * @version v1.0
 * @date 2019/4/17 17:36
 * @description 处理文件下载业务
 **/
@WebServlet("/downLoadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        ServletOutputStream outputStream=null;
        InputStream inputStream=null;
        try {
            //文件下载： 流拷贝
            String filename = request.getParameter("filename");
            request.setCharacterEncoding("utf-8");
            //设置头信息：1、content-disposition ；2、mimetype
            response.setContentType(getServletContext().getMimeType(filename));
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("utf-8"),"ISO8859-1"));

            //1、读取文件到inputstream中：
            String realPath = getServletContext().getRealPath("/download");

            File file = new File(realPath + File.separator + filename);
            if (!file.exists()){
                return ;
            }
            System.out.println("file = " + file);
            inputStream = new FileInputStream(file);
            //2、获取输出流： 写出到浏览器
             outputStream = response.getOutputStream();
            //3、流拷贝
            int len = 0;
            byte[] buf = new byte[1024];

            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }

            //释放资源
            outputStream.close();
            inputStream.close();
        }catch ( IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (outputStream!=null){
                    outputStream.close();
                }
                if (inputStream!=null){
                    inputStream.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }
}
