package com.enablue.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import com.enablue.util.WebOTS;

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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String from = request.getParameter("FROM");
		String to = request.getParameter("TO");
		//String text= request.getParameter("TEXT");
		String text = new String(request.getParameter("TEXT"));
		String fromSign = request.getParameter("fromSign");
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
			boolean sign = true;
			StringBuffer endText = new StringBuffer(request.getParameter("TEXT"));
			StringBuffer startText = new StringBuffer("");
			StringBuffer resultIndexContext = new StringBuffer();
			while (sign){
				if(endText.length()<=2000){
					sign = false;
					startText = endText;
				}else{
					startText = new StringBuffer(endText.substring(0,2000));
					int index = startText.lastIndexOf(fromSign);
					startText = new StringBuffer(endText.substring(0,index+1));
					endText = new StringBuffer(endText.substring(index+1));
				}
				String resultStr = ""/*webOTS.getTranslate(from, to, startText.toString())*/;
				resultIndexContext.append(resultStr);
			}
			JSONObject jsonObject = JSONObject.parseObject(resultIndexContext.toString());
			JSONObject trans_result = jsonObject.getJSONObject("data").getJSONObject("result").getJSONObject("trans_result");
			out.println(trans_result);
	        out.flush();
	        out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
