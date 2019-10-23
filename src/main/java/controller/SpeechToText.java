package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import util.WebApiParameters;
import util.WebIATWS;

/**
 * 语音听写
 * @author chinaxjk
 *
 */
@WebServlet("/speechToText")
public class SpeechToText extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		WebIATWS.setFile(WebApiParameters.file+"16k_10.pcm");
		// 构建鉴权url
        String authUrl;
		try {
			authUrl = WebIATWS.getAuthUrl(WebApiParameters.hostUrlIat, WebApiParameters.API_KEY, WebApiParameters.API_SECRET);
			OkHttpClient client = new OkHttpClient.Builder().build();
	        String url = authUrl.toString().replace("http://", "ws://").replace("https://", "wss://");
	        Request request = new Request.Builder().url(url).build();
	        WebSocket webSocket = client.newWebSocket(request, new WebIATWS());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
