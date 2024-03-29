package com.hzb.myUniversalUtils.net.Utils;


import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.hzb.myUniversalUtils.BuildConfig;
import com.hzb.myUniversalUtils.baseApplication.BaseApplication;
import com.hzb.myUniversalUtils.net.ServerIP;
import com.hzb.myUniversalUtils.net.HttpConstans;
import com.hzb.myUniversalUtils.net.Utils.persistentcookiejar.ClearableCookieJar;
import com.hzb.myUniversalUtils.net.Utils.persistentcookiejar.PersistentCookieJar;
import com.hzb.myUniversalUtils.net.Utils.persistentcookiejar.cache.SetCookieCache;
import com.hzb.myUniversalUtils.net.Utils.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.hzb.myUniversalUtils.utilTool.LogUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit封装
 */

/*
 在app model 中   myApplication

     //网络请求
    public static ApiService getRetrofitUtils() {
        if (apiService == null) {
            apiService = (ApiService) (new RetrofitUtils()).getApiUrl(ApiService.class);
        }
        return apiService;
    }

    调用
        LoginBody body = new LoginBody("18434395102", "111", "");
        RequestApiUtils.getData(this, MyApplication.getRetrofitUtils().sendss(body), new MyObserver<LoginBean>(this) {

            @Override
            public void onSuccess(LoginBean result) {
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
            }
        });

 */
public class RetrofitUtils<T> {
    private final String TAG = "RetrofitUtils";
    private T mApiUrl;
    private static RetrofitUtils retrofitUtils;

    /**
     * 单例模式
     *
     * @param
     * @return
     */
    private T t;

    public T getApiUrl(Class<T> tClass) {
        if (t == null) {
            synchronized (RetrofitUtils.class) {
                if (t == null) {
                    t = (T) (new RetrofitUtils()).getRetrofit(tClass);
                }
            }
        }
        return t;
    }

    public RetrofitUtils() {
    }


    public T getRetrofit(Class<T> tClass) {
        // 初始化Retrofit
        T t = initRetrofit(initOkHttp().build()).create(tClass);
        return t;
    }

    /**
     * 初始化Retrofit
     */
    @NonNull
    private Retrofit initRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(ServerIP.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    private static ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.getInstance()));

    /**
     * 初始化okhttp
     *
     * @return
     */
    @NonNull
    private OkHttpClient.Builder initOkHttp() {
        OkHttpClient.Builder builds = new OkHttpClient.Builder();
        builds.readTimeout(HttpConstans.READ_DEFAULT_TIME, TimeUnit.SECONDS);//设置读取超时时间
        builds.connectTimeout(HttpConstans.REQUEST_DEFAULT_TIME, TimeUnit.SECONDS);//设置请求超时时间
        builds.writeTimeout(HttpConstans.WRITE_DEFAULT_TIME, TimeUnit.SECONDS);//设置写入超时时间
        builds.retryOnConnectionFailure(true);//设置出现错误进行重新连接。
        builds.cookieJar(cookieJar);  //cookie 令牌


        if (BuildConfig.DEBUG) {
            builds.addInterceptor(genericClient());//添加打印拦截器
        }

        return builds;
    }


    public static HttpLoggingInterceptor genericClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (TextUtils.isEmpty(message)) {
                    return;
                }
                //打印json数据
                LogUtil.i("HttpLoggingInterceptor","log: " +message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }
}

