package com.hzb.test.ui.activity.splash;


import com.hzb.test.callBack.OnSplashTypeCallBack;

/**
 * FileName: GuidePresenter
 * Author: houzhengbang
 * Date: 2020-05-28 15:23
 * Description:
 */
public class SplashPresenter implements SplashControl.ISplashPresenter {

    private final SplashControl.ISplashView iSplashView;
    private final SplashControl.ISplashModel splashModel;

    public SplashPresenter(SplashControl.ISplashView iSplashView) {
        this.iSplashView = iSplashView;

        splashModel = new SplashModel();
    }

    @Override
    public void timing() {
        splashModel.timing(iSplashView.getContent() ,new OnSplashTypeCallBack<String>() {
            @Override
            public void onTiming(String s) {
                iSplashView.toLogin();
            }
        });
    }
}
