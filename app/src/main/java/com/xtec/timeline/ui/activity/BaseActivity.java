package com.xtec.timeline.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.xtec.timeline.utils.UIUtils;
import com.xtec.timeline.widget.swipeBackLayout.SwipeBackActivity;
import com.xtec.timeline.widget.swipeBackLayout.SwipeBackLayout;

/**
 * Created by 武昌丶鱼 on 2016/10/19.
 * Description:activity基类
 */
public class BaseActivity extends SwipeBackActivity{
    private static final String TAG = "BaseActivity";

    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置是否开启手势滑动返回
//        getSwipeBackLayout().setEnableGesture(true);
        //设置是否全屏滑动返回
//        getSwipeBackLayout().setSwipeMode(SwipeBackLayout.FULL_SCREEN_LEFT);
        //设置滑动返回触发位置
        getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        //设置状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
//            getActivity().getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏和导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams
                    .FLAG_FULLSCREEN);
        }
    }

    /**
     * 初始化toolbar
     * @param toolbar
     * @param isShowBackIcon
     */
    public void initToolbar(Toolbar toolbar,boolean isShowBackIcon){
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(isShowBackIcon);
        }
    }

    /**
     * 进度框,可根据需求自定义
     * @return
     */
    public ProgressDialog getProgressDialog(){
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }
        return mProgressDialog;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mProgressDialog!=null){
            mProgressDialog.dismiss();
        }
    }

    /**
     * 初始化topbar
     * @param context 上下文
     * @param topbar
     */
    public void initTopbar(Context context,View topbar){
        UIUtils.initTopbarForActivity(context,topbar);
    }

    /**
     * 初始化topbar
     * @param context 上下文
     * @param topbar
     * @param isEnableBack 是否激活返回按钮
     */
    public void initTopbar(Context context,View topbar,boolean isEnableBack){
        UIUtils.initTopbarForActivity(context,topbar,isEnableBack);
    }

    /**
     * 初始化topbar,带topbar的颜色
     * @param context 上下文
     * @param topbar
     * @param isEnableBack 是否激活返回按钮
     */
    public void initTopbar(Context context,View topbar,int color,boolean isEnableBack){
        UIUtils.initTopbarForActivity(context,topbar,color,isEnableBack);
    }
}
