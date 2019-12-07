package com.cc.community.exception;

/**
 * 枚举类，定义异常类型
 * Created by xiaomi on 2019/12/4.
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("你找的问题不存在，换个试试吧！",2001),
    TARGET_PARAM_NOT_FOUND("你找的问题不存在，换个试试吧！",2002),
    NOT_LOGIN("当前操作需要登录，请登录后进行重试！",2003),
    SYSTEM_ERR("服务器走丢了，请稍后再试！",2004),
    TYPE_PARAM_WRONG("回复类型不存在",2005 ),
    COMMENT_NOT_FOUND("回复的评论不存在了",2006 ),
    CONTENT_ISEMPTY("输入内容不能为空",2007)
    ;


   private String message;
   private Integer code;

    CustomizeErrorCode(String message,Integer code) {
        this.message=message;
        this.code=code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
