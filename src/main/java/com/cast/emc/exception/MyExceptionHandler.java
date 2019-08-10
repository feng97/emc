package com.cast.emc.exception;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;


@RestControllerAdvice
public class MyExceptionHandler {
    /* spring默认上传大小20MB 超出大小捕获异常MaxUploadSizeExceededException */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Object handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        return BizError.FILE_SIZE_LIMITED;
    }
}