package com.xtec.timeline.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xtec.timeline.R;
import com.xtec.timeline.widget.NumberProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NumberProgressBarActivity extends BaseActivity {

    @BindView(R.id.topbar_left)
    ImageButton topbarLeft;
    @BindView(R.id.topbar_title)
    TextView topbarTitle;
    @BindView(R.id.topbar_right)
    ImageButton topbarRight;
    @BindView(R.id.num_progress_bar)
    NumberProgressBar numProgressBar;

    private int i = 0;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            numProgressBar.setProgress(++i);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_progress_bar);
        ButterKnife.bind(this);
        View topbar = findViewById(R.id.topbar);
        initTopbar(this, topbar, R.color.white, true);
        topbarTitle.setText("数字进度条");
        topbarTitle.setTextColor(getResources().getColor(R.color.black));
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
               mHandler.sendEmptyMessage(0);
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 100);

    }


}
