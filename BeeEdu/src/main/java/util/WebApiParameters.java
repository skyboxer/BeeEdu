package util;

/**
 * 讯飞webapi接口公共参数
 * 
 * @author chinaxjk
 *
 */
public class WebApiParameters {
	// 应用ID（到控制台获取）
	public static final String APPID = "5d8c5a54";
	// 接口APIKey（到控制台机器翻译服务页面获取）
	public static final String API_KEY = "191c440025aa729d64f2c28e6accf898";
	// 接口APISercet（到控制台机器翻译服务页面获取）
	public static final String API_SECRET = "1db98f9db89fbf0d692d73874f58a39d";
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
