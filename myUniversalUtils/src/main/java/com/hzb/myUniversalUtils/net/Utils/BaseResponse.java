package com.hzb.myUniversalUtils.net.Utils;

/**
 * 统一响应
 * @param <T>
 */
public class BaseResponse<T> {


    /**
     * "result": "fail",
     * "errorCode": "4002",
     *  "errorMsg": "缺少必须的参数"
     */
    protected String result;
    protected String errorCode;
    protected String errorMsg;
    protected T data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "result='" + result + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
