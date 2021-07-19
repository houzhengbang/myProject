package com.hzb.myUniversalUtils.net.Utils;


import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import retrofit2.HttpException;

/**
 * 异常处理
 */
public class RxExceptionUtil {
//    public static String exceptionHandler(Throwable e){
//        String errorMsg = "未知错误";
//        if (e instanceof UnknownHostException) {
//            errorMsg = "网络不可用";
//        } else if (e instanceof SocketTimeoutException) {
//            errorMsg = "请求网络超时";
//        } else if (e instanceof HttpException) {
//            HttpException httpException = (HttpException) e;
//            errorMsg = convertStatusCode(httpException);
//        } else if (e instanceof ParseException || e instanceof JSONException
//                || e instanceof JSONException) {
//            errorMsg = "数据解析错误";
//        }
//        return errorMsg;
//    }
//
//
//    private static String convertStatusCode(HttpException httpException) {
//        String msg;
//        if (httpException.code() >= 500 && httpException.code() < 600) {
//            msg = "服务器处理请求出错";
//        } else if (httpException.code() >= 400 && httpException.code() < 500) {
//            msg = "服务器无法处理请求";
//        } else if (httpException.code() >= 300 && httpException.code() < 400) {
//            msg = "请求被重定向到其他页面";
//        } else {
//            msg = httpException.message();
//        }
//        return msg;
//    }

    public RxExceptionUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String getThrowable(Throwable e) {
        String message = "";
        if ((e instanceof UnknownHostException)) {
            message = "网络异常";
        } else if (e instanceof JsonSyntaxException) {
            message = "数据异常";
        } else if (e instanceof SocketTimeoutException) {
            message = "连接超时";
        } else if (e instanceof ConnectException) {
            message = "连接服务器失败";
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            message = convertStatusCode(httpException);
        } else {
//            message = e.getMessage();
            message = "未知错误";
        }
        return message;
    }



    private static String convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code() >= 500 && httpException.code() < 600) {
            msg = "服务器处理请求出错";
        } else if (httpException.code() >= 400 && httpException.code() < 500) {
            msg = "服务器无法处理请求";
        } else if (httpException.code() >= 300 && httpException.code() < 400) {
            msg = "请求被重定向到其他页面";
        } else {
            msg = httpException.message();
        }
        return msg;
    }

}
