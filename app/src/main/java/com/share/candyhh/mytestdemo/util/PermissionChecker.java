package com.share.candyhh.mytestdemo.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mory on 2016/7/21.
 * 适用系统6.0以上的动态权限获取功能
 * 当前类只是检测是否已经申请某个权限
 */

public class PermissionChecker {

    private final Context mContext;
    private List<String> deniedPermissions = new ArrayList<>();
    private boolean isLackPermission = false;


    public PermissionChecker(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

    /**
     * 判断权限集合
     */
    public boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                deniedPermissions.add(permission);
                isLackPermission = true;
            }
        }
        return isLackPermission;
    }

    /**
     * 判断是否缺少权限
     */
    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_DENIED;
    }

    public List<String> getDeniedPermissions() {
        return deniedPermissions;
    }
}