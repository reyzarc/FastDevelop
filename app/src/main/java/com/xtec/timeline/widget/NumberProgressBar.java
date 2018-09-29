package com.xtec.timeline.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class NumberProgressBar extends View {
    /**
     * 背景画笔
     */
    private Paint mBgPaint;
    /**
     * 进度画笔
     */
    private Paint mProgressPaint;
    /**
     * 文本画笔
     */
    private Paint mTextPaint;

    private int mTextSize = 22;
    /**
     * 进度条高度
     */
    private int mProgressBarHeight = 10;
    /**
     * 背景颜色
     */
    private int mBgColor = Color.rgb(204, 204, 204);
    /**
     * 进度条的颜色
     */
    private int mProgressColor = Color.rgb(66, 145, 241);
    /**
     * 文本颜色
     */
    private int mTextColor = Color.rgb(255, 0, 0);
    /**
     * 进度条进度
     */
    private int mProgress = 40;
    /**
     * 最大进度，默认100
     */
    private int mProgressMax = 100;

    private int mRadius = 20;

    private RectF mBgRect = new RectF(0, 0, 0, 0);
    private RectF mProgressRect = new RectF(0, 0, 0, 0);


    /**
     * 背景是否已经绘制（确保背景只用绘制一次）
     */
    private boolean isBgDrawn;


    public NumberProgressBar(Context context) {
        super(context);
        init();
    }

    public NumberProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumberProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mBgPaint.setColor(mBgColor);
        mTextPaint.setColor(mTextColor);
        mProgressPaint.setColor(mProgressColor);

        mTextPaint.setTextSize(mTextSize);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.e("reyzarc", 100 * 1.0f / 2 + "======?" + 100 / 2 * 1.0f);

        drawProgress(canvas);
        drawBackground(canvas);
        drawProgressText(canvas);


    }

    /**
     * 绘制进度文本
     *
     * @param canvas
     */
    private void drawProgressText(final Canvas canvas) {
        //文本的宽度
        float textWidth = mTextPaint.measureText(mProgress + "%");
        float maxX = Math.min(mProgressRect.right + 2.0f, mBgRect.right - textWidth);

        float maxY = getHeight() / 2.0f - ((mTextPaint.ascent() + mTextPaint.descent()) / 2.0f);

        if(mProgressRect.right+textWidth>=getWidth()-getPaddingRight()){
            maxY -= 16;
        }

        canvas.drawText(mProgress + "%", maxX, maxY, mTextPaint);

    }

    /**
     * 绘制进度背景
     *
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        //文本的宽度
        float textWidth = mTextPaint.measureText(mProgress + "%");

        mBgRect.left = mProgressRect.right + textWidth;
        //view设置的高度减去进度条的高度就是要画的矩形的top值
        mBgRect.top = getHeight() / 2.0f - mProgressBarHeight / 2.0f;
        mBgRect.right = getWidth() - getPaddingRight();
        mBgRect.bottom = mBgRect.top + mProgressBarHeight;

        if (mRadius == 0) {
            canvas.drawRect(mBgRect, mBgPaint);
        } else {
            canvas.drawRoundRect(mBgRect, mRadius, mRadius, mBgPaint);
        }
    }

    /**
     * 绘制进度
     */
    private void drawProgress(final Canvas canvas) {

        mProgressRect.left = getPaddingLeft();
        mProgressRect.top = getHeight() / 2.0f - mProgressBarHeight / 2.0f;
        mProgressRect.right = (getWidth() - getPaddingRight()) * (mProgress * 1.0f / mProgressMax);
        mProgressRect.bottom = mBgRect.top + mProgressBarHeight;

        if (mRadius == 0) {
            canvas.drawRect(mProgressRect, mProgressPaint);
        } else {
            canvas.drawRoundRect(mProgressRect, mRadius, mRadius, mProgressPaint);
        }
    }

    public void setProgress(int progress) {
        if (progress > mProgressMax) {
            return;
        }
        mProgress = progress;
        invalidate();
    }

    public void setProgressMax(int progressMax) {
        mProgressMax = progressMax;
        invalidate();
    }

    public void setRadius(int radius) {
        mRadius = radius;
        invalidate();
    }

}
