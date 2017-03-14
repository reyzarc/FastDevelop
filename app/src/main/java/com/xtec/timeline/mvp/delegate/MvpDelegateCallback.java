package com.xtec.timeline.mvp.delegate;


import android.support.annotation.NonNull;

import com.xtec.timeline.mvp.presenter.MvpPresenter;
import com.xtec.timeline.mvp.view.MvpView;


/**
 * Created by 冯浩 on 2016/6/28.
 */
public interface MvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>> {

    /**
     * 创建 Presenter
     */
    @NonNull
    P createPresenter();

    /**
     * 返回MVP Presenter
     */
    P getPresenter();

    /**
     * 设置 Presenter
     */
    void setPresenter(P presenter);

    /**
     * 返回MVP 中的View
     */
    V getMvpView();


}
