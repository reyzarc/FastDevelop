package com.xtec.timeline.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xtec.timeline.R;

/**
 * 弧形View
 */
public class ArcView extends View {
    private Paint mPaint;

    private RectF mRectF = new RectF();

    private int mArcHeight = 20;
    private int mBgColor = Color.WHITE;

    public ArcView(Context context) {
        this(context,null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ArcView);

        mBgColor = array.getColor(R.styleable.ArcView_background_color,Color.WHITE);
        mArcHeight = (int) array.getDimension(R.styleable.ArcView_arc_height,0);

        array.recycle();
        init();
    }


    private void init(){

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mBgColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //椭圆的外切矩形，截取其中一段则为弧形线段
        mRectF.left = 0-100;
        mRectF.top = 0;
        mRectF.right = getWidth()+100;
        mRectF.bottom = getHeight()*4;

//        mPath.addArc(mRectF,0,180f);
        canvas.drawArc(mRectF,180,180,false,mPaint);
    }

    public void setArcHeight(int height){
        mArcHeight = height;
        invalidate();
    }

    public void setColor(int color){
        mBgColor = color;
        invalidate();
    }

}
