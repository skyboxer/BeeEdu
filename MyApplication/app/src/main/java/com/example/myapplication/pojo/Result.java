package com.example.myapplication.pojo;

import java.util.HashMap;

public class Result {
    String version;
    String timestamp;
    HashMap<String,Object> response;
    Object 	bot_session;

    public Object getBot_session() {
        return bot_session;
    }

    public void setBot_session(Object bot_session) {
        this.bot_session = bot_session;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public HashMap<String, Object> getResponse() {
        return response;
    }

    public void setResponse(HashMap<String, Object> response) {
        this.response = response;
    }
}
