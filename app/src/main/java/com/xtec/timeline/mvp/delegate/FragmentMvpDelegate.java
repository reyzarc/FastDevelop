package com.xtec.timeline.mvp.delegate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xtec.timeline.mvp.presenter.MvpPresenter;
import com.xtec.timeline.mvp.view.MvpView;


/**
 * Created by 冯浩 on 2016/6/28.
 */
public interface FragmentMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {

    void onCreate(Bundle saved);


    void onDestroy();


    void onViewCreated(View view, @Nullable Bundle savedInstanceState);


    void onDestroyView();


    void onPause();


    void onResume();


    void onStart();


    void onStop();


    void onActivityCreated(Bundle savedInstanceState);


    void onAttach(Context context);


    void onDetach();


}
