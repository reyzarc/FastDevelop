package com.xtec.timeline.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.xtec.timeline.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 武昌丶鱼 on 2016/10/25.
 * Description:
 */
public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    @BindView(R.id.tv_countdown)
    TextView tvCountdown;

    private CountDownTimer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);


//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                finish();
//            }
//        }, 5000);
        timer = new CountDownTimer(6000,1000){

            @Override
            public void onTick(long l) {
                tvCountdown.setText(l/1000+" 跳过");
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }.start();

    }

    @OnClick(R.id.tv_countdown)
    public void onClick() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
            timer = null;
            finish();
        }
    }
}
