package com.cs.demo.constants;

/**
 * @Description : 异常常量
 */
public enum ErrorConstants {
    /**
     * 成功常量
     */
    SUCCESS(200, "success"),
    SIGN_CHECK_ERROR(100001, "签名校验未通过"),
    SYSTEM_ERROR(100002, "系统异常"),
    ;

    private int errorCode;

    private String errorMsg;

    ErrorConstants(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
