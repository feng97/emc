package com.cast.emc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @创建人 feng
 * @创建时间 2019/8/10
 * @描述 启动时判断文件上传目录是否存在，不存在则创建
 */
@Slf4j
@Component
public class InitStartupRunner implements CommandLineRunner {

    @Value("${web.upload-path}")
    private String path;

    @Override
    public void run(String... args) throws Exception {
        path.replace("/","\\");
        System.out.println(path);
        File file = new File(path);
        //如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
            log.info("文件上传目录不存在，建立路径:{}",path);
        }
    }
}
