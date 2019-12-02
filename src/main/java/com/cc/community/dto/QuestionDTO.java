package com.cc.community.dto;

import com.cc.community.model.User;
import lombok.Data;

/**
 * Created by xiaomi on 2019/12/2.
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
