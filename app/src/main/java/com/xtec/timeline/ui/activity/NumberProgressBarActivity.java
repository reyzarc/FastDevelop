package com.xtec.timeline.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.xtec.timeline.R;
import com.xtec.timeline.widget.NumberProgressBar;
import com.xtec.timeline.widget.Topbar;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NumberProgressBarActivity extends BaseActivity {


    @BindView(R.id.num_progress_bar)
    NumberProgressBar numProgressBar;
    @BindView(R.id.topbar)
    Topbar topbar;

    private int i = 0;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
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
        initTopbar(this, topbar);
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
