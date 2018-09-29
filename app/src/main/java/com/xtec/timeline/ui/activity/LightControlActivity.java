package com.xtec.timeline.ui.activity;

import android.os.Bundle;

import com.xtec.timeline.R;
import com.xtec.timeline.widget.Topbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LightControlActivity extends BaseActivity {


    @BindView(R.id.topbar)
    Topbar topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_control);
        ButterKnife.bind(this);
        initTopbar(this, topbar);

    }
}
