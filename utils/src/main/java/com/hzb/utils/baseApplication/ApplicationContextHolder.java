package com.hzb.utils.baseApplication;

import android.content.Context;

@SuppressWarnings("unused")
public class ApplicationContextHolder {

    public static Context getContext() {
        return BaseApplication.getInstance().getApplicationContext();
    }

}
