package com.xtec.timeline.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xtec.timeline.R;
import com.xtec.timeline.utils.T;
import com.xtec.timeline.widget.pwdkeyboard.VirtualKeyboardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 武昌丶鱼 on 2017/3/16.
 * Description:
 */

public class NumKeyboardActivity extends BaseActivity {

    @BindView(R.id.topbar_left)
    ImageButton topbarLeft;
    @BindView(R.id.topbar_title)
    TextView topbarTitle;
    @BindView(R.id.topbar_right)
    ImageButton topbarRight;
    @BindView(R.id.num_keyboard)
    VirtualKeyboardView numKeyboard;
    @BindView(R.id.et_num_pwd)
    EditText etNumPwd;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_keyboard);
        ButterKnife.bind(this);
        View topbar = findViewById(R.id.topbar);
        initTopbar(this, topbar, R.color.white, true);
        topbarTitle.setText("数字键盘");
        topbarTitle.setTextColor(getResources().getColor(R.color.black));

        numKeyboard.init(this, etNumPwd);

    }

    @OnClick(R.id.btn)
    public void onClick() {
        T.showShort(this,"输入是"+etNumPwd.getText().toString());
    }
}
