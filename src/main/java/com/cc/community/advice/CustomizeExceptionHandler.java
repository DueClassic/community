package com.cc.community.advice;

import com.cc.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 截获已知的异常处理，对其进行拦截，并将其异常类型显示到前端
 * Created by xiaomi on 2019/12/4.
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model){
        if(ex instanceof CustomizeException){
            model.addAttribute("message",ex.getMessage());
        }else{
            model.addAttribute("message","服务受阻！！");
        }
        return new ModelAndView("error");
    }
}
