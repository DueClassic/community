package com.cc.community.dto;

import com.cc.community.model.User;
import lombok.Data;

/**
 * Created by xiaomi on 2019/12/6.
 */
@Data
public class CommentDTO {
    private Long id;

    private Long parentId;

    private Integer type;

    private Long commentator;

    private Long gmtCreate;

    private Long gmtModified;

    private Long likeCount;

    private  Integer commentCount;

    private String content;

    private User user;
}
