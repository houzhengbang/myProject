package com.hzb.utils.net.Utils.cookie;

import android.content.Context;
import android.content.SharedPreferences;

import com.hzb.utils.baseApplication.BaseApplication;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * FileName: ReceivedCookiesInterceptor
 * Author: houzhengbang
 * Date: 2020/10/31 9:13 PM
 * Description:
 */
public class ReceivedCookiesInterceptor implements Interceptor {

    public ReceivedCookiesInterceptor() {
        super();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        //这里获取请求返回的cookie
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {

            HashSet<String> cookies = new HashSet<>();
            for(String header: originalResponse.headers("Set-Cookie"))
            {
//                LogUtil.i(TAG, "拦截的cookie是："+header);
                cookies.add(header);
            }
            //保存的sharepreference文件名为cookieData
            SharedPreferences sharedPreferences = BaseApplication.getInstance().getSharedPreferences("cookieData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet("cookie", cookies);

            editor.commit();
        }

        return originalResponse;
    }
}
