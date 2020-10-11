package com.cast.emc.service;

import com.cast.emc.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class BasicService {
    @Autowired
    protected EmcDataJPA emcDataJPA;

    @Autowired
    protected EnvirDataJPA envirDataJPA;

    @Autowired
    protected PulseDataJPA pulseDataJPA;

    @Autowired
    protected FreqDataJPA freqDataJPA;

    @Autowired
    protected SampleDataJPA sampleDataJPA;

    @Autowired
    protected UnitDataJPA unitDataJPA;

    @Autowired
    protected SysDataJPA sysDataJPA;

    @Autowired
    protected UsedFreqPointJPA usedFreqPointJPA;

    @Autowired
    protected OperationJPA operationJPA;

    @Autowired
    protected LabelJPA labelJPA;

    @Autowired
    protected UploadService uploadService;

    @Autowired
    protected SteadyDataJPA steadyDataJPA;
    protected Long formatTime(String time) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
        Date date = simpleDateFormat.parse(time);

        return date.getTime();
    }
}
