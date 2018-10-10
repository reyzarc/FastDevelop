package com.xtec.timeline.ui.activity;

import android.os.Bundle;

import com.xtec.timeline.R;
import com.xtec.timeline.widget.ArcView;
import com.xtec.timeline.widget.Topbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArcViewActivity extends BaseActivity {

    @BindView(R.id.topbar)
    Topbar topbar;
    @BindView(R.id.arc_view)
    ArcView arcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_view);
        ButterKnife.bind(this);
        initTopbar(this, topbar);

    }
}
