package com.cast.emc.utils;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 feng
 * @创建时间 2019/8/24
 * @描述
 */
public class JsonUtils {
    public static Map JsonToMap(String json) {
        Map maps = (Map) JSON.parse(json);
        return maps;
    }
}
