package com.xtec.timeline.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.xtec.timeline.R;

/**
 * Created by 武昌丶鱼 on 2016/11/8.
 * Description:自定义带状态的进度view
 */
public class ProgressStatusView extends View {
    private static final String TAG = "ProgressStatusView";

    private int mProgress = 0;
    //默认超时时间
    private int mTimeout = 3;

    private int line1_x = 0;
    private int line1_y = 0;
    private int line2_x = 0;
    private int line2_y = 0;
    private int line3_x = 0;
    private int line3_y = 0;
    private int line4_x = 0;
    private int line4_y = 0;

    private int center;
    private int center1;
    private int radius;
    private Paint mPaint;
    private RectF mRectF;
    private boolean isFinish;

    public enum Status{
        SUCCESS,FAILED,TIMEOUT,LOADING
    }

    private Status mStatus = Status.LOADING;

    public void setStatus(Status status){
        mStatus = status;
    }

    public ProgressStatusView(Context context) {
        super(context);
        init();
    }


    public ProgressStatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //初始化画笔,设置颜色为绿色
        mPaint.setColor(getResources().getColor(R.color.green));
        //设置画笔粗细
        mPaint.setStrokeWidth(5);
        //设置画笔模式,stroke为空心
        mPaint.setStyle(Paint.Style.STROKE);

        //获取圆心坐标
        center = getWidth() / 2;
        //√的起点坐标
        center1 = center - getWidth() / 5;
        //圆弧的半径
        radius = getWidth() / 2 - 5;

        mRectF = new RectF(center - radius - 1, center - radius - 1, center + radius + 1, center + radius + 1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //让进度每次自增1
        mProgress++;

        if (mProgress > 100) {
            mProgress = 0;
        }

        switch (mStatus){
            case LOADING://默认情况,一直绘制绿色圆圈
            case SUCCESS://成功,绘制绿色显示√
                mPaint.setColor(getResources().getColor(R.color.green));
                if(line1_x < radius / 3) {
                    line1_x++;
                    line1_y++;
                }
                //画第一根线
                canvas.drawLine(center1, center, center1 + line1_x, center + line1_y, mPaint);

                if (line1_x == radius / 3) {
                    line2_x = line1_x;
                    line2_y = line1_y;
                    line1_x++;
                    line1_y++;
                }
                if (line1_x >= radius / 3 && line2_x <= radius) {
                    line2_x++;
                    line2_y--;
                }
                //画第二根线
                canvas.drawLine(center1 + line1_x - 1, center + line1_y, center1 + line2_x, center + line2_y, mPaint);


                break;
            case FAILED://失败,绘制红色显示×
                mPaint.setColor(getResources().getColor(R.color.red));
                break;
            case TIMEOUT://超时,绘制橙色,显示！
                mPaint.setColor(getResources().getColor(R.color.colorPrimary));
                break;
        }

        canvas.drawArc(mRectF, 235, -360 * mProgress / 100, false, mPaint);

        //每隔10ms刷新界面
        postInvalidateDelayed(10);

    }
}
