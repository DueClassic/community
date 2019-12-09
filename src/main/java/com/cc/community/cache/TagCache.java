package com.cc.community.cache;

import com.cc.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiaomi on 2019/12/8.
 */
public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();

        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("java", "c", "c++", "php", "perl", "python", "javascript", "c#", "ruby", "go", "lua", "node.js", "erlang", "scala", "bash", "actionscript", "golang", "typescript", "flutter"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring", "hibernate", "struts", "tomcat", "maven", "eclipse", "intellij-idea"));
        tagDTOS.add(framework);

        TagDTO database = new TagDTO();
        database.setCategoryName("数据库");
        database.setTags(Arrays.asList("mysql","sqlite","oracle","sql","nosql","redis","mongodb","memcached","postgresql"));
        tagDTOS.add(database);

        TagDTO tools = new TagDTO();
        tools.setCategoryName("开发工具");
        tools.setTags(Arrays.asList("vim","emacs","ide","eclipse","xcode","intellij-idea","textmate","sublime-text","visual-studio","git","github","svn","hg","docker","ci"));
        tagDTOS.add(tools);

        return tagDTOS;
    }
    public static String filterInvalid(String tags){
        String[] split= StringUtils.split(tags,',');
        List<TagDTO> tagDTOS=get();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
