package com.xtec.timeline.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 武昌丶鱼 on 2016/12/8.
 * Description:心电图view
 */
public class HeartbeatView extends View {
    private static final String TAG = "HeartBeatView";

    private Paint mPaint;
    private Path mPath;

    private float transX, moveX;
    private boolean isCanvasMove;

    private int screenW, screenH;
    private float x, y;
    private float initScreenW;
    private float initX;

    public HeartbeatView(Context context) {
        super(context);
        init(context);
    }

    public HeartbeatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HeartbeatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setShadowLayer(7, 0, 0, Color.GREEN);

        mPath = new Path();
        transX = 0;
        isCanvasMove = false;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        screenH = h;
        screenW = w;

        x = 0;
        y = (screenH / 2) + (screenH / 4) + (screenH / 10);

        initScreenW = screenW;

        initX = ((screenW / 2) + (screenW / 4));

        moveX = screenW / 24;

        mPath.moveTo(x, y);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.BLACK);

        mPath.lineTo(x, y);

        canvas.translate(-transX, 0);
        calCoors();

        canvas.drawPath(mPath, mPaint);

        invalidate();
    }

    private void calCoors() {

        if (isCanvasMove = true) {
            transX += 4;
        }

        if (x < initX) {
            x += 8;
        } else {
            if (x < initX + moveX) {
                x += 2;
                y -= 8;
            } else {
                if (x < initX + (moveX * 2)) {
                    x += 2;
                    y += 14;
                } else {
                    if (x < initX + (moveX * 3)) {
                        x += 2;
                        y -= 12;
                    } else {
                        if (x < initX + (moveX * 4)) {
                            x += 2;
                            y += 6;
                        } else {
                            if (x < initScreenW) {
                                x += 8;
                            } else {
                                isCanvasMove = true;
                                initX = initX + initScreenW;
                            }
                        }
                    }
                }
            }
        }
    }
}
