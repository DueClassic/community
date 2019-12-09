package com.cc.community.enums;

/**
 * Created by xiaomi on 2019/12/8.
 */
public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论")
    ;
    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEnum(int status, String name){
        this.name=name;
        this.type =status;
    }

    public static String nameOfType(int type){
        for (NotificationTypeEnum notificationTypeEnum:NotificationTypeEnum.values()){
            if (notificationTypeEnum.getType()==type){
                return notificationTypeEnum.getName();
            }
        }
        return "";
    }
}
