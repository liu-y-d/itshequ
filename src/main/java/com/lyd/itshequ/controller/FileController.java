package com.lyd.itshequ.controller;

import com.lyd.itshequ.bean.FileDTO;
import com.lyd.itshequ.exception.MeErrorCode;
import com.lyd.itshequ.exception.MeExceptions;
import com.lyd.itshequ.util.QiNiuUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileController {
    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("editormd-image-file");
        FileDTO fileDTO = new FileDTO();
        try {
            String s = QiNiuUpload.updateFile(file, file.getOriginalFilename());
            fileDTO.setSuccess(1);
            fileDTO.setUrl(s);
        } catch (Exception e) {
            throw new MeExceptions(MeErrorCode.UPLOAD_ERROR);
        }

        return fileDTO;
    }
}
