package com.xtec.timeline.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xtec.timeline.R;

/**
 * Created by 武昌丶鱼 on 2017/4/18.
 * Description:自定义topbar
 */

public class Topbar extends LinearLayout {
    private int mTopbarHeight;
    private boolean mIsTransparent;
    private boolean mEnableBack;
    private Drawable mLeftIcon;
    private Drawable mRightIcon;
    private String mLeftText;
    private String mRightText;
    private int mTopbarColor;

    private LayoutInflater mLayoutInflater;
    private View mView;

    private TextView leftTextView, rightTextView, centerTextView;
    private ImageButton leftButton, rightButton;


    public Topbar(Context context) {
        this(context, null);
    }

    public Topbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Topbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(final Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Topbar);
        mEnableBack = ta.getBoolean(R.styleable.Topbar_back_enable, false);
        mIsTransparent = ta.getBoolean(R.styleable.Topbar_topbar_transparent, false);
        mTopbarColor = ta.getColor(R.styleable.Topbar_topbar_color, getResources().getColor(R.color.colorPrimary));
        mTopbarHeight = (int) ta.getDimension(R.styleable.Topbar_topbar_height, 50);
        mLeftIcon = ta.getDrawable(R.styleable.Topbar_left_icon);
        mRightIcon = ta.getDrawable(R.styleable.Topbar_right_icon);
        mLeftText = ta.getString(R.styleable.Topbar_left_text);
        mRightText = ta.getString(R.styleable.Topbar_right_text);

        ta.recycle();

        mLayoutInflater = LayoutInflater.from(context);
        mView = mLayoutInflater.inflate(R.layout.layout_topbar_new, null);

        leftButton = (ImageButton) mView.findViewById(R.id.topbar_left_icon);
        rightButton = (ImageButton) mView.findViewById(R.id.topbar_right_icon);
        leftTextView = (TextView) mView.findViewById(R.id.topbar_left_title);
        rightTextView = (TextView) mView.findViewById(R.id.topbar_right_title);
        centerTextView = (TextView) mView.findViewById(R.id.topbar_title);

        //设置背景透明
        if (mIsTransparent) {//背景透明,则默认将文字颜色设置为主题色
            mView.setBackgroundColor(0x00000000);
            leftTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
            rightTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
        } else {//背景不透明,默认为白色
            mView.setBackgroundColor(mTopbarColor);
            leftTextView.setTextColor(0x00ffffff);
            rightTextView.setTextColor(0x00ffffff);
        }

        if (mLeftIcon == null) {
            leftButton.setVisibility(GONE);
        } else {
            leftTextView.setVisibility(GONE);
            leftButton.setVisibility(VISIBLE);
            leftButton.setImageDrawable(mLeftIcon);
        }
        if (mRightIcon == null) {
            rightButton.setVisibility(GONE);
        } else {
            rightTextView.setVisibility(GONE);
            rightButton.setVisibility(VISIBLE);
            rightButton.setImageDrawable(mRightIcon);
        }

        if (TextUtils.isEmpty(mLeftText)) {
            leftTextView.setVisibility(GONE);
        } else {
            leftButton.setVisibility(GONE);
            leftTextView.setVisibility(VISIBLE);
            leftTextView.setText(mLeftText);
        }
        if (TextUtils.isEmpty(mRightText)) {
            rightTextView.setVisibility(GONE);
        } else {
            rightButton.setVisibility(GONE);
            rightTextView.setVisibility(VISIBLE);
            rightTextView.setText(mRightText);
        }

        //设置激活返回键
        if (mEnableBack) {
            leftTextView.setVisibility(GONE);
            leftButton.setVisibility(VISIBLE);
            leftButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_back_white));
            leftButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Activity) context).finish();
                }
            });
        }

        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mView, lp);
    }

    /**
     * 左边纯图片
     *
     * @param icon
     * @param listener
     */
    public void setLeftIcon(Drawable icon, OnClickListener listener) {
        if (leftButton != null) {
            leftButton.setImageDrawable(icon);
            leftTextView.setVisibility(GONE);
            leftButton.setVisibility(VISIBLE);
            leftButton.setOnClickListener(listener);
        }
    }

    /**
     * 右边纯图片
     *
     * @param icon
     * @param listener
     */
    public void setRightIcon(Drawable icon, OnClickListener listener) {
        if (leftButton != null) {
            rightButton.setImageDrawable(icon);
            rightTextView.setVisibility(GONE);
            rightButton.setVisibility(VISIBLE);
            rightButton.setOnClickListener(listener);
        }
    }

    /**
     * 左边纯文字
     *
     * @param leftText
     * @param listener
     */
    public void setLeftText(String leftText, OnClickListener listener) {
        if (!TextUtils.isEmpty(leftText)) {
            leftButton.setVisibility(GONE);
            leftTextView.setVisibility(VISIBLE);
            leftTextView.setText(leftText);
            leftTextView.setOnClickListener(listener);
        }
    }

    /**
     * 设置右边带图片的文字
     *
     * @param leftText
     * @param drawable
     * @param listener
     */
    public void setLeftText(String leftText, Drawable drawable, OnClickListener listener) {
        if (!TextUtils.isEmpty(leftText)) {
            leftButton.setVisibility(GONE);
            leftTextView.setVisibility(VISIBLE);
            leftTextView.setText(leftText);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            leftTextView.setCompoundDrawables(drawable, null, null, null);
            leftTextView.setCompoundDrawablePadding(10);
            leftTextView.setOnClickListener(listener);
        }
    }

    /**
     * 右边纯文字
     *
     * @param rightText
     * @param listener
     */
    public void setRightText(String rightText, OnClickListener listener) {
        if (!TextUtils.isEmpty(rightText)) {
            rightButton.setVisibility(GONE);
            rightTextView.setVisibility(VISIBLE);
            rightTextView.setText(rightText);
            rightTextView.setOnClickListener(listener);
        }
    }

    /**
     * 设置右边带图片的文字
     *
     * @param rightText
     * @param drawable
     * @param listener
     */
    public void setRightText(String rightText, Drawable drawable, OnClickListener listener) {
        if (!TextUtils.isEmpty(rightText)) {
            rightButton.setVisibility(GONE);
            rightTextView.setVisibility(VISIBLE);
            rightTextView.setText(rightText);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            rightTextView.setCompoundDrawables(null, null, drawable, null);
            rightTextView.setCompoundDrawablePadding(10);
            rightTextView.setOnClickListener(listener);
        }
    }

    /**
     * 右边bar点击事件
     *
     * @param listener
     */
    public void setRightBarClickListener(OnClickListener listener) {
        if (rightTextView.getVisibility() == View.VISIBLE) {
            rightTextView.setOnClickListener(listener);
        } else if (rightButton.getVisibility() == View.VISIBLE) {
            rightButton.setOnClickListener(listener);
        }
    }

    /**
     * 左边bar点击事件
     *
     * @param listener
     */
    public void setLeftBarClickListener(OnClickListener listener) {
        if (leftTextView.getVisibility() == View.VISIBLE) {
            leftTextView.setOnClickListener(listener);
        } else if (leftButton.getVisibility() == View.VISIBLE) {
            leftButton.setOnClickListener(listener);
        }
    }

    public TextView getRightTextView() {
        if (rightTextView != null) {
            return rightTextView;
        }
        return null;
    }

    public TextView getLeftTextView() {
        if (leftTextView != null) {
            return leftTextView;
        }
        return null;
    }
}
