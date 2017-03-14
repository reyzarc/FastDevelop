package com.xtec.timeline.ui.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xtec.timeline.R;
import com.xtec.timeline.utils.L;
import com.xtec.timeline.utils.UIUtils;
import com.xtec.timeline.widget.MultiStateView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by 武昌丶鱼 on 2016/10/19.
 * Description:
 */
public class FriendsFragment extends Fragment {
    private static final String TAG = "FriendsFragment";
    @BindView(R.id.topbar_left)
    ImageButton topbarLeft;
    @BindView(R.id.topbar_title)
    TextView topbarTitle;
    @BindView(R.id.topbar_right)
    ImageButton topbarRight;
    @BindView(R.id.mv_state)
    MultiStateView mvState;
    @BindView(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;
    @BindView(R.id.tv)
    TextView tv;

    private View view;
    private CountDownTimer timer;

    @Override
    public void onResume() {
        super.onResume();
        L.e("reyzarc", "friends onResume is running...");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_friends, null);
        View topBar = view.findViewById(R.id.friends_topbar);
        UIUtils.initTopbar(getActivity(), topBar, false);
        ((TextView) topBar.findViewById(R.id.topbar_title)).setText("朋友");
        topBar.setBackgroundColor(getResources().getColor(R.color.green));

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        UIUtils.ptrFrameAddHeader(getContext(), ptrFrame);
        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, tv, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mvState.setViewState(MultiStateView.ViewState.CONTENT);
                // do something...
                timer =  new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long l) {
                        tv.setText(l/1000+"");
                    }

                    @Override
                    public void onFinish() {
                        ptrFrame.refreshComplete();
                        mvState.setViewState(MultiStateView.ViewState.EMPTY);
                    }
                }.start();
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
