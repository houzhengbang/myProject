package com.hzb.test.app;

import com.hzb.test.net.ApiService;
import com.hzb.myUniversalUtils.baseApplication.BaseApplication;
import com.hzb.myUniversalUtils.net.Utils.RetrofitUtils;

/**
 * FileName: MyApplication
 * Author: houzhengbang
 * Date: 2020/10/31 1:35 PM
 * Description:
 */
public class MyApplication extends BaseApplication {

    private static ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    //网络请求
    public static ApiService getRetrofitUtils() {
        if (apiService == null) {
            apiService = (ApiService) (new RetrofitUtils()).getApiUrl(ApiService.class);
        }
        return apiService;
    }
}