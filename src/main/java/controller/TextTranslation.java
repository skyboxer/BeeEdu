package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import util.WebOTS;

/**
 * 文字翻译文字
 * 
 * @author chinaxjk
 *
 */
@WebServlet("/textTranslation")
public class TextTranslation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String from = request.getParameter("FROM");
		String to = request.getParameter("TO");
		String text= request.getParameter("TEXT");
		if(from == null || from.length()==0) {
			return;
		}
		if(to == null || to.length()==0) {
			return;
		}
		if(text == null || text.length()==0) {
			return;
		}
		WebOTS webOTS = new WebOTS();
		try {
			String resultStr = webOTS.getTranslate(from, to, text);
			JSONObject jsonObject = JSONObject.parseObject(resultStr);
			String dst = jsonObject.getJSONObject("data").getJSONObject("result").getJSONObject("trans_result").getString("dst");
			out.println(dst);
	        out.flush();
	        out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
