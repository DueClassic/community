package com.cc.community.exception;

/**
 * Created by xiaomi on 2019/12/4.
 */
public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message=errorCode.getMessage();
    }

    public CustomizeException(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
