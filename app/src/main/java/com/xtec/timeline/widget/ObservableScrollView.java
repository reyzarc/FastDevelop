package com.xtec.timeline.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by 武昌丶鱼 on 2016/10/25.
 * Description:自定义可以监听滑动距离的scrollview
 */
public class ObservableScrollView extends ScrollView{
    private static final String TAG = "ObservableScrollView";

    private OnScrollChangedListener mOnscrollChangedListener;

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener){
        mOnscrollChangedListener = onScrollChangedListener;
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ObservableScrollView(Context context) {
        super(context);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        mOnscrollChangedListener.onScrollChanged(l,t,oldl,oldt);
    }

    public interface OnScrollChangedListener{
        void onScrollChanged(int x,int y,int oldx,int oldy);
    }
}
