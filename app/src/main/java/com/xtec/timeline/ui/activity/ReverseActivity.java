package com.xtec.timeline.ui.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xtec.timeline.R;
import com.xtec.timeline.utils.UIUtils;
import com.xtec.timeline.widget.Topbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 武昌丶鱼 on 2017/1/10.
 * Description:
 */

public class ReverseActivity extends AppCompatActivity {


    ImageButton topbarRight;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_stop)
    Button btnStop;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.topbar)
    Topbar topbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse);
        ButterKnife.bind(this);
        UIUtils.initTopbarForActivity(this,topbar,true);

    }


    @OnClick({R.id.btn_start, R.id.btn_stop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                ObjectAnimator animator = ObjectAnimator.ofFloat(tv, "rotationY", 0f, 180f);
                animator.setDuration(2000);
                animator.start();
                break;
            case R.id.btn_stop:
                break;
        }
    }
}
