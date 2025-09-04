package com.ruoxijun.service.impl;

import com.ruoxijun.service.UploadService;
import com.ruoxijun.utils.JwtUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Data
@Service
public class UploadServiceImpl implements UploadService {

    @Value("${upload.path}")
    private String path;

    @Override
    public String uploadImage(MultipartFile image) throws IOException {
        String fileName = image.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (!".png".equals(suffix) && !".jpg".equals(suffix) && !".jpeg".equals(suffix)) {
            throw new RuntimeException("请上传图片");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String datePath = sdf.format(System.currentTimeMillis());
        String filePath = datePath + JwtUtils.getUUID() + suffix;
        File file = new File(path + filePath);
        image.transferTo(file);
        return filePath;
    }

}
