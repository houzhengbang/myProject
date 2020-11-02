package com.hzb.test.ui.activity.login;




/**
 * FileName: LoginModel
 * Author: houzhengbang
 * Date: 2020-05-27 10:17
 * Description:
 */

import android.app.Activity;
import android.content.Context;

/**
 * 登录的桥梁(View和Model的桥梁)-------P通过将V拿到的数据交给M来处理  P又将处理的结果回显到V
 * 说白点就是P中new出M和V 然后通过调用它们两个的方法来完成操作
 */
public class LoginControl {

    /**
     * V视图层
     */
    interface ILoginView {
        Context getContent();

        Activity getActivity();

    }

    /**
     * 逻辑处理层
     */
    interface ILoginModel {


//        void updataApk(Context content, RequestBody body, OnHttpCallBack onHttpCallBack);
    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface ILoginPresenter {
//
//        void updataApk(RequestBody body);
//
//        void getDevices(String s);
//
//        void getUniquelyIdentifies(String s);

    }
}
