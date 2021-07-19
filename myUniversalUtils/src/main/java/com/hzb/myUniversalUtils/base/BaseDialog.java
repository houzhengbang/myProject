package com.hzb.myUniversalUtils.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

/**
 * FileName: BaseDialog
 * Author: houzhengbang
 * Description:
 */
public abstract class BaseDialog extends Dialog {

    protected BaseDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//            setContentView(R.layout.dialog_weike_06);
        //设置 layout
        int layoutResId = getLayoutResId();
        if (layoutResId > 0) {
            super.setContentView(layoutResId);
        }

        //按空白处不能取消动画
        setCanceledOnTouchOutside(true);
        //设置点击返回键不消失
        setCancelable(true);

//        Window dialogWindow = getWindow();
//        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.main_menu_animStyle); // 添加动画
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        lp.width = mContext.getResources().getDisplayMetrics().widthPixels; // 宽度
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//        dialogWindow.setAttributes(lp);


        initHideBottomNavInner();
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();

    }

    //隐藏 底部虚拟导航
    private void initHideBottomNavInner() {
        Window window = getWindow();
        if (window == null || window.getDecorView() == null) {
            return;
        }
        //不加FLAG_NOT_FOCUSABLE，dialog显示时就会显示虚拟按键
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        setOnShowListener(d -> {
            //dialog显示之后，要清除FLAG_NOT_FOCUSABLE，否则不会弹出软键盘
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        });

        window.getDecorView().setOnSystemUiVisibilityChangeListener(visibility -> {
            //从后台重新进入时，要再次隐藏虚拟按键
            hideBottomNavInner();

        });
        //隐藏虚拟按键
        hideBottomNavInner();
    }

    private void hideBottomNavInner() {
        View decorView = getWindow().getDecorView();
        int vis = decorView.getSystemUiVisibility();
        vis |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(vis);
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
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化界面的确定和取消监听器
     */
    protected abstract void initEvent();

//    /**
//     * 从外界Activity为Dialog设置标题
//     *
//     * @param title
//     */
//
//    protected void setTitle(String title) {
//
//    }
//
//    /**
//     * 从外界Activity为Dialog设置dialog的message
//     *
//     * @param message
//     */
//    protected void setMessage(String message) {
//
//    }
}