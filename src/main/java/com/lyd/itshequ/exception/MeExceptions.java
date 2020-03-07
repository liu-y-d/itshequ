package com.lyd.itshequ.exception;

public class MeExceptions extends RuntimeException {
    private String message;
    private Integer code;
    public MeExceptions(IMeErrorCode iMeErrorCode) {
        this.code = iMeErrorCode.getCode();
        this.message = iMeErrorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode(){
        return code;
    }
}
