package com.aye.backendservice.entity;

/**
 * Created by Munna on 8/22/2021.
 */
public class Message {
    private String tag;
    private String msg;

    public Message() {
    }

    public Message(String tag, String msg) {
        this.tag = tag;
        this.msg = msg;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}