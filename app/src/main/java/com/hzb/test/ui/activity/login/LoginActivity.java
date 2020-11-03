package com.hzb.test.ui.activity.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.hzb.test.R;
import com.hzb.test.ui.activity.MainActivity;
import com.hzb.utils.base.Base2Activity;
import com.hzb.utils.utilTool.SharedPreferenceUtils;


/**
 * 登录页面
 * --------M层   对P层传递过来的userInfo进行登录(网络请求)判断,处理完成之后将处理结果回调给P层
 * --------P层   传递完数据给M层处理之后,实例化回调对象,成功了就通知V层登录成功,失败了就通知V层显示错误信息
 * --------V层   负责响应用户的交互(获取数据---->提示操作结果)
 */

/**
 * @author Created by houzhengbang
 * @description
 * @desc :登录页
 */
public class LoginActivity extends Base2Activity implements LoginControl.ILoginView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new LoginPresenter(this);
    }

    @Override
    public Context getContent() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {

    }

    public void login(View view) {

        SharedPreferenceUtils.setIsLogin(this, true);

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}
