package com.xtec.timeline.ui.activity;

import android.os.Bundle;

import com.xtec.timeline.R;
import com.xtec.timeline.widget.AnimationButton;
import com.xtec.timeline.widget.Topbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimationButtonActivity extends BaseActivity {
    @BindView(R.id.topbar)
    Topbar topbar;
    @BindView(R.id.animation_button)
    AnimationButton animationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_button);
        ButterKnife.bind(this);
        initTopbar(this, topbar);

        animationButton.setOnButtonClickListener(new AnimationButton.OnButtonClickListener() {
            @Override
            public void onButtonClick() {
                animationButton.startAnimator();
            }

            @Override
            public void onAnimationFinish() {
                animationButton.resetAnimator();
            }
        });

    }
}
