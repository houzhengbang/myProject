package com.hzb.myUniversalUtils.baseApplication;

import android.content.Context;

//@SuppressWarnings("unused")
public class ApplicationContextHolder {

    public static Context getContext() {
        return BaseApplication.getInstance().getApplicationContext();
    }

}
