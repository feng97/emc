package com.cast.emc.model.enums;

import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.util.HashMap;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SignalType {
    FREQUENCY("frequency", "频域信号"),
    TIME("time", "时域信号");
    private String code;
    private String content;
    static HashMap<SignalType, Integer> map = new HashMap<>();

    SignalType(String code, String content) {
        this.code = code;
        this.content = content;
    }

}
