package com.cast.emc.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.HashMap;

/**
 * @创建人 feng
 * @创建时间 2019/8/24
 * @描述
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OperationTypeEnum {
    UNKNOW(0, "未知操作"),
    CREATE(1, "增加操作"),
    DELETE(2, "删除操作"),
    UPDATE(3, "更新操作");

    private int code;
    private String content;
    static HashMap<OperationTypeEnum, Integer> map = new HashMap<>();

    OperationTypeEnum(int code, String content) {
        this.code = code;
        this.content = content;
    }

    static {
        for (OperationTypeEnum operationTypeEnum : OperationTypeEnum.values()) {
            map.put(operationTypeEnum, operationTypeEnum.getCode());
        }
    }

    public static OperationTypeEnum findByCode(int code) {
        for (OperationTypeEnum operationTypeEnum : OperationTypeEnum.values()) {
            if (operationTypeEnum.getCode() == code) return operationTypeEnum;
        }
        return null;
    }
}
