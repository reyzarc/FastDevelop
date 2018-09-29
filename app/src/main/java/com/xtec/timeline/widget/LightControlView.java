package com.xtec.timeline.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class LightControlView extends View {

    private Paint mBgPaint;
    private Paint mLightPaint;

    private int mBgColor = Color.rgb(204, 204, 204);
    private int mLightColor = Color.BLUE;

    private int mRadius = 10;
    private int mBgHeight = 400;
    private int mBgWidth = 40;
    private int mLightHeight = 25;

    private RectF mBgRect = new RectF(0, 0, 0, 0);
    private RectF mLightRect = new RectF(0, 0, 0, 0);


    public LightControlView(Context context) {
        super(context);
        init();
    }

    public LightControlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LightControlView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mBgPaint.setColor(mBgColor);
        mLightPaint.setColor(mLightColor);

        mLightPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);


        mBgRect.left = getPaddingLeft();
        mBgRect.top = (getHeight() - mBgHeight) / 2.0f;
        mBgRect.right = getWidth() - getPaddingRight();
        mBgRect.bottom = mBgRect.top + mBgHeight;

        canvas.drawRoundRect(mBgRect, mRadius, mRadius, mBgPaint);

        mLightRect.left = getPaddingLeft();
        mLightRect.top = (getHeight() + mBgHeight) / 2.0f-mLightHeight;
        mLightRect.right = getWidth() - getPaddingRight();
        mLightRect.bottom = mBgRect.bottom;

        canvas.drawRect(mLightRect, mLightPaint);


        canvas.restoreToCount(layerId);
    }

    private float lastX,lastY;
    private boolean isInView;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();
                lastY = event.getY();

                //判断触摸位置是否落在控件内部
                isInView = mBgRect.contains(lastX,lastY);
                if(isInView){
                    handleTouch(event);
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                handleTouch(event);
                return true;

            case MotionEvent.ACTION_UP:
                break;
        }

        return super.onTouchEvent(event);
    }

    private void handleTouch(MotionEvent event) {
       float y = event.getY();

       mLightHeight = (int) ((mBgHeight+getHeight())/2.0f-y);
       invalidate();
    }
}
