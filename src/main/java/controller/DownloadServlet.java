package controller;

import javax.servlet.ServletException;
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
 * @author buguniao
 * @version v1.0
 * @date 2019/4/17 17:36
 * @description 处理文件下载业务
 **/
@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //文件下载： 流拷贝
        String filename = request.getParameter("filename");

        //设置头信息：1、content-disposition ；2、mimetype
        response.setHeader("content-disposition","attchement;filename="+filename);
        response.setContentType(getServletContext().getMimeType(filename));


        //1、读取文件到inputstream中：
        String realPath = getServletContext().getRealPath("/result");
        System.out.println("realPath = " + realPath);
        InputStream inputStream = new FileInputStream(new File(realPath+File.separator+filename));

        //2、获取输出流： 写出到浏览器
        ServletOutputStream outputStream = response.getOutputStream();


        //3、流拷贝
        int len = 0;
        byte[] buf = new byte[1024];

        while ( (len=inputStream.read(buf))!=-1 ){
            outputStream.write(buf,0,len);
        }

        //释放资源
        outputStream.close();
        inputStream.close();


    }
}
