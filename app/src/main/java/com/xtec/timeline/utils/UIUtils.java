package com.xtec.timeline.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xtec.timeline.R;
import com.xtec.timeline.widget.PtrHeader;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;

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
     */
    public static void initTopbarForActivity(final Context context, View topbar) {
        ViewGroup.LayoutParams params = topbar.getLayoutParams();
        params.height = getStatusBarHeight(context)+dip2px(context,50);
        topbar.setLayoutParams(params);
        topbar.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
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
    }

    /**
     * 初始化activity的topbar,带状态栏颜色
     * @param context
     * @param topbar
     * @param color
     * @param isEnableBack
     */
    public static void initTopbarForActivity(final Context context, View topbar,int color, boolean isEnableBack) {
        ViewGroup.LayoutParams params = topbar.getLayoutParams();
        params.height = getStatusBarHeight(context)+dip2px(context,50);
        topbar.setLayoutParams(params);

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

    /**
     * 设置textView中的文字局部颜色
     *
     * @param context
     * @param textView
     * @param content   要设置的文本
     * @param textColor 要设置的颜色
     * @param from      从第几个字符开始（0开始计数，中文1个字为一字符，英文一个字母为一字符，空格也计算）
     * @param length    要设置的字符长度
     */
    public static void setTextColor(Context context, TextView textView, String content, int textColor, int from, int length) {
        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        ForegroundColorSpan span = new ForegroundColorSpan(context.getResources().getColor(textColor));
        if(from<content.length()&&from+length<=content.length()){
            builder.setSpan(span, from,from+length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(builder);
        }else{
            textView.setText(content);
        }
    }


    /**
     * 下拉刷新的头部布局
     * @param context
     * @param ptrFrame
     */
    public static void ptrFrameAddHeader(Context context, PtrClassicFrameLayout ptrFrame) {

        // header
        final PtrHeader header = new PtrHeader(context);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, dip2px(context, 15), 0, dip2px(context, 10));
//        int[] colors = context.getResources().getIntArray(R.array.google_colors);
//        header.setColorSchemeColors(colors);
//        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
//        header.setPadding(0, DensityUtil.dip2px(context,15), 0, DensityUtil.dip2px(context,10));
//        header.setPtrFrameLayout(ptrFrame);

        ptrFrame.setResistance(2.7f);
        ptrFrame.setRatioOfHeaderHeightToRefresh(1.0f);
        ptrFrame.setDurationToClose(150);
        ptrFrame.setDurationToCloseHeader(500);
        ptrFrame.setPullToRefresh(false);
        ptrFrame.setKeepHeaderWhenRefresh(true);
        ptrFrame.setHeaderView(header);
        ptrFrame.addPtrUIHandler(header);
        ptrFrame.disableWhenHorizontalMove(true);
    }

    /**
     * 设置view的margin
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    public static void setMargins (View v, int l, int t, int r, int b) {

        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
//            v.requestLayout();
        }
    }
}
