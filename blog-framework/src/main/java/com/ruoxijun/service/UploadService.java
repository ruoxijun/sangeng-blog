package com.ruoxijun.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    String uploadImage(MultipartFile image) throws IOException;
}
