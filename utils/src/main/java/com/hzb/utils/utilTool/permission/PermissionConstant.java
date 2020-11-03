package com.hzb.utils.utilTool.permission;

import android.Manifest;

/**
 */

public class PermissionConstant {

    public static final int PERMISSION_REQUEST_CODE = 1000;

    public static final String[] MAIN_PERMISSIONS = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
    };

    public static final String[] LOGIN_PERMISSION = {Manifest.permission.GET_ACCOUNTS};

    public static final String[] LOCATION_PERMISSION = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};


    //存储
    public static final String[] EXTERNAL_STORAGE_PERMISSION = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    //相机
    public static final String[] CAMERA_PERMISSIONS = {Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

}
