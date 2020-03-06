package com.lyd.itshequ.exception;

public enum MeErrorCode implements IMeErrorCode {
    POST_NOT_FOUND("您找的问题不存在！"),
    LOGIN_TIME_OUT("登录超时，请重试");
    @Override
    public String getMessage(){
        return message;
    }
    private String message;
    MeErrorCode(String message){
        this.message = message;
    }
}
