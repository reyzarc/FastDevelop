package com.xtec.timeline.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xtec.timeline.R;
import com.xtec.timeline.common.UpdateManager;
import com.xtec.timeline.utils.L;
import com.xtec.timeline.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 武昌丶鱼 on 2016/10/19.
 * Description:
 */
public class MoreFragment extends Fragment {
    private static final String TAG = "MoreFragment";
    @BindView(R.id.tv_more)
    TextView tvMore;
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        L.e("reyzarc", "more onCreateView is running...");

        view = inflater.inflate(R.layout.fragment_more, null);

        View topBar = view.findViewById(R.id.more_topbar);
        UIUtils.initTopbar(getActivity(), topBar, false);

        ((TextView) topBar.findViewById(R.id.topbar_title)).setText("更多");

        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.tv_more)
    public void onClick() {
        UpdateManager.checkNewVersion(getActivity(),false);
    }
}
