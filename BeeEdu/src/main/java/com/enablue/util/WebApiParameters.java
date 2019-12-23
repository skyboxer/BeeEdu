package com.enablue.util;

/**
 * 讯飞webapi接口公共参数
 * 
 * @author chinaxjk
 *
 */
public class WebApiParameters {
	// 应用ID（到控制台获取）
	public static final String APPID = "5dd61b5b";
	// 接口APIKey（到控制台机器翻译服务页面获取）
	public static final String API_KEY = "320546ff61555e5cf803e908eb089938";
	// 接口APISercet（到控制台机器翻译服务页面获取）
	public static final String API_SECRET = "fc640c2420174ac9925df4332747d19b";
	// OTS webapi 接口地址
	public static final String WebOTS_URL = "https://ntrans.xfyun.cn/v2/ots";
	// http url 不支持解析 ws/wss schema
	public static final String hostUrl = "https://tts-api.xfyun.cn/v2/tts";
	// 语音转写 java端
	public static final String hostUrlIat = "https://iat-api.xfyun.cn/v2/iat";
	// 语音转写 小语种
	public static final String hostUrlIatNiche = "\"https://iat-niche-api.xfyun.cn/v2/iat\";";
	// 语音文件转写 地址
	public static final String file = "src/main/resources/iat/"; // 中文
	
	
}
