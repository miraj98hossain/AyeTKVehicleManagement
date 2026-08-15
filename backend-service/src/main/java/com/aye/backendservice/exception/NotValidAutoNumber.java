package com.aye.backendservice.exception;

public class NotValidAutoNumber extends Exceptions {

    private String errorMessage;

    public NotValidAutoNumber(String msg) {
        this.errorMessage = msg;
    }

}
