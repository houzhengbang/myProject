package com.hzb.myUniversalUtils.net.Utils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 数据返回统一处理  参考https://www.jianshu.com/p/ff619fea7e22
 *
 * @param <T>
 */
public abstract class BaseObserver<T> implements Observer<T> {
    private static final String TAG = "BaseObserver";

    public static final String FAIL = "fail";
    public static final String SUCCESS = "success";

    @Override
    public void onNext(T t) {

        //加判空，防止网络请求返回空的情况
        if (t != null && t instanceof BaseResponse) {
            //        //在这边对 基础数据 进行统一处理  举个例子：
            BaseResponse baseBean = (BaseResponse) t;
            if (baseBean.getResult().equals(FAIL)) {
                onFailure(null, baseBean.getErrorMsg());
            } else if (baseBean.getResult().equals(SUCCESS)) {
                //请求正常
                onSuccess(t);
            }
        }
}

    @Override
    public void onError(Throwable e) {//服务器错误信息处理
        onFailure(e, RxExceptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    public abstract void onSuccess(T result);

    public abstract void onFailure(Throwable e, String errorMsg);

}
