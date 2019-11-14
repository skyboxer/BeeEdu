package controller;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import util.WebTTSWS;


/**
 * 语音合成接口
 */
@WebServlet("/acquiringTextVoice")
public class AcquiringTextVoice extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String text = req.getParameter("Text");
        if(text == null || text.length()==0) {
            return;
        }
        try {
            WebTTSWS webTTSWS = new WebTTSWS();
            String projectPath=  getServletContext().getRealPath("/upload/");
            System.out.print("项目目录："+projectPath);
            String EntTo = "intp65";
            String fileWavName = webTTSWS.getVoice(text,projectPath,EntTo);
            out.println(fileWavName);
            out.flush();
            out.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
