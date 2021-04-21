package com.cs.demo.dto;

import com.cs.demo.constants.ErrorConstants;

/**
 * 数据对象返回实体
 */
public class BaseResponse<T> {
    private Integer respCode;

    private String respMsg;

    private T data;

    public BaseResponse() {
        super();
    }

    public BaseResponse(Integer respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    public BaseResponse(ErrorConstants constants) {
        this.respCode = constants.getErrorCode();
        this.respMsg = constants.getErrorMsg();
    }

    public BaseResponse(T data) {
        this.respCode = ErrorConstants.SUCCESS.getErrorCode();
        this.respMsg = ErrorConstants.SUCCESS.getErrorMsg();
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getRespCode() {
        return respCode;
    }

    public void setRespCode(Integer respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }
}
