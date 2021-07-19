package com.hzb.myUniversalUtils.utilTool.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * 运行时权限检查和申请处理，为了方便处理权限请求结果该类继承于{@link Fragment}但是没有UI视图，
 * 使用时不要主动添加到{@link Activity}中，请使用相关静态方法，基本使用示例：
 * <p>
 * PermissionHandler.requestPermissionsIfNeed(MainActivity.this, new PermissionHandler.PermissionRequestCallback() {
 * <p>
 * public void showPermissionRationale(PermissionHandler.PermissionRequestInterface requestInterface) {
 * // 1. 显示权限说明弹窗
 * // 2. requestInterface.requestPermission();
 * }
 * <p>
 * public void onPermissionGranted() {
 * // 用户同意授权回调
 * }
 * <p>
 * public void onPermissionDenied(String[] permissions) {
 * // 用户拒绝授权回调
 * }
 * }, Manifest.permission.CAMERA);
 *
 * @author Created by jiangyujiang on 2018/3/2.
 */


/**
 *    PermissionHandler.requestPermissionsIfNeed(MainActivity.this, new PermissionHandler.PermissionRequestCallback() {
 *
 *             @Override
 *             public void showPermissionRationale(PermissionHandler.PermissionRequestInterface requestInterface) {
 * // 1. 显示权限说明弹窗
 *                 Log.e("TAG", "showPermissionRationale: 呵呵" );
 *                   requestInterface.requestPermission();
 * //                requestInterface.cancelPermissionRequest();
 *
 *
 *             }
 *
 *             @Override
 *             public void onPermissionGranted() {
 *                 // 用户同意授权回调
 *                 Toast.makeText(MainActivity.this, "用户授权成功", Toast.LENGTH_SHORT).show();
 *             }
 *
 *             @Override
 *             public void onPermissionDenied(String[] permissions) {
 *                 Toast.makeText(MainActivity.this, "请求权限失败" + permissions, Toast.LENGTH_SHORT).show();
 *                 // 用户拒绝授权回调
 *
 *                  new AppSettingsDialog.Builder(MainActivity.this).build().show();
 *             }
 *         }, PermissionConstant.CAMERA_PERMISSIONS);
 */

public class PermissionHandler extends Fragment {
    private static final String KEY_PERMISSIONS = "permissions";
    private static final String KEY_CALLBACK = "callback";

    private static final int PERMISSION_REQUEST_CODE = 1;

    private String[] mPermissions;
    private PermissionRequestCallback mRequestCallback;

    /**
     * 检查用户是否授权指定权限
     *
     * @param context     {@link Activity}当前界面
     * @param permissions 需要检查的权限
     * @return 如果所有权限都已被用户授权将返回true，否则返回false
     */
    public static boolean checkPermissions(Activity context, String... permissions) {
        if (permissions == null || permissions.length == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String p : permissions) {
            if (context.checkSelfPermission(p) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取指定权限中没有被用户授权的部分
     *
     * @param context     {@link Activity}当前界面
     * @param permissions 需要检查的权限
     * @return 返回指定权限中没有被用户授权的部分，如果全部授权将返回null
     */
    public static String[] getDeniedPermissions(Activity context, String... permissions) {
        if (permissions == null || permissions.length == 0) {
            return null;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return null;
        }
        List<String> deniedPermissions = new LinkedList<>();
        for (String p : permissions) {
            if (context.checkSelfPermission(p) != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(p);
            }
        }
        if (!deniedPermissions.isEmpty()) {
            String[] result = new String[deniedPermissions.size()];
            return deniedPermissions.toArray(result);
        }
        return null;
    }

    /**
     * 如果指定权限中有未授权的，将请求用户授权
     *
     * @param context     {@link Activity}当前界面
     * @param callback    {@link PermissionRequestCallback}权限授权请求回调
     * @param permissions 需要请求用户授权的权限
     * @return 如果需要请求授权将返回true，否则返回false
     */
    public static boolean requestPermissionsIfNeed(Activity context, PermissionRequestCallback callback, String... permissions) {
        String[] deniedPermissions = getDeniedPermissions(context, permissions);
        if (deniedPermissions == null || deniedPermissions.length == 0) {
            return false;
        }
        if (callback == null) {
            throw new IllegalArgumentException("权限请求回调不能为空");
        }
        internalPermissionsRequest(context, deniedPermissions, callback);
        return true;
    }

    private static void internalPermissionsRequest(Activity context, String[] permissions, PermissionRequestCallback callback) {
        PermissionHandler permissionHandler = new PermissionHandler();
        Bundle args = new Bundle();
        args.putStringArray(KEY_PERMISSIONS, permissions);
        args.putSerializable(KEY_CALLBACK, callback);
        permissionHandler.setArguments(args);
        context.getFragmentManager()
                .beginTransaction()
                .add(0, permissionHandler)
                .commitAllowingStateLoss();
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if (args == null) {
            removeFromActivity();
            return;
        }
        mPermissions = args.getStringArray(KEY_PERMISSIONS);
        args.remove(KEY_PERMISSIONS);
        mRequestCallback = (PermissionRequestCallback) args.getSerializable(KEY_CALLBACK);
        args.remove(KEY_CALLBACK);
        if (mPermissions == null || mRequestCallback == null) {
            removeFromActivity();
            return;
        }

        for (String p : mPermissions) {
            if (shouldShowRequestPermissionRationale(p)) {
                mRequestCallback.showPermissionRationale(new PermissionRequestInterface() {
                    @Override
                    public void requestPermission() {
                        requestPermissions(mPermissions, PERMISSION_REQUEST_CODE);
                    }

                    @Override
                    public void cancelPermissionRequest() {
                        removeFromActivity();
                    }
                });
                return;
            }
        }
        requestPermissions(mPermissions, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != PERMISSION_REQUEST_CODE) {
            removeFromActivity();
            return;
        }
        List<String> deniedPermissions = new LinkedList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[i]);
            }
        }
        if (deniedPermissions.isEmpty()) {
            mRequestCallback.onPermissionGranted();
        } else {
            String[] deniedArray = new String[deniedPermissions.size()];
            deniedArray = deniedPermissions.toArray(deniedArray);
            mRequestCallback.onPermissionDenied(deniedArray);
        }
        removeFromActivity();
    }

    private void removeFromActivity() {
        if (getActivity() != null) {
            getActivity().getFragmentManager()
                    .beginTransaction()
                    .remove(this)
                    .commitAllowingStateLoss();
        }
    }

    /**
     * {@link PermissionRequestCallback#showPermissionRationale(PermissionRequestInterface)}中的回调参数，
     * 向用户解释完权限用途后调用对应方法执行权限请求
     */
    public interface PermissionRequestInterface {
        /**
         * 继续请求权限
         */
        void requestPermission();

        /**
         * 取消权限请求
         */
        void cancelPermissionRequest();
    }

    public interface PermissionRequestCallback extends Serializable {
        /**
         * 向用户展示权限申请原因或者使用说明
         *
         * @param requestInterface {@link PermissionRequestInterface}
         */
        void showPermissionRationale(PermissionRequestInterface requestInterface);

        /**
         * 用户同意授权
         */
        void onPermissionGranted();

        /**
         * 用户拒绝授权
         *
         * @param permissions 被用户拒绝的权限
         */
        void onPermissionDenied(String[] permissions);
    }
}
