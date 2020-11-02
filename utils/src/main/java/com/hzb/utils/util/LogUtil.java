package com.hzb.utils.util;

import com.hzb.utils.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * FileName: LogUtil
 * Author: houzhengbang
 * Date: 2020/10/31 7:48 PM
 * Description:
 */
public class LogUtil {
    /**
     * 注意：打包的时候记得设置isDebug为false
     */
    public static boolean isDebug = BuildConfig.DEBUG;

    public static void e(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).e(msg + "");
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).d(msg + "");
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).i(msg + "");
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).v(msg + "");
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Logger.t(tag).w(msg + "");
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Logger.e(msg + "");
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Logger.d(msg + "");
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Logger.i(msg + "");
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Logger.v(msg + "");
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Logger.w(msg + "");
        }
    }

    public static void json(String json) {
        if (isDebug) {
            Logger.json(json);
        }
    }

    public static void xml(String xml) {
        if (isDebug) {
            Logger.xml(xml);
        }
    }

    public static void wtf(String wtf) {
        if (isDebug) {
            Logger.wtf(wtf);
        }
    }
} 