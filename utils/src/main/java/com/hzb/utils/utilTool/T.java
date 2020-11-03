package com.hzb.utils.utilTool;

import android.widget.Toast;

import com.hzb.utils.baseApplication.BaseApplication;


/**
 * FileName: T
 * Author: houzhengbang
 * Date: 2020-05-28 16:17
 * Description:Toast统一管理类
 */
public class T {

    private static Toast toast;

    private T() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    /**
     * 短时间显示Toast
     *
     * 
     * @param message
     */
    public static void showShort( CharSequence message) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_SHORT);
            } else {
                toast.setText(message);
            }
            toast.show();
        }
    }

    /**
     * 短时间显示Toast
     *
     * 
     * @param message
     */
    public static void showShort( int message) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_SHORT);
            } else {
                toast.setText(message);
            }
            toast.show();
        }
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * 
     * @param message
     */
    public static void showLong( CharSequence message) {
        if (isShow){
            if (toast == null) {
                toast = Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_LONG);
            } else {
                toast.setText(message);
            }
            toast.show();
        }
//            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * 
     * @param message
     */
    public static void showLong( int message) {
        if (isShow){
            if (toast == null) {
                toast = Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_LONG);
            } else {
                toast.setText(message);
            }
            toast.show();
        }
//            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * 
     * @param message
     * @param duration
     */
    public static void show( CharSequence message, int duration) {
        if (isShow)
            Toast.makeText(BaseApplication.getInstance(), message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * 
     * @param message
     * @param duration
     */
    public static void show( int message, int duration) {
        if (isShow)
            Toast.makeText(BaseApplication.getInstance(), message, duration).show();
    }

}