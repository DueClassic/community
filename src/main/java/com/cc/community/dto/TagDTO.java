package com.cc.community.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by xiaomi on 2019/12/8.
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
