package com.cc.community.mapper;

import com.cc.community.model.Question;
import com.cc.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    int incViewer(Question record);
}