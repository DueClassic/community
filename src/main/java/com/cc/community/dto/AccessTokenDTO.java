package com.cc.community.dto;

import lombok.Data;

/**
 * Created by xiaomi on 2019/12/1.
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
    private String bio;
}
