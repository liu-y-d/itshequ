package com.lyd.itshequ.exception;

public enum MeErrorCode implements IMeErrorCode {
    LOGIN_TIME_OUT(2000, "登录超时，请重试"),
    POST_NOT_FOUND(2001, "您找的问题不存在！"),
    TARGET_PARAM_NOT_FOUNT(2002, "未选中任何问题或评论进行回复！"),
    NO_LOGIN(2003, "您没有权限，登录后重试！"),
    SYS_ERROR(2004,"系统错误请重试！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误，或问题不存在！"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在！"),
    NOT_NULL(2007,"输入内容不能为空！"),
    NOTIFICATION_NOT_FOUND(2008,"通知不存在！"),
    READ_ERROR(2009,"请勿修改URL！"),
    UPLOAD_ERROR(2010,"图片上传失败");

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private String message;
    private Integer code;

    MeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
