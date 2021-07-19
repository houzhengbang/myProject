package com.hzb.myUniversalUtils.net.Utils;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hzb.myUniversalUtils.aRouterPath.PathManagement;
import com.hzb.myUniversalUtils.utilTool.activityManager.AppManager;

import io.reactivex.disposables.Disposable;

/**
 * Observer加入加载框
 *
 * @param <T>
 */

public abstract class MyObserver<T> extends BaseObserver<T> {
    private boolean mShowDialog;
    private ProgressDialog dialog;
    private Context mContext;
    private Disposable d;


    public MyObserver(Context context, Boolean showDialog) {
        mContext = context;
        mShowDialog = showDialog;
    }

    public MyObserver(Context context) {
        this(context, true);
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        if (!isConnected(mContext)) {
            Toast.makeText(mContext, "未连接网络", Toast.LENGTH_SHORT).show();
            if (d.isDisposed()) {
                d.dispose();
            }
        } else {

                if (dialog == null && mShowDialog == true) {
                    dialog = new ProgressDialog(mContext);
                    dialog.setMessage("加载中");
                    dialog.setIndeterminate(true);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            // 对话框取消 直接停止执行请求
                            if (!d.isDisposed()) {
                                d.dispose();
                            }
                        }
                    });
                    dialog.show();
                }
            }
    }

    @Override
    public void onError(Throwable e) {
        cancleRequest();
        super.onError(e);
    }

    @Override
    public void onComplete() {
        cancleRequest();
        super.onComplete();
    }

    @Override
    public void onNext(T t) {
        super.onNext(t);
        //加判空，防止网络请求返回空的情况
        if (t != null && t instanceof BaseResponse) {
            BaseResponse baseBean = (BaseResponse) t;
            if (baseBean.getErrorCode().equals("5026")) {
                onSignOut();
            }
        }
    }

    public void hidDialog() {


            if (dialog != null && mShowDialog == true)
                dialog.dismiss();
            dialog = null;


    }

    /**
     * 是否有网络连接，不管是wifi还是数据流量
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        boolean available = info.isAvailable();
        return available;
    }

    /**
     * 取消订阅
     */
    public void cancleRequest() {
        if (d != null && d.isDisposed()) {
            d.dispose();

        }
        hidDialog();
    }
    /**
     * 被踢下线处理
     */
    public void onSignOut() {
        AppManager.getInstance().finishAllActivity();// 销毁Activity

        //@Route(path = PathManagement.ACTIVITY_URL_LOGIN )
        ARouter.getInstance().build(PathManagement.ACTIVITY_URL_LOGIN).navigation();
    }
}

