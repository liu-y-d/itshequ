package com.lyd.itshequ.exception;

public class MeExceptions extends RuntimeException {
    private String message;

    public MeExceptions(IMeErrorCode iMeErrorCode) {
        this.message = iMeErrorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
