package com.xtec.timeline.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.xtec.timeline.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * 下拉刷新的头布局
 */
public class PtrHeader extends FrameLayout implements PtrUIHandler {


	private View headerView;
	private CircularProgressBar circularProgressBar;
	private ProgressBar pullToRefreshProgress;

	public PtrHeader(Context context) {
		super(context);
		init();
	}

	public PtrHeader(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		headerView = LayoutInflater.from(getContext()).inflate(R.layout.pull_to_refresh_head, this);
		circularProgressBar = (CircularProgressBar) headerView.findViewById(R.id.progress);
		pullToRefreshProgress = (ProgressBar) headerView.findViewById(R.id.pull_to_refresh_progress);
	}


	@Override
	public void onUIReset(PtrFrameLayout frame) {
		pullToRefreshProgress.setVisibility(View.INVISIBLE);
		circularProgressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void onUIRefreshPrepare(PtrFrameLayout frame) {
		pullToRefreshProgress.setVisibility(View.INVISIBLE);
		circularProgressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void onUIRefreshBegin(PtrFrameLayout frame) {
		pullToRefreshProgress.setVisibility(View.VISIBLE);
		circularProgressBar.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onUIRefreshComplete(PtrFrameLayout frame) {
		pullToRefreshProgress.setVisibility(View.INVISIBLE);
		circularProgressBar.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator
			ptrIndicator) {
		float percent = Math.min(1f, ptrIndicator.getCurrentPercent());
		if (status == PtrFrameLayout.PTR_STATUS_PREPARE) {
			circularProgressBar.setProgress((int) (percent * 100 * 1.0));
			invalidate();
		}

	}
}
