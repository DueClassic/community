package com.cc.community.advice;

import com.alibaba.fastjson.JSON;
import com.cc.community.dto.ResultDTO;
import com.cc.community.exception.CustomizeErrorCode;
import com.cc.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 截获已知的异常处理，对其进行拦截，并将其异常类型显示到前端
 * Created by xiaomi on 2019/12/4.
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model,
                        HttpServletRequest request,
                        HttpServletResponse response){
        String contentType=request.getContentType();
        if("application/json".equals(contentType)){
            ResultDTO resultDTO;
            if (ex instanceof  CustomizeException){
                resultDTO=ResultDTO.errorOf((CustomizeException)ex);
            }else{
                resultDTO=ResultDTO.errorOf(CustomizeErrorCode.SYSTEM_ERR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            }catch (IOException e){
            }
            return null;

        }else {
            if (ex instanceof CustomizeException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYSTEM_ERR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
