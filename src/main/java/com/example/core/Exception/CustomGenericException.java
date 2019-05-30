package com.example.core.Exception;

/**
 * @Author wangwei
 * @Date 2018/10/23 11:57
 * -描述- 自定义异常类
 */
public class CustomGenericException extends Throwable {
    private String errMsg;
    private Integer errCode;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

}
