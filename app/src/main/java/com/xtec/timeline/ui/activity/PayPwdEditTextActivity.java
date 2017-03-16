package com.xtec.timeline.ui.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xtec.timeline.R;
import com.xtec.timeline.utils.T;
import com.xtec.timeline.widget.pwdkeyboard.PopEnterPassword;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 武昌丶鱼 on 2017/3/16.
 * Description:
 */

public class PayPwdEditTextActivity extends BaseActivity {
    @BindView(R.id.topbar_left)
    ImageButton topbarLeft;
    @BindView(R.id.topbar_title)
    TextView topbarTitle;
    @BindView(R.id.topbar_right)
    ImageButton topbarRight;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pwd_edit_text);
        ButterKnife.bind(this);
        View topbar = findViewById(R.id.topbar);
        initTopbar(this, topbar,R.color.white, true);
        topbarTitle.setText("仿微信支付");
        topbarTitle.setTextColor(getResources().getColor(R.color.black));

    }

    @OnClick(R.id.btn)
    public void onClick() {
        showPayKeyboard();
    }

    private void showPayKeyboard() {
        PopEnterPassword popEnterPassword = new PopEnterPassword(this, new PopEnterPassword.PopEnterPasswordListener() {
            @Override
            public void onInputPwdFinish(String pwd) {
                //do something
                T.showShort(PayPwdEditTextActivity.this,"密码是"+pwd);
            }

            @Override
            public void onForgetPwdClick() {
               T.showShort(PayPwdEditTextActivity.this,"忘记密码啦");
            }
        });
        View view = findViewById(R.id.ll_content);
        popEnterPassword.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
