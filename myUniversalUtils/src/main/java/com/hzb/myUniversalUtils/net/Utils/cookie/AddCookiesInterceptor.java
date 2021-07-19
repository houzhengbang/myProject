package com.hzb.myUniversalUtils.net.Utils.cookie;

import android.content.Context;

import com.hzb.myUniversalUtils.baseApplication.BaseApplication;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * FileName: AddCookiesInterceptor
 * Author: houzhengbang
 * Date: 2020/10/31 9:14 PM
 * Description:
 */
public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> perferences = (HashSet) BaseApplication.getInstance().getSharedPreferences("cookieData", Context.MODE_PRIVATE).getStringSet("cookie", null);
        if (perferences != null) {
            for (String cookie : perferences) {
                builder.addHeader("Cookie", cookie);
            }
        }
        return chain.proceed(builder.build());
    }
}
