package com.xtec.timeline.ui.activity;

import android.os.Bundle;

import com.john.waveview.WaveView;
import com.xtec.timeline.R;
import com.xtec.timeline.widget.Topbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : 武昌丶鱼
 * e-mail : reyzarc@163.com
 * time   : 2017-07-11
 * desc   :
 */
public class CalendarActivity extends BaseActivity {

    @BindView(R.id.topbar)
    Topbar topbar;
    @BindView(R.id.wave_view)
    WaveView waveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
        initTopbar(this, topbar);
    }
}
