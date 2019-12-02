package com.cc.community.mapper;

import com.cc.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by xiaomi on 2019/12/1.
 */
@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,bio,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatarUrl})")
    void insert(User user);
    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);
    @Select("select * from user where account_id=#{githubUserId}")
    User findByGithubUserId(Long githubUserId);
    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer id);
}
