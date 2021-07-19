package com.hzb.myUniversalUtils.net.Utils;



import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * FileName: HttpUtilss
 * Author: houzhengbang
 * Date: 2020-05-27 13:10
 * Description: 网络请求  封装类
 */
public class HttpRequestUtil {
    //请求网络
    public static <T> void getData(Observable<T> observable, MyObserver<T> observer) {
        observable.observeOn(AndroidSchedulers.mainThread())    //在主线程处理数据
                .subscribeOn(Schedulers.io())                   //在子线程请求数据
//                .subscribeOn(Schedulers.newThread())                   //在新 线程请求数据
                .subscribe(observer);
    }

    //请求网络
    public static <T> void getData2(Observable<T> observable, MyObserver<T> observer) {
        observable.compose( RxSchedulers.applySchedulers())
                .subscribe(observer);
    }

    /**
     * 通用的Rx线程转换类
     */
    static class RxSchedulers {

        static final ObservableTransformer schedulersTransformer = new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return (upstream).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

        public static <T> ObservableTransformer<T, T> applySchedulers() {
            return (ObservableTransformer<T, T>) schedulersTransformer;
        }
    }

}
