package com.cc.community.controller;

import com.cc.community.dto.AccessTokenDTO;
import com.cc.community.dto.GithubUser;
import com.cc.community.mapper.UserMapper;
import com.cc.community.model.User;
import com.cc.community.provider.GithubProvider;
import com.cc.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by xiaomi on 2019/12/1.
 */
@Controller
@Slf4j
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();

        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser=githubProvider.getUser(accessToken);
        User user=new User();
        if(githubUser!=null&&githubUser.getId()!=null){
            //登录成功
            String token = UUID.randomUUID().toString();
            userService.createOrUpdate(githubUser,token);
            response.addCookie(new Cookie("token",token));
            //重定向
            return "redirect:/";
        }else{
            log.error("callback get github error,{}",githubUser);
            //登录失败
            return "redirect:/";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response
                         ){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        response.addCookie(cookie);
        return  "redirect:/";
    }
}
