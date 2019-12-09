package com.cc.community.enums;

/**
 * Created by xiaomi on 2019/12/8.
 */
public enum NotificationStatusEnum {
    UNREAD(0),READ(1);

    private int status;

    public int getStatus(){
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
