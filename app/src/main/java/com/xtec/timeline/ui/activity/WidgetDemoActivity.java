package com.xtec.timeline.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xtec.timeline.R;
import com.xtec.timeline.utils.MathUtils;
import com.xtec.timeline.utils.Utils;
import com.xtec.timeline.widget.Topbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by 武昌丶鱼 on 2017/3/16.
 * Description:控件集合
 */

public class WidgetDemoActivity extends BaseActivity {


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
    @BindView(R.id.btn_baidu_map)
    Button btnBaiduMap;
    @BindView(R.id.topbar)
    Topbar topbar;
    @BindView(R.id.btn_progress_bar)
    Button btnProgressBar;
    @BindView(R.id.btn_light_control)
    Button btnLightControl;
    @BindView(R.id.btn_animation_button)
    Button btnAnimationButton;
    @BindView(R.id.btn_circle_wave)
    Button btnCircleWave;
    @BindView(R.id.btn_arc_view)
    Button btnArcView;
    @BindView(R.id.btn_multi_image_view)
    Button btnMultiImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_demo);
        ButterKnife.bind(this);
        initTopbar(this, topbar);
    }

    @OnClick({R.id.btn_multi_image_view,R.id.btn_arc_view, R.id.btn_circle_wave, R.id.btn_animation_button, R.id.btn_light_control, R.id.btn_progress_bar, R.id.btn_baidu_map, R.id.btn_num_keyboard, R.id.btn_pwd_edit_text, R.id.btn_format_edit_text, R.id.btn_circle_progress, R.id.btn_fast_dialog, R.id.btn_progress_wheel, R.id.btn_square_image, R.id.btn_zoom_seek_bar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_num_keyboard://数字键盘
                if (!Utils.isFastDoubleClick()) {
                    startActivity(new Intent(this, NumKeyboardActivity.class));
                }
                break;
            case R.id.btn_pwd_edit_text://仿微信支付密码输入框
                startActivity(new Intent(this, PayPwdEditTextActivity.class));
                break;
            case R.id.btn_format_edit_text://自动格式化电话/银行卡的editText
                startActivity(new Intent(this, FormatEditTextActivity.class));

                break;
            case R.id.btn_circle_progress://环形进度条
                startActivity(new Intent(this, TopbarTestActivity.class));
                break;
            case R.id.btn_fast_dialog://自定义的dialog
                startActivity(new Intent(this, ImageChooseActivity.class));
                break;
            case R.id.btn_progress_wheel://环形进度
                startActivity(new Intent(this, FloatViewActivity.class));
                break;
            case R.id.btn_square_image://圆形头像
                startActivity(new Intent(this, SampleActivity.class));
                break;
            case R.id.btn_zoom_seek_bar://文字跟随拖动变大的seekBar
                MathUtils.calculateInterest(174000, 0.12f, 18);
                MathUtils.calculateInterest2(891.6f, 0.16f);
                break;
            case R.id.btn_baidu_map://百度地图
                startActivity(new Intent(this, BaiduMapActivity.class));
                break;
            case R.id.btn_progress_bar://带数字进度条
                startActivity(new Intent(this, NumberProgressBarActivity.class));
                break;
            case R.id.btn_light_control://IOS亮度调节
                startActivity(new Intent(this, LightControlActivity.class));
                break;
            case R.id.btn_animation_button://动画效果Button
                startActivity(new Intent(this, AnimationButtonActivity.class));
                break;
            case R.id.btn_circle_wave://圆形水波进度球
                startActivity(new Intent(this, CircleWaveActivity.class));
                break;
            case R.id.btn_arc_view://弧形
                startActivity(new Intent(this, ArcViewActivity.class));
                break;
            case R.id.btn_multi_image_view://多图显示
                startActivity(new Intent(this, MultiImageViewActivity.class));
                break;
        }
    }
}
