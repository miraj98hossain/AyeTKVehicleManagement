package com.aye.backendservice.exception;

/**
 * Created by Toufiq on 7/11/2019.
 */
public class NorderException extends Exception {
    private static final long serialVersionUID = 7718828512143293558L;

    //    private final ErrorCode code;
    private String errorMessage;

    public NorderException(String message, Throwable cause) {
        super(message, cause);

    }
}
