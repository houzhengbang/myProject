package com.hzb.myUniversalUtils.baseApplication;

import android.app.Application;


import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.BuildConfig;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;


public class BaseApplication extends Application {


    private static BaseApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        //logger 初始化
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) 是否显示线程信息。默认为true
                .methodCount(0)         // (Optional) 要显示多少个方法行。默认值2
                .methodOffset(7)        // (Optional) 隐藏内部方法调用直到offset。默认5
//                .logStrategy(customLog) // (Optional) 更改日志策略以打印出来。默认LogCat
                .tag(getPackageName())   // (Optional) 每个日志的全局标签。默认值PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));


        //路由 初始化
        if(BuildConfig.DEBUG){
            ARouter.openLog(); // 打印日志
            ARouter.openDebug(); // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //路由  销毁
        ARouter.getInstance().destroy();
    }

    public synchronized static BaseApplication getInstance() {
        return sInstance;
    }

}
