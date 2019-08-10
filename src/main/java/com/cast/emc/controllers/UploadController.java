package com.cast.emc.controllers;

import com.cast.emc.DTO.FileDTO;
import com.cast.emc.DTO.ImageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Slf4j
@Controller
public class UploadController extends BasicController {
    @ResponseBody
    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    public ImageDTO uploadImage(@RequestParam("upfile") MultipartFile file){
        ImageDTO imageDTO = new ImageDTO();

        String fileName = UUID.randomUUID().toString();
        String filePath = "E:\\emc\\src\\main\\resources\\data";
        File dest = new File(filePath + fileName + uploadService.getSuffix(file));
        try {
            file.transferTo(dest);
            imageDTO.setState("SUCCESS");
            imageDTO.setUrl(fileName + uploadService.getSuffix(file));
            imageDTO.setTitle(fileName + uploadService.getSuffix(file));
            imageDTO.setOriginal(fileName + uploadService.getSuffix(file));
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return imageDTO;
    }
    @ResponseBody
    @RequestMapping(value = "/upload/file", method = RequestMethod.POST)
    public FileDTO uploadFile(@RequestParam("file") MultipartFile file){
        FileDTO fileDTO = new FileDTO();
        if (file.isEmpty()) {
            fileDTO.setMsg("上传失败，请选择文件");
        }

        String fileName = UUID.randomUUID().toString();
        String filePath = "E:\\emc\\src\\main\\resources\\data\\";
        File dest = new File(filePath + fileName + uploadService.getSuffix(file));
        try {
            file.transferTo(dest);
            fileDTO.setMsg("上传成功");
            fileDTO.setCode(0);
            HashMap<String,String> res = new HashMap<>();
            res.put("src", "/file/" + fileName + uploadService.getSuffix(file));
            fileDTO.setData(res);
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return fileDTO;
    }
}
