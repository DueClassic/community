package com.cc.community.service;

import com.cc.community.dto.PageDTO;
import com.cc.community.dto.QuestionDTO;
import com.cc.community.dto.QuestionQueryDTO;
import com.cc.community.exception.CustomizeErrorCode;
import com.cc.community.exception.CustomizeException;
import com.cc.community.mapper.QuestionExtMapper;
import com.cc.community.mapper.QuestionMapper;
import com.cc.community.mapper.UserMapper;
import com.cc.community.model.Question;
import com.cc.community.model.QuestionExample;
import com.cc.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiaomi on 2019/12/2.
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
     private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PageDTO list(Integer page, Integer size) {
        PageDTO pageDTO=new PageDTO();
        Integer totalCount=(int)questionMapper.countByExample(new QuestionExample());
        Integer totalPage;
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }
        else{
            totalPage=totalCount/size+1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        pageDTO.setPagination(totalPage,page);
        //page:第几页  size：页内数量   offset:偏移量
        Integer offset=size*(page-1);
        QuestionExample example = new QuestionExample();
        example.setOrderByClause("gmt_create desc");
        List<Question> questions=questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOS=new ArrayList<>();
        for(Question question:questions){
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setData(questionDTOS);
        return pageDTO;
    }

    public PageDTO list(String search,Integer page, Integer size) {
        if (StringUtils.isNotBlank(search)){
            String regexTag=StringUtils.replaceChars(search,",","|");
            search=StringUtils.replaceChars(regexTag,"，","|");
        }

        PageDTO pageDTO=new PageDTO();
        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(search);
        Integer totalCount=questionExtMapper.countBySearch(questionQueryDTO);
        Integer totalPage;
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }
        else{
            totalPage=totalCount/size+1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        pageDTO.setPagination(totalPage,page);
        //page:第几页  size：页内数量   offset:偏移量
        Integer offset=page<1?0:size*(page-1);
        QuestionExample example = new QuestionExample();
        example.setOrderByClause("gmt_create desc");
        questionQueryDTO.setSize(size);
        questionQueryDTO.setPage(offset);
        List<Question> questions=questionExtMapper.selectBySearch(questionQueryDTO);
        List<QuestionDTO> questionDTOS=new ArrayList<>();
        for(Question question:questions){
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setData(questionDTOS);
        return pageDTO;
    }

    public PageDTO list(Long userId, Integer page, Integer size) {
        PageDTO pageDTO=new PageDTO();
        Integer totalPage;

        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount=(int) questionMapper.countByExample(questionExample);
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }
        else{
            totalPage=totalCount/size+1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        pageDTO.setPagination(totalPage,page);
        //page:第几页  size：页内数量   offset:偏移量
        Integer offset=size*(page-1);
        questionExample.setOrderByClause("gmt_create desc");
        List<Question> questions=questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOS=new ArrayList<>();
        for(Question question:questions){
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setData(questionDTOS);
        return pageDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question=questionMapper.selectByPrimaryKey(id);
        if(question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
        }else{
            //更新
            question.setGmtModified(System.currentTimeMillis());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updated=questionMapper.updateByExampleSelective(question,example);
            if(updated!=1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
    public void incViewer(Long questionId) {
        Question updateQuestion=new Question();
        updateQuestion.setId(questionId);
        updateQuestion.setViewCount(1);
        questionExtMapper.incViewer(updateQuestion);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())){
            return new ArrayList<>();
        }
        String regexTag=StringUtils.replaceChars(queryDTO.getTag(),",","|");
        regexTag=StringUtils.replaceChars(regexTag,"，","|");
        Question question=new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexTag);
        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;

    }
}
