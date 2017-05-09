package com.xtec.timeline.common;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by 武昌丶鱼 on 2016/10/26.
 * Description:
 */
public class BaseApplication extends Application{
    private static final String TAG = "BaseApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}
