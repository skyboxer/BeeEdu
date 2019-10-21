package controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 上传文件
 * @author chinaxjk
 *
 */
@SuppressWarnings("serial")
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //获取复杂表单的输入流
       InputStream in=request.getInputStream();

       //输入流转化为字符串
       byte[] b=new byte[1024];
       in.read(b);
       System.out.println(new String(b));
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
   }
}
