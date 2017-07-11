package com.xtec.timeline.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 武昌丶鱼 on 2017/5/18.
 * Description:
 */

public class FloatView extends View {
    private float mWidth;
    private float mHeight;

    private float centerX; // 圆心X
    private float centerY; // 圆心Y
    private float mRadius = 10; // 圆的半径
    private int mColor = Color.RED;

    private int mScreenWidth;

    private Paint mPaint;

    private boolean started;


    public FloatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatView(Context context) {
        this(context, null, 0);
    }

    public FloatView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColor);

    }

    private void init() {
        mWidth = getWidth();
        mHeight = getHeight();
        centerX = mWidth / 2.0F;
        centerY = (mHeight / 2.0F);

        start();
    }

    public void start() {
        if (!started) {
            started = true;
            new Thread(thread).start();
        }
    }

    public void stop() {
        // Thread.interrupted();
        started = true;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        stop();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mRadius <= 0.0F) {
            return;
        }

        canvas.drawCircle(centerX, centerY, mRadius, mPaint);

    }

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            init();
        } else {
            stop();
        }
    }

    //在外面定义一个方法把started设置为true就可以暂停了
    private Runnable thread = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (started) {
                mRadius = mRadius + 5;

                postInvalidate();
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException localInterruptedException) {
                    localInterruptedException.printStackTrace();
                }
            }
        }
    };

}
