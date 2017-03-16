package com.xtec.timeline.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xtec.timeline.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by 武昌丶鱼 on 2017/3/16.
 * Description:控件集合
 */

public class WidgetDemoActivity extends BaseActivity {

    @BindView(R.id.topbar_left)
    ImageButton topbarLeft;
    @BindView(R.id.topbar_title)
    TextView topbarTitle;
    @BindView(R.id.topbar_right)
    ImageButton topbarRight;
    @BindView(R.id.btn_num_keyboard)
    Button btnNumKeyboard;
    @BindView(R.id.btn_pwd_edit_text)
    Button btnPwdEditText;
    @BindView(R.id.btn_format_edit_text)
    Button btnFormatEditText;
    @BindView(R.id.btn_circle_progress)
    Button btnCircleProgress;
    @BindView(R.id.btn_fast_dialog)
    Button btnFastDialog;
    @BindView(R.id.btn_progress_wheel)
    Button btnProgressWheel;
    @BindView(R.id.btn_square_image)
    Button btnSquareImage;
    @BindView(R.id.btn_zoom_seek_bar)
    Button btnZoomSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_demo);
        ButterKnife.bind(this);
        View topbar = findViewById(R.id.widget_demo_topbar);
        initTopbar(this,topbar,true);
    }

    @OnClick({R.id.btn_num_keyboard, R.id.btn_pwd_edit_text, R.id.btn_format_edit_text, R.id.btn_circle_progress, R.id.btn_fast_dialog, R.id.btn_progress_wheel, R.id.btn_square_image, R.id.btn_zoom_seek_bar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_num_keyboard://数字键盘
                startActivity(new Intent(this,NumKeyboardActivity.class));
                break;
            case R.id.btn_pwd_edit_text://仿微信支付密码输入框
                startActivity(new Intent(this,PayPwdEditTextActivity.class));
                break;
            case R.id.btn_format_edit_text://自动格式化电话/银行卡的editText
                startActivity(new Intent(this,FormatEditTextActivity.class));

                break;
            case R.id.btn_circle_progress://环形进度条
                break;
            case R.id.btn_fast_dialog://自定义的dialog
                break;
            case R.id.btn_progress_wheel://环形进度
                break;
            case R.id.btn_square_image://圆形头像
                break;
            case R.id.btn_zoom_seek_bar://文字跟随拖动变大的seekBar
                break;
        }
    }
}
