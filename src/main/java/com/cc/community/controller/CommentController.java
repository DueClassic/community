package com.cc.community.controller;

import com.cc.community.dto.CommentDTO;
import com.cc.community.dto.ResultDTO;
import com.cc.community.exception.CustomizeErrorCode;
import com.cc.community.mapper.CommentMapper;
import com.cc.community.model.Comment;
import com.cc.community.model.User;
import com.cc.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiaomi on 2019/12/5.
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
        User user1 = (User) request.getSession().getAttribute("user");
        if (user1==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setCommentator(1L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
