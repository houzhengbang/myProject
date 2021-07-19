package com.hzb.myUniversalUtils.net;

public class ServerIP {


    private static ServerIP mInstance;

    public static ServerIP getRetrofitIP() {
        if (mInstance == null) {
            synchronized (ServerIP.class) {
                if (mInstance == null) {
                    mInstance = new ServerIP();
                }
            }
        }
        return mInstance;
    }

    private ServerIP() {

    }

    public String getHOST() {
        return baseHOST;
    }

    public String getHOSTWeb() {
        return HOSTWeb;
    }

    public String getHOSTSocket() {
        return socketHost;
    }


    private String baseIP = "118.186.8.106:8083/"; //本地
//    private String baseIP = "kezhong.bclc.com.cn/";//正式
//    private String baseIP = "192.168.0.119:8080/"; //测试


    private String baseHOST = "http://" + baseIP;  //接口

    private String HOSTWeb = "http://118.186.8.106:8081/";  //web H5

    private String socketHost = "ws://" + baseIP + "school-api/socketkzt/";  //socket


}
