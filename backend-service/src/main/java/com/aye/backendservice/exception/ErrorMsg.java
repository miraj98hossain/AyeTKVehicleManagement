package com.aye.backendservice.exception;


import com.aye.entity.user.userData.JViews;
import com.fasterxml.jackson.annotation.JsonView;

public class ErrorMsg {
    @JsonView(JViews.Public.class)
    private String msg;

    public ErrorMsg() {
        // TODO Auto-generated constructor stub
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
