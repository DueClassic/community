package com.cc.community.dto;

import lombok.Data;

/**
 * Created by xiaomi on 2019/12/5.
 */
@Data
public class CommentCreateDTO {
    private  Long parentId;
    private String content;
    private Integer type;
}
