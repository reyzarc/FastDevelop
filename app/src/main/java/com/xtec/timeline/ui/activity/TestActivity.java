package com.xtec.timeline.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xtec.timeline.R;
import com.xtec.timeline.utils.StatusbarUtil;
import com.xtec.timeline.widget.HeartbeatView;
import com.xtec.timeline.widget.customToast.AppMsg;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 武昌丶鱼 on 2016/10/20.
 * Description:
 */
public class TestActivity extends BaseActivity {
    private static final String TAG = "TestActivity";
    @BindView(R.id.topbar_left)
    ImageButton topbarLeft;
    @BindView(R.id.topbar_title)
    TextView topbarTitle;
    @BindView(R.id.topbar_right)
    ImageButton topbarRight;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_stop)
    Button btnStop;
    @BindView(R.id.hv)
    HeartbeatView hv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        Window window = getWindow();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
//            View decorView = window.getDecorView();
//            int option =View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
////            window.setStatusBarColor(Color.TRANSPARENT);
//            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏和导航栏
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        } else {
//            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams
//                    .FLAG_FULLSCREEN);
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        View topbar = findViewById(R.id.test_topbar);
        initTopbar(this, topbar, true);
        topbar.setBackgroundColor(getResources().getColor(R.color.gray));
        StatusbarUtil.setFontBlack(this,true);

    }

    @OnClick({R.id.btn_start, R.id.btn_stop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startActivity(new Intent(this,ReverseActivity.class));

                break;
            case R.id.btn_stop:
//                ObjectAnimator animator1 = ObjectAnimator.ofFloat(hv, "rotationY", 0f, -360f);
//                animator1.setDuration(2000);
//                animator1.start();

                CharSequence msg = "这是测试toast...";
                AppMsg.Style style=AppMsg.STYLE_ALERT;
                AppMsg appMsg = AppMsg.makeText(this, msg, style);
                appMsg.setDuration(1000);
                appMsg.setLayoutGravity(Gravity.CENTER);
                appMsg.show();


                break;
        }

    }
}
