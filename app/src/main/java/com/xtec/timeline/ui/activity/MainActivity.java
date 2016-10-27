package com.xtec.timeline.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xtec.timeline.R;
import com.xtec.timeline.ui.fragment.FootprintFragment;
import com.xtec.timeline.ui.fragment.FriendsFragment;
import com.xtec.timeline.ui.fragment.MessageFragment;
import com.xtec.timeline.ui.fragment.MoreFragment;
import com.xtec.timeline.utils.L;
import com.xtec.timeline.utils.T;
import com.xtec.timeline.widget.FastDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.main_content)
    FrameLayout mainContent;
    @BindView(R.id.main_rb_footprint)
    RadioButton mainRbFootprint;
    @BindView(R.id.main_rb_message)
    RadioButton mainRbMessage;
    @BindView(R.id.main_rb_friends)
    RadioButton mainRbFriends;
    @BindView(R.id.main_rb_more)
    RadioButton mainRbMore;
    @BindView(R.id.main_rg)
    RadioGroup mainRg;

    private Fragment mFootprintFragment, mFriendsFragment, mMessageFragment, mMoreFragment, mFromFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        getSwipeBackLayout().setEnableGesture(false);
        mainRg.setOnCheckedChangeListener(this);
        mainRbFootprint.setChecked(true);
        showTipsDialog();
    }

    private void showTipsDialog() {
        new FastDialog(this)
                .setTitle("测试标题")
                .setContent("1.这是第一个版本测试;\n2.有什么bug及时反馈;\n3.就算反馈了也不会修复的^_^")
                .setPositiveButton("立即举报", new FastDialog.OnClickListener() {
                    @Override
                    public void onClick(FastDialog dialog) {
                        T.showShort(MainActivity.this, "举报成功...");
                    }
                })
                .setNegativeButton("默默离开", new FastDialog.OnClickListener() {
                    @Override
                    public void onClick(FastDialog dialog) {
                        T.showShort(MainActivity.this, "走好不送...");
                    }
                })
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .create().show();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        L.e("reyzarc",checkedId+"---->");
        switch (checkedId) {
            case R.id.main_rb_footprint://足迹
                switchStatusBarColor(true);
                mainRbFootprint.setChecked(true);
                if (mFootprintFragment == null) {
                    mFootprintFragment = new FootprintFragment();
                }
                switchFragment(mFootprintFragment);


                break;
            case R.id.main_rb_message://消息
                mainRbMessage.setChecked(true);
                if (mMessageFragment == null) {
                    mMessageFragment = new MessageFragment();
                }
                switchFragment(mMessageFragment);
                switchStatusBarColor(true);
                break;
            case R.id.main_rb_friends://朋友
                mainRbFriends.setChecked(true);
                if (mFriendsFragment == null) {
                    mFriendsFragment = new FriendsFragment();
                }
                switchFragment(mFriendsFragment);
                switchStatusBarColor(false);
                break;
            case R.id.main_rb_more://更多
                mainRbMore.setChecked(true);
                if (mMoreFragment == null) {
                    mMoreFragment = new MoreFragment();
                }
                switchFragment(mMoreFragment);
                switchStatusBarColor(false);
                break;
        }
    }

    /**
     * 切换状态栏样式
     * @param isTransparent 状态栏是否透明
     */
    private void switchStatusBarColor(boolean isTransparent) {
        //统一将状态栏颜色设置为透明,让fragment或者activity的topbar自己去控制颜色显示,从而有效避免切换时引起的视觉上的状态栏闪跳
//        if (isTransparent){//状态栏全透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
//                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏和导航栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            } else {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams
                        .FLAG_FULLSCREEN);
            }
//        }
//        else{//状态栏主题色
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
//                getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
//            }
//        }
    }

    /**
     * 切换fragment
     *
     * @param desFragment 即将跳转的fragment
     */
    private void switchFragment(Fragment desFragment) {
        //如果跳转的fragment不存在或者就是当前页面,则不处理
        if (desFragment == null || desFragment == mFromFragment) {
            return;
        }
        if (!desFragment.isAdded()) {//跳转的页面还没有被添加
            if (mFromFragment == null) {//页面不存在
                getSupportFragmentManager().beginTransaction().add(R.id.main_content, desFragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(mFromFragment).add(R.id.main_content, desFragment).commit();
            }
        } else {//跳转的页面已添加
            getSupportFragmentManager().beginTransaction().hide(mFromFragment).show(desFragment).commit();
        }
        mFromFragment = desFragment;
    }
}
