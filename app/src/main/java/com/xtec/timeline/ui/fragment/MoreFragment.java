package com.xtec.timeline.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xtec.timeline.R;
import com.xtec.timeline.utils.L;

/**
 * Created by 武昌丶鱼 on 2016/10/19.
 * Description:
 */
public class MoreFragment extends Fragment {
    private static final String TAG = "MoreFragment";
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        L.e("reyzarc", "more onCreateView is running...");

        view = inflater.inflate(R.layout.fragment_footprint, null);

        return view;
    }
}
