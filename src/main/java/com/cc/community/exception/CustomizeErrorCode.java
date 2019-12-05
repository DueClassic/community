package com.cc.community.exception;

/**
 * Created by xiaomi on 2019/12/4.
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
   QUESTION_NOT_FOUND("你找的问题不存在，换个试试吧！") ;

   private String message;

    CustomizeErrorCode(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
