package com.enablue.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 机器翻译2.0(niutrans) WebAPI 接口调用示例
 * 运行前：请先填写Appid、APIKey、APISecret
 * 运行方法：直接运行 main() 即可 
 * 结果： 控制台输出结果信息
 * 
 * 1.接口文档（必看）：https://www.xfyun.cn/doc/nlp/niutrans/API.html
 * 2.错误码链接：https://www.xfyun.cn/document/error-code （错误码code为5位数字）
 * 3.个性化翻译术语自定义：
 * ***登陆开放平台 https://www.xfyun.cn/
 * ***在控制台--机器翻译(niutrans)--自定义翻译处
 * ***上传自定义翻译文件（打开上传或更新窗口，可下载示例文件）
 * @author iflytek
 */

public class WebOTS {	
	public String getTranslate(String FROM,String TO,String TEXT,String APPID,String API_SECRET,String API_KEY) throws Exception{
		if (APPID.equals("") || API_KEY.equals("") || API_SECRET.equals("")) {
			System.out.println("Appid 或APIKey 或APISecret 为空！请打开demo代码，填写相关信息。");
			return null;
		}
		String body = buildHttpBody(FROM,TO,TEXT,APPID);
		//System.out.println("【ITR WebAPI body】\n" + body);
		Map<String, String> header = buildHttpHeader(body,APPID,API_KEY,API_SECRET);
		Map<String, Object> resultMap = HttpUtil.doPost2(WebApiParameters.WebOTS_URL, header, body);
		if (resultMap != null) {
			String resultStr = resultMap.get("body").toString();
			System.out.println("【OTS WebAPI 接口调用结果】\n" + resultStr);
			//以下仅用于调试
		    Gson json = new Gson();
	        ResponseData resultData = json.fromJson(resultStr, ResponseData.class);
	        int code = resultData.getCode();
	        if (resultData.getCode() != 0) {
	    		System.out.println("请前往https://www.xfyun.cn/document/error-code?code=" + code + "查询解决办法");
	    		return "请前往https://www.xfyun.cn/document/error-code?code=" + code +"查询解决办法";
	        }
	        return resultStr;
		} else {
			System.out.println("调用失败！请根据错误信息检查代码，接口文档：https://www.xfyun.cn/doc/nlp/niutrans/API.html");
			return "调用失败！请根据错误信息检查代码，接口文档：https://www.xfyun.cn/doc/nlp/niutrans/API.html";
		}
	}
	/**
	 * 组装http请求头
	 */	
   public static Map<String, String> buildHttpHeader(String body,String APPID,String API_KEY,String API_SECRET) throws Exception {
		Map<String, String> header = new HashMap<String, String>();	
        URL url = new URL(WebApiParameters.WebOTS_URL);
        
        //时间戳
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));        
        Date dateD = new Date();
        String date = format.format(dateD);
		//System.out.println("【OTS WebAPI date】\n" + date);

		//对body进行sha256签名,生成digest头部，POST请求必须对body验证
		String digestBase64 = "SHA-256=" + signBody(body);
		//System.out.println("【OTS WebAPI digestBase64】\n" + digestBase64);
        
		//hmacsha256加密原始字符串
        StringBuilder builder = new StringBuilder("host: ").append(url.getHost()).append("\n").//
        		append("date: ").append(date).append("\n").//
                append("POST ").append(url.getPath()).append(" HTTP/1.1").append("\n").//
                append("digest: ").append(digestBase64);
		//System.out.println("【OTS WebAPI builder】\n" + builder);
        String sha = hmacsign(builder.toString(), API_SECRET);
		//System.out.println("【OTS WebAPI sha】\n" + sha);
		
		//组装authorization
        String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", API_KEY, "hmac-sha256", "host date request-line digest", sha);
        //System.out.println("【OTS WebAPI authorization】\n" + authorization);
		
        header.put("Authorization", authorization);
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json,version=1.0");
        header.put("Host", url.getHost());
        header.put("Date", date);
        header.put("Digest", digestBase64);
		//System.out.println("【OTS WebAPI header】\n" + header);
		return header;
    }
   

	/**
	 * 组装http请求体
	 */	
   public static String buildHttpBody(String FROM,String TO,String TEXT,String APPID) throws Exception {
       JsonObject body = new JsonObject();
       JsonObject business = new JsonObject();
       JsonObject common = new JsonObject();
       JsonObject data = new JsonObject();
       //填充common
       common.addProperty("app_id",APPID);
       //填充business
       business.addProperty("from", FROM);
       business.addProperty("to", TO);
       //填充data
       //System.out.println("【OTS WebAPI TEXT字个数：】\n" + TEXT.length());
       byte[] textByte = TEXT.getBytes("UTF-8");
       String textBase64 = new String(Base64.getEncoder().encodeToString(textByte)); 
       //System.out.println("【OTS WebAPI textBase64编码后长度：】\n" + textBase64.length());
       data.addProperty("text", textBase64);
       //填充body
       body.add("common", common);
       body.add("business", business);
       body.add("data", data);
       return body.toString();
   }

   
	/**
	 * 对body进行SHA-256加密
	 */	
	private static String signBody(String body) throws Exception {
		MessageDigest messageDigest;
		String encodestr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(body.getBytes("UTF-8"));
			encodestr = Base64.getEncoder().encodeToString(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodestr;
	}
	
	/**
	 * hmacsha256加密
	 */
	private static String hmacsign(String signature, String apiSecret) throws Exception {
		Charset charset = Charset.forName("UTF-8");
		Mac mac = Mac.getInstance("hmacsha256");
		SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(charset), "hmacsha256");
		mac.init(spec);
		byte[] hexDigits = mac.doFinal(signature.getBytes(charset));
		return Base64.getEncoder().encodeToString(hexDigits);
	}
	  
   public static class ResponseData {
       private int code;
       private String message;
       private String sid;
       private Object data;
       public int getCode() {
           return code;
       }
       public String getMessage() {
           return this.message;
       }
       public String getSid() {
           return sid;
       }
       public Object getData() {
           return data;
       }
   }
}
