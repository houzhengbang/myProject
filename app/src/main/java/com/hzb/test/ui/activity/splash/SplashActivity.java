package com.hzb.test.ui.activity.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hzb.test.ui.activity.MainActivity;
import com.hzb.test.ui.activity.guide.GuideActivity;
import com.hzb.test.ui.activity.login.LoginActivity;
import com.hzb.myUniversalUtils.base.BaseActivity;
import com.hzb.myUniversalUtils.utilTool.AppUtils;
import com.hzb.myUniversalUtils.utilTool.SharedPreferenceUtils;


/**
 * @author Created by houzhengbang
 * @description
 * @desc :欢迎页
 */

public class SplashActivity extends BaseActivity implements SplashControl.ISplashView {

    private SplashPresenter splashPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //系统UI标志隐藏导航 状态栏
        hideNavigationBar();

        // 设置状态栏透明
        setStatusBarColor("#00000000");
//        StatusBarUtil.setLightMode(this);
//        StatusBarUtil.setDarkMode(this);


        //setContentView(R.layout.activity_start_page);


        splashPresenter = new SplashPresenter(this);
        splashPresenter.timing();

    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {

    }

    @Override
    public Context getContent() {
        return this;
    }

    @Override
    public void toLogin() {
        goHome();
    }

    /**
     * 跳转到主页面
     */
    private void goHome() {
        boolean firstOpen = AppUtils.isFirstOpen();
        if (firstOpen) {
            AppUtils.setFirstOpen(false);
            goNextactive(3);
        } else {
            if (!SharedPreferenceUtils.getIsLogin(this)) {
                goNextactive(1);
            } else {
                goNextactive(2);
            }
        }
    }

    /**
     * 跳转页面
     *
     * @param type
     */
    private void goNextactive(int type) {
        Intent intent = new Intent();
        if (1 == type) {
            intent.setClass(this, LoginActivity.class);
        } else if (2 == type) {
            intent.setClass(this, MainActivity.class);
        } else if (3 == type) {
            intent.setClass(this, GuideActivity.class);
        }
        startActivity(intent);
        finish();// 销毁当前活动界面
    }
}
