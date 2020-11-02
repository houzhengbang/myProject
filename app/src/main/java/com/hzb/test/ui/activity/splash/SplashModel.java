package com.hzb.test.ui.activity.splash;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.hzb.test.callBack.OnSplashTypeCallBack;


/**
 * FileName: GuidePresenter
 * Author: houzhengbang
 * Date: 2020-05-28 15:23
 * Description:
 */
public class SplashModel implements SplashControl.ISplashModel {

    private static final int WHAT_DELAY = 0x11;// 启动页的延时跳转
    private static final int DELAY_TIME = 1000;// 延时时间
    private Handler handler;


    @Override
    public void timing(Context context, OnSplashTypeCallBack<String> typeCallBack) {

        // 创建Handler对象，处理接收的消息
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case WHAT_DELAY:// 延时3秒跳转
                        typeCallBack.onTiming("跳转");
                        break;
                }
            }
        };

        // 调用handler的sendEmptyMessageDelayed方法
        handler.sendEmptyMessageDelayed(WHAT_DELAY, DELAY_TIME);
    }
}
