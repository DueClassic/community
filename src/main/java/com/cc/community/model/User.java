package com.cc.community.model;

import lombok.Data;

/**
 * Created by xiaomi on 2019/12/1.
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;
}
