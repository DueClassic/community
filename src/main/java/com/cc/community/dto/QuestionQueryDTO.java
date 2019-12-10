package com.cc.community.dto;

import lombok.Data;

/**
 * Created by xiaomi on 2019/12/10.
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
