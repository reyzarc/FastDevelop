package com.xtec.timeline.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 武昌丶鱼 on 2016/11/1.
 * Description:
 */
public class Utils {
    private static final String TAG = "Utils";

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String version = null;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = null;
            info = manager.getPackageInfo(context.getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取versionCode
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int version = 0;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = null;
            info = manager.getPackageInfo(context.getPackageName(), 0);
            version = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 判断手机上是否安装了某个应用
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }


    /**
     * 判断服务是否在运行
     *
     * @param context
     * @param serviceClass
     * @return
     */
    public static boolean isServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> list = am.getRunningServices(Integer.MAX_VALUE);
        if (list.size() == 0) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo service : list) {
            if (serviceClass.getSimpleName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isServiceRunning(Class<?> serviceClass, Context context) {
        boolean isServiceRunning = false;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                isServiceRunning = true;
            }
        }
        return isServiceRunning;
    }

    /**
     * 判断当前程序是否运行在前台
     *
     * @return
     */
    public static boolean isRunningForeground(Context context) {
        String packageName = getPackageName(context);
        String topActivityClassName = getTopActivityName(context);
        if (packageName != null && topActivityClassName != null
                && topActivityClassName.startsWith(packageName)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获得包名
     *
     * @param context
     * @return
     */
    public static String getPackageName(Context context) {
        String pkName = context.getPackageName();
        return pkName;
    }


    /**
     * 获得栈顶的activity名称
     *
     * @param context
     * @return
     */
    public static String getTopActivityName(Context context) {
        String topActivityClassName = null;
        ActivityManager activityManager = (ActivityManager) (context
                .getSystemService(android.content.Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager
                .getRunningTasks(1);
        if (runningTaskInfos != null) {
            ComponentName f = runningTaskInfos.get(0).topActivity;
            topActivityClassName = f.getClassName();
        }
        return topActivityClassName;
    }

    /**
     * 获取用户手机设备号
     *
     * @param context
     * @return
     */
    public static String getPhoneDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * 判断权限集合
     *
     * @param context
     * @param permissions
     * @return
     */
    public static boolean hasPermissions(Context context, String... permissions) {
        boolean hasPermission = false;
        for (String permission : permissions) {
            if (hasPermission(context, permission)) {
                hasPermission = true;
            } else {
                return false;
            }
        }
        return hasPermission;
    }

    /**
     * 判断是否缺少权限
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean hasPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) ==
                PackageManager.PERMISSION_GRANTED;
    }

    //当前应用是否处于前台
    public static boolean isForeground(Context context) {
        if (context != null) {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
            String currentPackageName = cn.getPackageName();
            if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 跳转到应用权限设置页面
     * @param context
     * @param code
     */
    public static void startAppSetting(Context context, int code) {
        Intent intent = new Intent();
        if (isMIUI()) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction("miui.intent.action.APP_PERM_EDITOR");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.putExtra("extra_pkgname", context.getPackageName());
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= 9) {
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", context.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                intent.setAction(Intent.ACTION_VIEW);
                intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
            }
        }
        ((Activity) context).startActivityForResult(intent, code);
    }

    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    /**
     * 判断是小米系统
     * @return
     */
    public static boolean isMIUI() {
        try {
            final BuildProperties prop = BuildProperties.newInstance();
            return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
        } catch (final IOException e) {
            return false;
        }
    }

    /**
     * 防止按钮重复触发点击事件
     * @return
     */
    private static long mLastClickTime = 0;
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - mLastClickTime;
        if ( 0 < timeD && timeD < 1000) {
            return true;
        }
        mLastClickTime = time;
        return false;
    }
}
