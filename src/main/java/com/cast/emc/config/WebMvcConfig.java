package com.cast.emc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;

/**
 * 权限配置类
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Value("${web.upload-path}")
    private String filePath;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 静态资源与上传资源url映射
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/image/**").addResourceLocations("file:E:/emc/src/main/resources/image/");
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/data/**").addResourceLocations("file:E:/emc/src/main/resources/data/");
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/envelope/**").addResourceLocations("file:E:/emc/src/main/resources/envelope/");
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/exceed/**").addResourceLocations("file:E:/emc/src/main/resources/exceed/");
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/report/**").addResourceLocations("file:E:/emc/src/main/resources/report/");
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + filePath);
        super.addResourceHandlers(registry);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }
}
