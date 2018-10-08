package com.xtec.timeline.ui.activity;

import android.os.Bundle;

import com.xtec.timeline.R;
import com.xtec.timeline.widget.CircleExpandView;
import com.xtec.timeline.widget.FloatView;
import com.xtec.timeline.widget.Topbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 武昌丶鱼 on 2017/4/19.
 * Description:
 */

public class FloatViewActivity extends BaseActivity {
    @BindView(R.id.topbar)
    Topbar topbar;
    @BindView(R.id.float_view)
    FloatView floatView;
    @BindView(R.id.float_view2)
    CircleExpandView floatView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_view);
        ButterKnife.bind(this);
        initTopbar(this, topbar);

        floatView.start();

    }
}
