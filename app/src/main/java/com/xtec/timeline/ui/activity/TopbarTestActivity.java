package com.xtec.timeline.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xtec.timeline.R;
import com.xtec.timeline.utils.T;
import com.xtec.timeline.widget.Topbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 武昌丶鱼 on 2017/4/19.
 * Description:
 */

public class TopbarTestActivity extends BaseActivity {
    @BindView(R.id.topbar)
    Topbar topbar;
    @BindView(R.id.topbar2)
    Topbar topbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topbar_test);
        ButterKnife.bind(this);
        initTopbar(this,topbar);
        topbar.setRightIcon(getResources().getDrawable(R.drawable.ic_launcher), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.showShort(TopbarTestActivity.this,"点击了---->right");
            }
        });

        topbar2.setLeftText("测试", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.showShort(TopbarTestActivity.this,"点击了---->"+((TextView)view).getText());
            }
        });

        topbar2.setRightText("右边", getResources().getDrawable(R.drawable.ic_back_white), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.showShort(TopbarTestActivity.this,"点击了---->"+((TextView)view).getText());
            }
        });
    }
}
