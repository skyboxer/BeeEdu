package com.example.chatbot;

public class Config {
    private static final String APPID = "5d8c5a54";
    private static final String APISecret = "1db98f9db89fbf0d692d73874f58a39d";
    private static final String APIKey = "191c440025aa729d64f2c28e6accf898";
    private static final String HOST = "rtasr.xfyun.cn/v1/ws";

    private static final String BASE_URL = "wss://" + HOST;

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
