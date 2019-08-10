package com.cast.emc.exception;

import com.cast.emc.common.ResponseType;

public class BizException extends RuntimeException {
    public ResponseType responseType;
    private Object extraMessage;

    public BizException(ResponseType responseType) {
        super();
        this.responseType = responseType;
        this.extraMessage = null;
    }

    public BizException(ResponseType responseType, Object meesage) {
        super();
        this.responseType = responseType;
        this.extraMessage = meesage;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public Object getExtraMessage() {
        return extraMessage;
    }


}