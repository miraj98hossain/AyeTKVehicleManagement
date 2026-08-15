package com.aye.backendservice.exception;

/**
 * Created by toufiq on 3/2/2021.
 */
public class OnlineCollectionException extends Exception {
    private static final long serialVersionUID = 7718828512143293558L;
    private String errorMessage;

    public OnlineCollectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
