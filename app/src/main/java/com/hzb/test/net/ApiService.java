package com.hzb.test.net;


import com.hzb.test.entity.LoginBean;
import com.hzb.test.entity.LoginBody;

import io.reactivex.Observable;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("parent/login/parentLogin.jhtml")
    Observable<LoginBean> sendss(@Body LoginBody req);

}
