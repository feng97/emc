package com.cast.emc.common;

public class ResponseType {
    private Integer code;
    private String message;
    public static ResponseType responseSuccess = new ResponseType(0, "ok");
    public static ResponseType ADD_FAILED = new ResponseType(3001, "add error");

    public ResponseType(Integer errCode, String errMsg) {
        this.code = errCode;
        this.message = errMsg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
