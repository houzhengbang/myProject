package com.hzb.utils.util.permission;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hzb.utils.R;
import com.hzb.utils.util.BaseTypeUtils;

import java.util.Arrays;

import pub.devrel.easypermissions.EasyPermissions;


/**
 *
 implements EasyPermissions.PermissionCallbacks

 PermissionUtils.applyPermission(MainActivity.this, PermissionConstant.CAMERA_PERMISSIONS);
 PermissionUtils.applyPermission(MainActivity.this,"zzzzzzz", PermissionConstant.CAMERA_PERMISSIONS);


 //用户授权成功。
 @Override
 public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
 Toast.makeText(this, "用户授权成功", Toast.LENGTH_SHORT).show();
 }

 //请求权限失败。
 @Override
 public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
 Toast.makeText(this, "请求权限失败", Toast.LENGTH_SHORT).show();

 //若是在权限弹窗中，用户勾选了'NEVER ASK AGAIN.'或者'不在提示'，且拒绝权限。 这时候，需要跳转到设置界面去，让用户手动开启。
 if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
 new AppSettingsDialog.Builder(this).build().show();
 }
 }
 */


public class PermissionUtils {

    private PermissionUtils() {

    }

    public static boolean applyPermission(@NonNull Object host, @NonNull String[] permissions) {
        return applyPermission(host, "", permissions);
    }

    /**
     * 申请权限
     *
     * @param host 宿主类，必须是Activity或Fragment
     * @param rationale 用于描述申请该权限的原因, 如果要显示原因，需要实现 {@link Activity#shouldShowRequestPermissionRationale(String)}
     *                  And {@link Fragment#shouldShowRequestPermissionRationale(String)} 方法，并返回true，默认不显示
     * @param permissions
     * @return true 权限已获取不必申请
     */
    public static boolean applyPermission(@NonNull Object host, @NonNull String rationale, @NonNull String[] permissions) {
        if (BaseTypeUtils.isArrayEmpty(permissions)) {
            return true;
        }

        Context context;
        if (host instanceof Activity) {
            context = (Context) host;
            if (EasyPermissions.hasPermissions(context, permissions)) {
                return true;
            } else {
                EasyPermissions.requestPermissions((Activity) host, getRationale(context, permissions, rationale), PermissionConstant.PERMISSION_REQUEST_CODE, permissions);
            }
        } else if (host instanceof Fragment) {
            context = ((Fragment) host).getContext();
            if (context == null) {
                return false;
            }
            if (EasyPermissions.hasPermissions(context, permissions)) {
                return true;
            } else {
                EasyPermissions.requestPermissions((Fragment) host, getRationale(context, permissions, rationale), PermissionConstant.PERMISSION_REQUEST_CODE, permissions);
            }
        } else if (host instanceof android.app.Fragment) {
            context = ((android.app.Fragment) host).getActivity();
            if (context == null) {
                return false;
            }
            if (EasyPermissions.hasPermissions(context, permissions)) {
                return true;
            } else {
                EasyPermissions.requestPermissions((Fragment) host, getRationale(context, permissions, rationale), PermissionConstant.PERMISSION_REQUEST_CODE, permissions);
            }
        }

        return false;
    }

    private static String getRationale(@NonNull Context context, @NonNull String[] permissions, @NonNull String rationale) {
        if (!TextUtils.isEmpty(rationale)) {
            return rationale;
        }
        if (Arrays.equals(permissions, PermissionConstant.CAMERA_PERMISSIONS)) {
            rationale = context.getString(R.string.text_camera_permission_rationale);
        } else if (Arrays.equals(permissions, PermissionConstant.EXTERNAL_STORAGE_PERMISSION)) {
            rationale = context.getString(R.string.text_external_permission_rationale);
        }
        return rationale;
    }

    public static void callbackRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, @NonNull Object... receivers) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, receivers);
    }

}
