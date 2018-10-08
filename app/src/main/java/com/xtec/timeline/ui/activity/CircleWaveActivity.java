package com.xtec.timeline.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.xtec.timeline.R;
import com.xtec.timeline.widget.Topbar;
import com.xtec.timeline.widget.WaveView;
import com.xtec.timeline.widget.WaveViewHelper;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircleWaveActivity extends BaseActivity {
    @BindView(R.id.topbar)
    Topbar topbar;
    @BindView(R.id.wave_view)
    WaveView waveView;

    private WaveViewHelper mWaveViewHelper;

    private int progress = 0;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progress++;
            if(progress>=76){
                return;
            }
            waveView.setWaterLevelRatio(progress/100F);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_wave);
        ButterKnife.bind(this);
        initTopbar(this, topbar);

        waveView.setShapeType(WaveView.ShapeType.CIRCLE);
        /**绘制边界线的宽及颜色，不需要的可以注销，此处默认宽4，白色*/
        waveView.setBorder((int) getResources().getDimension(R.dimen.wave_border), 0xffffffff);
        /**是否显示双线*/
        waveView.setShowBehindLine(false);
        waveView.setWaveColor(
                Color.parseColor("#fff7bfc0"),//波浪线上（淡紫色）setShowBehindLine为true时显示,false不显示
                Color.parseColor("#fffeab2f"));//波浪线下（橘黄色）
        mWaveViewHelper = new WaveViewHelper(waveView);
//        waveView.setWaterLevelRatio(0.0F);
//        waveView.setWaterLevelRatio(35/100F);
        mWaveViewHelper.start();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task,1000,200);
    }
}
