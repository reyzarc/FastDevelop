package com.xtec.timeline.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.LinearLayout;

/**
 * author       : hulong
 * e-mail       : reyzarc@163.com
 * create date  : 2018/10/31
 * description  : 自动包裹软键盘的顶部布局
 */


public class SoftInputLinearLayout extends LinearLayout {

    private int[] mInsets = new int[4];

    public SoftInputLinearLayout(Context context) {
        super(context);
    }

    public SoftInputLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public WindowInsets computeSystemWindowInsets(WindowInsets in, Rect outLocalInsets) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // Intentionally do not modify the bottom inset. For some reason,
            // if the bottom inset is modified, window resizing stops working.
            // TODO: Figure out why.

            mInsets[0] = outLocalInsets.left;
            mInsets[1] = outLocalInsets.top;
            mInsets[2] = outLocalInsets.right;

            outLocalInsets.left = 0;
            outLocalInsets.top = 0;
            outLocalInsets.right = 0;
        }

        return super.computeSystemWindowInsets(in, outLocalInsets);

    }

    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            mInsets[0] = insets.getSystemWindowInsetLeft();
            mInsets[1] = insets.getSystemWindowInsetTop();
            mInsets[2] = insets.getSystemWindowInsetRight();
            return super.onApplyWindowInsets(insets.replaceSystemWindowInsets(0, 0, 0,
                    insets.getSystemWindowInsetBottom()));
        } else {
            return insets;
        }
    }

}

