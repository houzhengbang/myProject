package com.hzb.utils.net.Utils;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.bigkoo.svprogresshud.listener.OnDismissListener;

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
    private SVProgressHUD svProgressHUD;


    private boolean isShowSVProgressHUD = true;

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

            if (isShowSVProgressHUD) {
                if (svProgressHUD == null && mShowDialog == true) {
                    svProgressHUD = new SVProgressHUD(mContext);
                    svProgressHUD.setOnDismissListener(new OnDismissListener() {
                        @Override
                        public void onDismiss(SVProgressHUD hud) {
                            // 对话框取消 直接停止执行请求
                            if (!d.isDisposed()) {
                                d.dispose();
                            }
                        }
                    });
                    svProgressHUD.showWithStatus("加载中", SVProgressHUD.SVProgressHUDMaskType.ClearCancel);
                }
            } else {

                if (dialog == null && mShowDialog == true) {
                    dialog = new ProgressDialog(mContext);
                    dialog.setMessage("正在加载中");
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
    }

    @Override
    public void onError(Throwable e) {
        if (d.isDisposed()) {
            d.dispose();
        }
        hidDialog();
        super.onError(e);
    }

    @Override
    public void onComplete() {
        if (d.isDisposed()) {
            d.dispose();
        }
        hidDialog();
        super.onComplete();
    }


    public void hidDialog() {

        if (isShowSVProgressHUD) {
            if (svProgressHUD != null && mShowDialog == true)
                svProgressHUD.dismiss();
            svProgressHUD = null;
        } else {
            if (dialog != null && mShowDialog == true)
                dialog.dismiss();
            dialog = null;
        }

    }

    /**
     * 是否有网络连接，不管是wifi还是数据流量
     *
     * @param context
     * @return
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
            hidDialog();
        }
    }
}

