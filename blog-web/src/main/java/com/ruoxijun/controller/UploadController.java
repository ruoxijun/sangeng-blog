package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.enums.ResultEnum;
import com.ruoxijun.exception.SystemException;
import com.ruoxijun.service.UploadService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    private UploadService uploadService;

    @PostMapping("/image")
    public R<String> uploadImage(MultipartFile image) throws IOException {
        if (image == null) {
            throw new RuntimeException("请上传图片");
        }
        return R.ok(uploadService.uploadImage(image));
    }

}
