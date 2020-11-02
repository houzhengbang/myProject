package com.hzb.test.callBack;

/**
 * FileName: OnHttpCallBack
 * Author: houzhengbang
 * Date: 2020-05-27 16:39
 * Description: 网络请求返回 数据后  获取数据-回调至P层进行处理
 */
public  interface OnSplashTypeCallBack<T> {
    void onTiming(T t);//成功了就回调这个方法,可以传递任何形式的数据给调用者

}
