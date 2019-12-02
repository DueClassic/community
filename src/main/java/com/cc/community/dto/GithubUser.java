package com.cc.community.dto;

import lombok.Data;

/**
 * Created by xiaomi on 2019/12/1.
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
