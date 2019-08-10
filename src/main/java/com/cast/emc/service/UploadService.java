package com.cast.emc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class UploadService extends BasicService {

    @Value("${web.upload-path}")
    private String path;

    public String getSuffix(MultipartFile file) {
        return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
    }

    public String getSuffix(String file) {
        return file.substring(file.lastIndexOf("."));
    }

    public String uploadFile(MultipartFile file) {
        if (StringUtils.isEmpty(file.getOriginalFilename())){
            return null;
        }
        String fileName = UUID.randomUUID().toString();
        
        File dest = new File(path + fileName + getSuffix(file));
        String path = "/file/" + fileName + getSuffix(file);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return path;
    }
}
