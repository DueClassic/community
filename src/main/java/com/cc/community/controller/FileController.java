package com.cc.community.controller;

import com.cc.community.dto.FileDTO;
import com.cc.community.exception.CustomizeErrorCode;
import com.cc.community.exception.CustomizeException;
import com.cc.community.provider.UCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by xiaomi on 2019/12/9.
 */
@Controller
public class FileController {
    @Autowired
    private UCloudProvider uCloudProvider;
    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest)request;
        MultipartFile file=multipartRequest.getFile("editormd-image-file");
        try {
            String fileName = uCloudProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl(fileName);
            return fileDTO;
        } catch (IOException e) {
            throw new CustomizeException(CustomizeErrorCode.FAIL_UPLOAD_FILE);
        }
    }
}
