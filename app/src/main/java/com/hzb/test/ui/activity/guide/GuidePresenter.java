package com.hzb.test.ui.activity.guide;

/**
 * FileName: GuidePresenter
 * Author: houzhengbang
 * Date: 2020-05-28 15:23
 * Description:
 */
public class GuidePresenter implements GuideControl.ISplashPresenter {

    private final GuideControl.ISplashView iSplashView;
    private final GuideControl.ISplashModel splashModel;

    public GuidePresenter(GuideControl.ISplashView iSplashView) {
        this.iSplashView = iSplashView;
        splashModel = new GuideModel();
    }

}
