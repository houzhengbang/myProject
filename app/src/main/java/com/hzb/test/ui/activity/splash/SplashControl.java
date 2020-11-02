package com.hzb.test.ui.activity.splash;

import android.content.Context;

import com.hzb.test.callBack.OnSplashTypeCallBack;


/**
 * FileName: GuidePresenter
 * Author: houzhengbang
 * Date: 2020-05-28 15:23
 * Description:
 */
public class SplashControl {

    interface ISplashView {
        Context getContent();
        void toLogin();
    }

    interface ISplashModel {
        void timing(Context context, OnSplashTypeCallBack<String> typeCallBack);
    }

    interface ISplashPresenter {
        void timing();
    }
}
