package com.hzb.test.update;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * FileName: MyInstalledReceiver
 * Author: houzhengbang
 * Date: 2020/11/2 3:57 PM
 * Description:
 */
public class MyInstalledReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //接收安装广播
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
            String packageName = intent.getDataString();
//            LogUtils.e("安装了:" +packageName + "包名的程序");
            Log.e("TAG", "onReceive: "+"安装了:" +packageName + "包名的程序" );
            AppDownloadUtils.getInstance(context).deleteFile();
        }
    }
}