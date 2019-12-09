package com.cc.community.dto;

import com.cc.community.model.User;
import lombok.Data;

/**
 * Created by xiaomi on 2019/12/8.
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private String notifierName;
    private String outerTitle;
    private Long notifier;
    private String typeName;
    private Long outerid;
    private Integer type;
}
