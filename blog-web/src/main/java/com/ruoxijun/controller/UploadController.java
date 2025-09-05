package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.service.UploadService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    private UploadService uploadService;

    /**
     * 上传图片
     *
     * @param image 图片
     * @return 图片路径
     * @throws IOException 抛出IO异常
     */
    @PostMapping("/image")
    public R<String> uploadImage(MultipartFile image) throws IOException {
        if (image == null) {
            throw new RuntimeException("请上传图片");
        }
        return R.ok(uploadService.uploadImage(image));
    }

}
