package com.hzb.test.net;

import android.content.Context;

import com.hzb.myUniversalUtils.net.Utils.MyObserver;
import com.hzb.myUniversalUtils.net.Utils.RxHelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 提交参数方式
 */
public class RequestApiUtils {

    //请求网络
    public static <T> void getData(Context context, Observable<T> observable, MyObserver<T> observer) {
        observable
                .compose(RxHelper.observableIO2Main(context))
                .observeOn(AndroidSchedulers.mainThread())    //在主线程处理数据
                .subscribeOn(Schedulers.io())                   //在子线程请求数据
//                .subscribeOn(Schedulers.newThread())                   //在新 线程请求数据
                .subscribe(observer);

    }

}

