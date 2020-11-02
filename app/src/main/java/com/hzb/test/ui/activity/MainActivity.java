package com.hzb.test.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.hzb.test.R;
import com.hzb.test.app.MyApplication;
import com.hzb.test.entity.LoginBean;
import com.hzb.test.entity.LoginBody;
import com.hzb.test.net.RequestApiUtils;
import com.hzb.test.ui.fragment.MainFragment;
import com.hzb.utils.base.Base2Activity;
import com.hzb.utils.net.Utils.MyObserver;
import com.hzb.utils.util.LogUtil;
import com.orhanobut.logger.Logger;

public class MainActivity extends Base2Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

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


    private void getDatas() {

        LoginBody body = new LoginBody("18434395102", "111", "");
        RequestApiUtils.getData(this, MyApplication.getRetrofitUtils().sendss(body), new MyObserver<LoginBean>(this) {

            @Override
            public void onSuccess(LoginBean result) {
//                Log.e("MainActivity", "onSuccess: " + result);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
//                Log.e("MainActivity", "onFailure: " + errorMsg);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void xx(View view) {

        getDatas();
    }
}