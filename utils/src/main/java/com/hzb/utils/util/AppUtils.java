package com.hzb.utils.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;

import androidx.core.content.ContextCompat;

import com.hzb.utils.baseApplication.ApplicationContextHolder;
import com.hzb.utils.baseApplication.BaseApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;


@SuppressLint("MissingPermission")
public class AppUtils {

    private AppUtils() {

    }

    private static final String FILE_PROVIDER_SUFFIX = ".FileProvider";

    private static String sDeviceId;

    private static int sVersionCode;

    private static String sVersionName;

    private static String sAuthority;


    /**
     * 获取客户端版本号
     */
    public static int getClientVersionCode() {
        if (sVersionCode != 0) {
            return sVersionCode;
        }
        try {
            PackageInfo packInfo = BaseApplication.getInstance().getPackageManager()
                    .getPackageInfo(BaseApplication.getInstance().getPackageName(), 0);
            sVersionCode = packInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sVersionCode;
    }

    /**
     * 获取客户端版本名
     */
    public static String getClientVersionName() {
        if (!TextUtils.isEmpty(sVersionName)) {
            return sVersionName;
        }

        try {
            PackageInfo packInfo = BaseApplication.getInstance().getPackageManager()
                    .getPackageInfo(BaseApplication.getInstance().getPackageName(), 0);
            sVersionName = packInfo.versionName;
            return sVersionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String getAuthority() {
        if (!TextUtils.isEmpty(sAuthority)) {
            return sAuthority;
        }
        sAuthority = getPackageName(ApplicationContextHolder.getContext()) + FILE_PROVIDER_SUFFIX;
        return sAuthority;
    }

    /**
     * 获取系统版本号
     */
    public static String getOSVersionName() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取系统SDK版本
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 判断系统是否是7.0及以上
     */
    public static boolean isNougat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    /**
     * 判断系统是否是6.0及以上
     */
    public static boolean isMashMellow() {
        return getSDKVersion() >= Build.VERSION_CODES.M;
    }

    /**
     * 判断系统是否是5.0及以上
     */
    public static boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isPreLollipop() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * 判断系统是否是4.4及以上
     */
    public static boolean isKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static String getMIUIVersion() {
        return getSystemProperties("ro.miui.ui.version.name");
    }


    /**
     * 获取进程名称
     *
     * @param context
     * @param pid
     * @return
     */
    public static String getProcessName(Context context, int pid) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return null;
        }
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo processInfo : runningApps) {
            if (processInfo.pid == pid) {
                return processInfo.processName;
            }
        }
        return null;
    }


    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static String getSystemProperties(String propName) {
        String line = "";
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return line;
    }

    public static boolean checkPermissionGranted(Context context, String permission) {
        if (isMashMellow()) {
            return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    public static void setCookie(String url, String cookie, WebView webView) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(webView.getContext());
        }

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, cookie);//cookies是在HttpClient中获得的cookie

        String newCookie = cookieManager.getCookie(url);
        CookieSyncManager.getInstance().sync();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
    }

    public static String getRandomStr() {
        String random = "";
        try {
            random = String.valueOf(Math.abs(new Random().nextInt()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(random)) {
            random = String.valueOf(System.currentTimeMillis());
        }
        return random;
    }

    public static boolean isFirstOpen() {
        boolean firstOpen = false;
        try {
            firstOpen = SharedPreferenceUtils.getFirstOpen(ApplicationContextHolder.getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return firstOpen;
    }

    public static void setFirstOpen(boolean isFirstLogin) {
        try {
            SharedPreferenceUtils.setFirstOpen(ApplicationContextHolder.getContext(), isFirstLogin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
