package com.aye.backendservice.exception;

/**
 * Created by toufiq on 5/10/2021.
 */
public class CustStockException extends Exception {

    private String errorMessage;

    public CustStockException(String message, Throwable cause) {
        super(message, cause);
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
