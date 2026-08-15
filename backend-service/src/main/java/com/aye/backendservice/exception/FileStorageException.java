package com.aye.backendservice.exception;

public class FileStorageException extends Exceptions {

    private String errorMessage;

    public FileStorageException(String msg) {
        this.errorMessage = msg;
    }

}
