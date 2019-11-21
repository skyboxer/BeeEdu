package com.example.myapplication.util;

public class Config {
    private static final String APPID = "5dd61b5b";
    private static final String APISecret = "1db98f9db89fbf0d692d73874f58a39d";
    private static final String APIKey = "f26e1aaf4a9827eb99e32642e246b23c";
    private static final String HOST = "rtasr.xfyun.cn/v1/ws";

    private static final String BASE_URL = "ws://" + HOST;

    private static final String ORIGIN = "http://" + HOST;

    public String getAPPID() {
        return APPID;
    }

    public String getAPISecret() {
        return APISecret;
    }

    public String getAPIKey() {
        return APIKey;
    }

    public String getBaseUrl(){
        return BASE_URL;
    }
    public String getOrigin(){
        return ORIGIN;
    }
}
