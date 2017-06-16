package com.scu.pocketcampus;

public class ChatMessage {

    private String msg;
    private String name;
    private String userId;

    public ChatMessage(String msg, String name, String userId) {
        this.msg = msg;
        this.name = name;
        this.userId = userId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
