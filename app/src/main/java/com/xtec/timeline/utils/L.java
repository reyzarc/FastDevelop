package com.xtec.timeline.utils;

import com.xtec.timeline.BuildConfig;

/**
 * Created by 武昌丶鱼 on 2016/10/18.
 * Description:
 */
public class L {
    private static final String TAG = "timeline";
    public static final boolean IS_DEBUG = BuildConfig.LOG_DEBUG;

    public static void v(String msg) {
        if (IS_DEBUG) {
            android.util.Log.v(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (IS_DEBUG) {
            android.util.Log.e(TAG, msg);
        }
    }

    public static void v(String flag, String msg) {
        if (IS_DEBUG) {
            android.util.Log.v(flag, msg);
        }
    }

    public static void e(String flag, String msg) {
        if (IS_DEBUG) {
            android.util.Log.e(flag, msg);
        }
    }

    public static void i(String msg) {
        if (IS_DEBUG) {
            android.util.Log.i(TAG, msg);
        }
    }

    public static void i(String flag, String msg) {
        if (IS_DEBUG) {
            android.util.Log.i(flag, msg);
        }
    }

}
