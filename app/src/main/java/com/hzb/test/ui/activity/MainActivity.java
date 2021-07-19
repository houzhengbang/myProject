package com.hzb.test.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.hzb.test.R;
import com.hzb.test.ui.fragment.MainFragment;
import com.hzb.test.update.AppDownloadUtils;
import com.hzb.myUniversalUtils.base.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("main");

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {


//        LogUtil.e("ddddd");

        // //替换 或 添加
        MainFragment mainFragment1 = new MainFragment();
        switchFragment(mainFragment1, "s");

//
//        //回退的时候显示上一个Fragment
//        MainFragment mainFragment2 = new MainFragment();
//        pushFragment(mainFragment2);
    }

    //替换
    public void switchFragment(Fragment fragment, String tag) {
        Bundle bundle = fragment.getArguments();
        bundle = getBundle(bundle);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (!TextUtils.isEmpty(tag)) {
            fragmentManager.beginTransaction().replace(R.id.fragment_layout, fragment, tag).commit();
        } else {
            fragmentManager.beginTransaction().replace(R.id.fragment_layout, fragment).commit();
        }
    }

    //对于是否要加transaction.addToBackStack(null);也就是将Fragment加入到回退栈。
    // 官方的说法是取决于你是否要在回退的时候显示上一个Fragment。
    public void pushFragment(Fragment fragment, String tag) {
        Bundle bundle = fragment.getArguments();
        bundle = getBundle(bundle);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (!TextUtils.isEmpty(tag)) {
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_layout, fragment)
                    .addToBackStack(null)
                    .show(fragment).commit();

        } else {
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_layout, fragment)
                    .addToBackStack(null)
                    .show(fragment).commit();
        }
    }

    public Bundle getBundle(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("CLAZZID", "");
        bundle.putSerializable("CURRENTVOLUME", null);
        return bundle;
    }


//    private void getDatas() {
//
//        LoginBody body = new LoginBody("18434395102", "111", "");
//        RequestApiUtils.getData(this, MyApplication.getRetrofitUtils().sendss(body), new MyObserver<LoginBean>(this) {
//
//            @Override
//            public void onSuccess(LoginBean result) {
////                Log.e("MainActivity", "onSuccess: " + result);
//            }
//
//            @Override
//            public void onFailure(Throwable e, String errorMsg) {
////                Log.e("MainActivity", "onFailure: " + errorMsg);
//
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private String url = "http://apk-cdn.zhangxinhulian.com/com.zxhl.weather-guanwang-1.0.6_106_jiagu.apk";

    public void xx(View view) {

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    2);
        } else {
            AppDownloadUtils.getInstance(this).setDownUrl(url).start();
        }
    }


    public void stop(View view) {
        AppDownloadUtils.getInstance(this).stop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 8.0以上版本安装apk 获取未知来源为true才会继续下载安装
        if (requestCode == AppDownloadUtils.REQUEST_CODE_APP_INSTALL) {
//            if (StringUtils.isEmpty(url)) return;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (AppDownloadUtils.isHasInstallPermissionWithO(this)) {
                    if (AppDownloadUtils.getInstance(this).getmFile() != null) {
                        AppDownloadUtils.getInstance(this).installApk(this, AppDownloadUtils.getInstance(this).getmFile(), AppDownloadUtils.getInstance(this).getFilePath());
                    } else {
                        AppDownloadUtils.getInstance(this).setDownUrl(url).start();
                    }
                }
            }
        }
    }
}