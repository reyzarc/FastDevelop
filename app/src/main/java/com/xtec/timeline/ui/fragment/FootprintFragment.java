package com.xtec.timeline.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xtec.timeline.R;
import com.xtec.timeline.ui.activity.TestActivity;
import com.xtec.timeline.utils.L;
import com.xtec.timeline.utils.T;
import com.xtec.timeline.utils.UIUtils;
import com.xtec.timeline.widget.ObservableScrollView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 武昌丶鱼 on 2016/10/19.
 * Description:
 */
public class FootprintFragment extends Fragment {
    private static final String TAG = "FootprintFragment";
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_footprint)
    TextView tvFootprint;
    @BindView(R.id.footprint_ll_dots)
    LinearLayout footprintLlDots;
    @BindView(R.id.footprint_sv)
    ObservableScrollView footprintSv;

    private View view;
    private int imgRes[] = new int[]{R.drawable.splash, R.drawable.ic_launcher, R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};

    private Timer mTimer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_footprint, null);
        ButterKnife.bind(this, view);

        final View topbar = view.findViewById(R.id.footprint_topbar);
        UIUtils.initTopbar(getActivity(), topbar, true);
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) topbar.getLayoutParams();
//        params.setMargins(0,UIUtils.getStatusBarHeight(getActivity()),0,0);
//        topbar.setLayoutParams(params);

//        topbar.setBackgroundColor(getResources().getColor(R.color.dots_container_color));
        //根据scrollview动态改变状态栏的颜色
        final int topbarHeight = UIUtils.getTopBarHeight(getActivity());
        footprintSv.setOnScrollChangedListener(new ObservableScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    topbar.setBackgroundColor(getResources().getColor(R.color.transparent));
                } else if (y > 0 && y <= topbarHeight) {
                    float scale = (float) y / topbarHeight;
                    float alpha = (255 * scale);
                    topbar.setBackgroundColor(Color.argb((int) alpha, 255, 110, 0));
                } else {
                    topbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });


        viewpager.setAdapter(new MyPagerAdapter());
        viewpager.setCurrentItem(imgRes.length * 100);
        UIUtils.addNavigationDot(getActivity(), footprintLlDots, 0, imgRes.length, R.drawable.gray_dot, R.drawable.red_dot);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                UIUtils.addNavigationDot(getActivity(), footprintLlDots, position % imgRes.length, imgRes.length, R.drawable.gray_dot, R.drawable.red_dot);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //按下时暂停自动轮播,抬起时自动
        viewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                L.e("reyzarc", "onTouch is running...");
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mTimer != null) {
                            mTimer.cancel();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        startTimer();
                        break;
                }

                return false;
            }
        });

        return view;
    }

    private Handler bannerHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);
        }
    };

    @OnClick(R.id.tv_footprint)
    public void onClick() {
        startActivity(new Intent(getActivity(), TestActivity.class));
    }

    class MyPagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView iv = new ImageView(getActivity());
            iv.setImageResource(imgRes[position % imgRes.length]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    T.showShort(getActivity(),"第"+position%imgRes.length+"个banner");
                }
            });
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        startTimer();
    }

    private void startTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                bannerHandler.obtainMessage().sendToTarget();
            }
        }, 3000, 3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}
