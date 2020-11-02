package com.hzb.utils.baseApplication;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.Log;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.concurrent.atomic.AtomicBoolean;


public class BaseApplication extends Application {


    private static BaseApplication sInstance;


    private AtomicBoolean mIsForeground = new AtomicBoolean();

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        //logger 初始化
//        Logger.addLogAdapter(new AndroidLogAdapter());

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) 是否显示线程信息。默认为true
                .methodCount(0)         // (Optional) 要显示多少个方法行。默认值2
                .methodOffset(7)        // (Optional) 隐藏内部方法调用直到offset。默认5
//                .logStrategy(customLog) // (Optional) 更改日志策略以打印出来。默认LogCat
                .tag(getPackageName())   // (Optional) 每个日志的全局标签。默认值PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }


    public synchronized static BaseApplication getInstance() {
        return sInstance;
    }

}
