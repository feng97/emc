package com.cast.emc.controllers;

import com.cast.emc.common.ResponseType;
import com.cast.emc.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class BasicController {
    @Autowired
    protected EmcDataService emcDataService;

    @Autowired
    protected EnvirDataService envirDataService;

    @Autowired
    protected PulseDataService pulseDataService;

    @Autowired
    protected FreqDataService freqDataService;

    @Autowired
    protected SampleDataService sampleDataService;

    @Autowired
    protected UnitDataService unitDataService;

    @Autowired
    protected SysDataService sysDataService;

    @Autowired
    protected UsedFreqPointService usedFreqPointService;

    @Autowired
    protected SteadyDataService steadyDataService;

    @Autowired
    protected OperationService operationService;

    @Autowired
    protected UploadService uploadService;

    @Autowired
    protected LabelService labelService;

    public Map resultJson(ResponseType resType, Object result) {
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("code", resType.getCode());
        messageMap.put("messageInfo", resType.getMessage());
        messageMap.put("serverTime", System.currentTimeMillis());
        if (result != null) {
            messageMap.put("data", result);
        }
        return messageMap;
    }

    protected Map ok(Object o) {

        return resultJson(ResponseType.responseSuccess, o);
    }

    protected Map ok() {
        return resultJson(ResponseType.responseSuccess, null);
    }

    protected String formatData(Long createTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
        return sdf.format(createTime);
    }

}
