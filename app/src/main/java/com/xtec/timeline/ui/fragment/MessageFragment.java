package com.xtec.timeline.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xtec.timeline.R;
import com.xtec.timeline.ui.activity.WidgetDemoActivity;
import com.xtec.timeline.utils.UIUtils;
import com.xtec.timeline.widget.Topbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 武昌丶鱼 on 2016/10/19.
 * Description:
 */
public class MessageFragment extends Fragment {
    private static final String TAG = "MessageFragment";
    @BindView(R.id.topbar_left)
    ImageButton topbarLeft;
    @BindView(R.id.topbar_title)
    TextView topbarTitle;
    @BindView(R.id.topbar_right)
    ImageButton topbarRight;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.topbar)
    Topbar topbar;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_message, null);


//        View topBar = view.findViewById(R.id.message_topbar);
//        UIUtils.initTopbar(getActivity(), topBar, false);
//
//        ((TextView)topBar.findViewById(R.id.topbar_title)).setText("消息");


        ButterKnife.bind(this, view);
        UIUtils.initTopbar(getActivity(),topbar,false);
        return view;
    }

    @OnClick(R.id.tv_message)
    public void onClick() {

        startActivity(new Intent(getActivity(), WidgetDemoActivity.class));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
