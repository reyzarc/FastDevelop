package com.xtec.timeline.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xtec.timeline.R;

/**
 * Created by 武昌丶鱼 on 2016/10/21.
 * Description:
 */
public class UIUtils {
    private static final String TAG = "UIUtils";


    /**
     * 初始化非activity的topbar
     * @param context
     * @param topBar
     * @param isTransparent 是否透明
     */
    public static void initTopbar(final Context context, View topBar, boolean isTransparent) {
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.setMargins(0, getStatusBarHeight(context),topBar.getMeasuredWidth(),topBar.getMeasuredHeight());
//        topBar.setLayoutParams(params);
        //手动增加状态栏高度
        ViewGroup.LayoutParams params = topBar.getLayoutParams();
        params.height = getStatusBarHeight(context)+dip2px(context,50);
        topBar.setLayoutParams(params);
        if(isTransparent){
            topBar.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        }else{
            topBar.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context){
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 获取topbar的高度
     * @param context
     * @return
     */
    public static int getTopBarHeight(Context context){
        return getStatusBarHeight(context)+dip2px(context,50);
    }


    /**
     * 将px值转换为dip或dp值
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * 初始化activity的topbar
     * @param context
     * @param topbar
     * @param isEnableBack
     */
    public static void initTopbarForActivity(final Context context, View topbar, boolean isEnableBack) {
        ViewGroup.LayoutParams params = topbar.getLayoutParams();
        params.height = getStatusBarHeight(context)+dip2px(context,50);
        topbar.setLayoutParams(params);
        topbar.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

        if(isEnableBack){//有返回键
            ImageButton btn  = (ImageButton) topbar.findViewById(R.id.topbar_left);
            btn.setImageResource(R.drawable.ic_back_arrow);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Activity)context).finish();
                }
            });
        }
    }

    /**
     * 动态添加小圆点指示器,这种方式添加的小圆点不能跟随滑动
     *
     * @param context
     * @param dotsContainer 装载小圆点的容器
     * @param position      选中页的位置
     * @param count          小圆点个数
     * @param normalImgRes  正常显示的小圆点图标
     * @param focusImgRes   选中后小圆点的图标
     */
    public static void addNavigationDot(Context context, LinearLayout dotsContainer,
                                        int position, int count, int normalImgRes,
                                        int focusImgRes) {
        if (count > 1) {
            dotsContainer.removeAllViews();
            for (int i = 0; i < count; i++) {
                ImageView iv = new ImageView(context);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dip2px(context, 7), dip2px(context, 7));
                layoutParams.rightMargin = 10;
                layoutParams.leftMargin = 10;
                layoutParams.bottomMargin=10;
                iv.setLayoutParams(layoutParams);
                if (i == position) {
                    iv.setImageDrawable(context.getResources().getDrawable(focusImgRes));
                } else {
                    iv.setImageDrawable(context.getResources().getDrawable(normalImgRes));
                }
                dotsContainer.addView(iv);
            }
        }
    }
}
