package com.hzb.utils.net.Utils;

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

    //    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//
//    public String getResult() {
//        return result;
//    }
//
//    public void setResult(String result) {
//        this.result = result;
//    }
//
//    public String getErrorCode() {
//        return errorCode;
//    }
//
//    public void setErrorCode(String errorCode) {
//        this.errorCode = errorCode;
//    }
//
//    public String getErrorMsg() {
//        return errorMsg;
//    }
//
//    public void setErrorMsg(String errorMsg) {
//        this.errorMsg = errorMsg;
//    }

//    @Override
//    public String toString() {
//        return "BaseBean{" +
//                "result='" + result + '\'' +
//                ", errorCode='" + errorCode + '\'' +
//                ", errorMsg='" + errorMsg + '\'' +
//                '}';
//    }
//
//    public T data() {
//        return null;
//    }


//
//    private int res_code;
//    private String err_msg;
//    private T demo;
//    public int getRes_code() {
//        return res_code;
//    }
//    public void setRes_code(int res_code) {
//        this.res_code = res_code;
//    }
//    public String getErr_msg() {
//        return err_msg;
//    }
//    public void setErr_msg(String err_msg) {
//        this.err_msg = err_msg;
//    }
//    public T getDemo() {
//        return demo;
//    }
//    public void setDemo(T demo) {
//        this.demo = demo;
//    }
}
