package com.xtec.timeline.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xtec.timeline.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 武昌丶鱼 on 2017/3/16.
 * Description:
 */

public class FormatEditTextActivity extends BaseActivity {

    @BindView(R.id.topbar_left)
    ImageButton topbarLeft;
    @BindView(R.id.topbar_title)
    TextView topbarTitle;
    @BindView(R.id.topbar_right)
    ImageButton topbarRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_format_edit_text);
        ButterKnife.bind(this);
        View topbar = findViewById(R.id.topbar);
        initTopbar(this, topbar,R.color.white, true);
        topbarTitle.setText("格式化手机/卡号");
    }
}
