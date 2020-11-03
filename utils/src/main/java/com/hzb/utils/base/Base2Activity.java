package com.hzb.utils.base;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hzb.utils.R;
import com.hzb.utils.utilTool.StatusBarUtil;
import com.hzb.utils.utilTool.permission.PermissionUtils;

/**
 * activity基类
 * todo://
 */
public abstract class Base2Activity extends AppCompatActivity {


    @Override
    protected void onPause() {
        post(new OnPause());
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        post(new OnDestroy());
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        post(new Base2Activity.OnResume());
        super.onResume();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


//        //系统UI标志隐藏导航 状态栏
//        Window _window = getWindow();
//        WindowManager.LayoutParams params = _window.getAttributes();
//        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE;
//        _window.setAttributes(params);


//        //设置横屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);


//        //设置状态栏颜色
        StatusBarUtil.setColor(this, Color.parseColor("#000000"));

        super.onCreate(savedInstanceState);

        int layoutResId = getLayoutResId();
        if (layoutResId > 0) {
            super.setContentView(layoutResId);
        }


        //加载框
//        SVProgressHUD svProgressHUD = new SVProgressHUD(this);
//
//        svProgressHUD.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss(SVProgressHUD hud) {
//
//            }
//        });
//        svProgressHUD.showWithStatus("加载中",SVProgressHUD.SVProgressHUDMaskType.ClearCancel);


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


//
//        Log.e("sub","w"+ DisplayUtils.getScreenWidth(Base2Activity.this) + " h = "+DisplayUtils.getScreenHeight(Base2Activity.this));
//
//
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
//        int dwidth = dm.widthPixels;
//        int dheight = dm.heightPixels;
//
//        Log.e("sub","w"+dwidth+ " h = "+dheight);
//


    }

    public void setTitle(String title) {
        TextView titleTextView = findViewById(R.id.ac_head_title);
        if (titleTextView != null) {
            titleTextView.setText(title);
        }
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


    public class BaseActivityEvent {
        public Activity from;
        public String fromName;
    }

    private void post(Base2Activity.BaseActivityEvent event) {
        String className = this.getClass().getName();
        if (!TextUtils.isEmpty(className)) {
            event.from = this;
            event.fromName = className;
        }
    }

    public class OnDestroy extends Base2Activity.BaseActivityEvent {
    }

    public class OnResume extends Base2Activity.BaseActivityEvent {
    }

    public class OnPause extends Base2Activity.BaseActivityEvent {
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        post(new Base2Activity.OnConfigurationChanged(newConfig));
        super.onConfigurationChanged(newConfig);
    }

    public class OnConfigurationChanged extends Base2Activity.BaseActivityEvent {
        public Configuration newConfig;

        public OnConfigurationChanged(Configuration newConfig) {
            this.newConfig = newConfig;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        post(new Base2Activity.OnActivityResult(requestCode, resultCode, data));
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.callbackRequestPermissionResult(requestCode, permissions, grantResults, getPermissionCallback());
    }

    protected Object getPermissionCallback() {
        return this;
    }

    public class OnActivityResult extends Base2Activity.BaseActivityEvent {
        public int requestCode;
        public int resultCode;
        public Intent data;

        public OnActivityResult(int requestCode, int resultCode, Intent data) {
            this.requestCode = requestCode;
            this.resultCode = resultCode;
            this.data = data;
        }
    }


    public void clickClose(View view) {
        finish();
    }

//    public void clickRouteToCurrrentPlay(View view){
//        if (KtvSession.checkLogin()) {
//            ARouter.getInstance().build(PathConfig.ACTIVITY_GROUP_PLAY_LIST).navigation();
//        } else {
//            ARouter.getInstance().build(PathConfig.ACTIVITY_GROUP_USER_LOGIN_SMS).navigation();
//        }
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
//            if(mSVProgressHUD.isShowing()){
//                mSVProgressHUD.dismiss();
//                mHandler.removeCallbacksAndMessages(null);
//                return false;
//            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
