package com.hzb.myUniversalUtils.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hzb.myUniversalUtils.R;
import com.hzb.myUniversalUtils.utilTool.StatusBarUtil;
import com.hzb.myUniversalUtils.utilTool.activityManager.AppManager;
import com.hzb.myUniversalUtils.utilTool.permission.PermissionUtils;

import io.reactivex.disposables.CompositeDisposable;

/**
 * activity基类
 * todo://
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //设置状态栏颜色
        setStatusBarColor("#000000");

        super.onCreate(savedInstanceState);

        AppManager.getInstance().addActivity(this);
        //设置 layout
        int layoutResId = getLayoutResId();
        if (layoutResId > 0) {
            super.setContentView(layoutResId);
        }

        //标题栏 返回
        ImageView head_back = findViewById(R.id.ac_head_back);
        if (head_back != null) {
            head_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }

        initView(savedInstanceState);

        initData(getIntent(), savedInstanceState);

        //网络请求
        mCompositeDisposable = new CompositeDisposable();

    }


    @Override
    protected void onPause() {
        post(new OnPause());
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        post(new OnDestroy());
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);

        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear(); // clear时网络请求会随即cancel
            mCompositeDisposable = null;
        }
    }

    @Override
    protected void onResume() {
        post(new BaseActivity.OnResume());
        super.onResume();
    }


    /**
     * 获取布局资源id
     *
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化View
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    protected abstract void initData(Intent intent, Bundle savedInstanceState);


    private class BaseActivityEvent {
        public Activity from;
        public String fromName;
    }

    private void post(BaseActivity.BaseActivityEvent event) {
        String className = this.getClass().getName();
        if (!TextUtils.isEmpty(className)) {
            event.from = this;
            event.fromName = className;
        }
    }

    public class OnDestroy extends BaseActivity.BaseActivityEvent {
    }

    public class OnResume extends BaseActivity.BaseActivityEvent {
    }

    public class OnPause extends BaseActivity.BaseActivityEvent {
    }


    //当我们的屏幕方向发生改变时，就可以触发onConfigurationChanged事件。我们要想当前的activity捕获这个事件，需要做以下这么几件事情。
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        post(new BaseActivity.OnConfigurationChanged(newConfig));
        super.onConfigurationChanged(newConfig);
    }

    public class OnConfigurationChanged extends BaseActivity.BaseActivityEvent {
        public Configuration newConfig;

        public OnConfigurationChanged(Configuration newConfig) {
            this.newConfig = newConfig;
        }
    }


    //携带数据返回 结果回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        post(new BaseActivity.OnActivityResult(requestCode, resultCode, data));
        super.onActivityResult(requestCode, resultCode, data);
    }

    //权限授权 结果 回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.callbackRequestPermissionResult(requestCode, permissions, grantResults, getPermissionCallback());
    }

    protected Object getPermissionCallback() {
        return this;
    }

    public class OnActivityResult extends BaseActivity.BaseActivityEvent {
        public int requestCode;
        public int resultCode;
        public Intent data;

        public OnActivityResult(int requestCode, int resultCode, Intent data) {
            this.requestCode = requestCode;
            this.resultCode = resultCode;
            this.data = data;
        }
    }


    //设置标题
    public void setTitle(String title) {
        TextView titleTextView = findViewById(R.id.ac_head_title);
        if (titleTextView != null) {
            titleTextView.setText(title);
        }
    }

    //退出
    public void clickClose(View view) {
        finish();
    }

    //设置横竖屏  true
    public void directionScreen(boolean type) {
        if (type) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
        }
    }

    //设置状态栏颜色
    public void setStatusBarColor(String color) {
        String colors;
        if (TextUtils.isEmpty(color)) {
            colors = "#000000";
        } else {
            colors = color;
        }
        StatusBarUtil.setColor(this, Color.parseColor(colors));
    }

    //系统UI标志隐藏导航 状态栏
    public void hideNavigationBar() {
        Window _window = getWindow();
        WindowManager.LayoutParams params = _window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        _window.setAttributes(params);

    }
}
