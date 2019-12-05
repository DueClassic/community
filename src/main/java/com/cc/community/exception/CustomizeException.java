package com.cc.community.exception;

/**
 * 截获程序抛出的自定义异常，由ExceptionHandler拦截并处理
 * Created by xiaomi on 2019/12/4.
 */
public class CustomizeException extends RuntimeException{
    private String message;

    //构造函数,直接获取message字符串
    public CustomizeException(String message){
        this.message=message;
    }
    //构造函数，从CustomizeErrorCode中取值
    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message=errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
