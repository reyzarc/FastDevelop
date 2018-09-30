package com.xtec.timeline.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public class AnimationButton extends View {
    private Paint mBgPaint;
    private RectF mBgRectF = new RectF(0, 0, 0, 0);
    private int mBgColor = Color.rgb(25, 25, 25);

    private int mButtonHeight = 180;
    private int mButtonWidth = 800;

    private int mRadius;

    private int mChangedWidth = 0;

    private ValueAnimator mChangeRadiusAnimator;

    private ValueAnimator mRect2CircleAnimator;

    private ObjectAnimator mUpTranslationAnimator;

    private AnimatorSet mAnimatorSet;

    private OnButtonClickListener mButtonClickListener;


    public AnimationButton(Context context) {
        super(context);
        init();
    }

    public AnimationButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimationButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mBgPaint.setColor(mBgColor);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonClickListener != null) {
                    mButtonClickListener.onButtonClick();
                }
            }
        });

        initAnimation();

    }

    private void initAnimation() {

        changeRadiusAnimation();
        rect2circleAnimation();
        upTranslationAnimation();

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.play(mUpTranslationAnimator).after(mRect2CircleAnimator).after(mChangeRadiusAnimator);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mBgRectF.left = (getWidth() - mButtonWidth) / 2.0f;
        mBgRectF.top = (getHeight() - mButtonHeight) / 2.0f;
        mBgRectF.right = mBgRectF.left + mButtonWidth;
        mBgRectF.bottom = mBgRectF.top + mButtonHeight;

        canvas.drawRoundRect(mBgRectF, mRadius, mRadius, mBgPaint);
    }

    private void changeRadiusAnimation() {
        mChangeRadiusAnimator = ValueAnimator.ofInt(0, mButtonHeight / 2);
        mChangeRadiusAnimator.setDuration(2000);
        mChangeRadiusAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mRadius = value;
                invalidate();
            }
        });
    }

    /**
     * 圆角矩形渐变到圆形的动画
     */
    private void rect2circleAnimation() {
        mRect2CircleAnimator = ValueAnimator.ofInt(mButtonWidth, mButtonHeight);
        mRect2CircleAnimator.setDuration(2000);
        mRect2CircleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mButtonWidth = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    private void upTranslationAnimation(){
        float currTranslationY = this.getTranslationY();
        mUpTranslationAnimator = ObjectAnimator.ofFloat(this,"translationY",currTranslationY,currTranslationY-200);
        mUpTranslationAnimator.setDuration(500);
        mUpTranslationAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public interface OnButtonClickListener {
        void onButtonClick();
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        mButtonClickListener = onButtonClickListener;
    }

    public void doAnimator() {
        mAnimatorSet.start();
    }

}
