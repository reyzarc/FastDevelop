package com.xtec.timeline.mvp.delegate;

import android.os.Bundle;

import com.xtec.timeline.mvp.presenter.MvpPresenter;
import com.xtec.timeline.mvp.view.MvpView;


/**
 * Created by 冯浩 on 2016/6/28.
 */
public interface ActivityMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {


    void onCreate(Bundle bundle);


    void onDestroy();


    void onPause();


    void onResume();


    void onStart();


    void onStop();


    void onRestart();

}
