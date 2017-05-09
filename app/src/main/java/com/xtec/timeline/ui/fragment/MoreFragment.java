package com.xtec.timeline.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xtec.timeline.R;
import com.xtec.timeline.ui.activity.WidgetDemoActivity;
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
    @BindView(R.id.topbar_left)
    ImageButton topbarLeft;
    @BindView(R.id.topbar_title)
    TextView topbarTitle;
    @BindView(R.id.topbar_right)
    ImageButton topbarRight;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.ll_drawer)
    LinearLayout llDrawer;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        L.e("reyzarc", "more onCreateView is running...");

        view = inflater.inflate(R.layout.fragment_more, null);

        View topBar = view.findViewById(R.id.more_topbar);
        UIUtils.initTopbar(getActivity(), topBar, false);

        ((TextView) topBar.findViewById(R.id.topbar_title)).setText("更多");

        topBar.setBackgroundColor(getResources().getColor(R.color.blue));

        ButterKnife.bind(this, view);

        topbarRight.setImageResource(R.drawable.ic_star);
        return view;
    }

    @OnClick({R.id.tv_more,R.id.topbar_right})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_more:
//                UpdateManager.checkNewVersion(getActivity(), false);
                startActivity(new Intent(getActivity(), WidgetDemoActivity.class));
                drawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.topbar_right:
                if(drawerLayout.isDrawerOpen(llDrawer)){
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }else{
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
