package com.hzb.test.ui.activity.splash;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hzb.test.ui.activity.MainActivity;
import com.hzb.test.ui.activity.guide.GuideActivity;
import com.hzb.test.ui.activity.login.LoginActivity;
import com.hzb.utils.base.Base2Activity;
import com.hzb.utils.utilTool.AppUtils;
import com.hzb.utils.utilTool.SharedPreferenceUtils;
import com.hzb.utils.utilTool.StatusBarUtil;


/**
 * @author Created by houzhengbang
 * @description
 * @desc :欢迎页
 */

public class SplashActivity extends Base2Activity implements SplashControl.ISplashView {

    private SplashPresenter splashPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        //系统UI标志隐藏导航 状态栏
        Window _window = getWindow();
        WindowManager.LayoutParams params = _window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        _window.setAttributes(params);


        //全屏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
        StatusBarUtil.setColorDiff(this, Color.parseColor("#00000000"));  // 设置状态栏透明
//        StatusBarUtil.setLightMode(this);
//        StatusBarUtil.setDarkMode(this);


        //        setContentView(R.layout.activity_start_page);


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
