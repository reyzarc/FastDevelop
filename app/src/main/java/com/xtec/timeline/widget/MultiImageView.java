package com.xtec.timeline.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xtec.timeline.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author       : hulong
 * e-mail       : reyzarc@163.com
 * create date  : 2018/11/1
 * description  :
 */


public class MultiImageView extends LinearLayout implements View.OnClickListener {

    ImageView ivOneImage;
    ImageView ivTwoLeft;
    ImageView ivTwoRight;
    LinearLayout llTwoImages;
    ImageView ivThreeLeft;
    ImageView ivThreeRightTop;
    ImageView ivThreeRightBottom;
    LinearLayout llThreeImages;
    TextView tvMoreImages;

    private View mView;
    private Context mContext;
    private List<String> mImageList;
    private OnImageClickListener mListener;

    public MultiImageView(Context context) {
        this(context, null);
    }

    public MultiImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        mImageList = new ArrayList<>();
        mView = LayoutInflater.from(context).inflate(R.layout.layout_multi_image, null);
        ivThreeRightBottom = (ImageView) mView.findViewById(R.id.iv_three_right_bottom);
        ivThreeRightTop = (ImageView) mView.findViewById(R.id.iv_three_right_top);
        ivThreeLeft = (ImageView) mView.findViewById(R.id.iv_three_left);
        ivTwoRight = (ImageView) mView.findViewById(R.id.iv_two_right);
        ivTwoLeft = (ImageView) mView.findViewById(R.id.iv_two_left);
        ivOneImage = (ImageView) mView.findViewById(R.id.iv_one_image);
        tvMoreImages = (TextView) mView.findViewById(R.id.tv_more_images);
        llThreeImages = (LinearLayout) mView.findViewById(R.id.ll_three_images);
        llTwoImages = (LinearLayout) mView.findViewById(R.id.ll_two_images);

        ivOneImage.setOnClickListener(this);
        ivTwoLeft.setOnClickListener(this);
        ivTwoRight.setOnClickListener(this);
        ivThreeLeft.setOnClickListener(this);
        ivThreeRightTop.setOnClickListener(this);
        ivThreeRightBottom.setOnClickListener(this);

        LinearLayout.LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mView, lp);
    }

    public void setImageList(List<String> list) {
        mImageList = list;
        updateUi();
    }

    private void updateUi() {
        if (!mImageList.isEmpty()) {
            int size = mImageList.size();
            switch (size) {
                case 1:
                    ivOneImage.setVisibility(VISIBLE);
                    llTwoImages.setVisibility(GONE);
                    llThreeImages.setVisibility(GONE);
                    Picasso.with(mContext).load(mImageList.get(0)).placeholder(R.drawable.ic_launcher).into(ivOneImage);
                    break;
                case 2:
                    ivOneImage.setVisibility(GONE);
                    llTwoImages.setVisibility(VISIBLE);
                    llThreeImages.setVisibility(GONE);
                    Picasso.with(mContext).load(mImageList.get(0)).placeholder(R.drawable.ic_launcher).into(ivTwoLeft);
                    Picasso.with(mContext).load(mImageList.get(1)).placeholder(R.drawable.ic_launcher).into(ivTwoRight);
                    break;
                case 3:
                default:
                    ivOneImage.setVisibility(GONE);
                    llTwoImages.setVisibility(GONE);
                    llThreeImages.setVisibility(VISIBLE);
                    Picasso.with(mContext).load(mImageList.get(0)).placeholder(R.drawable.ic_launcher).into(ivThreeLeft);
                    Picasso.with(mContext).load(mImageList.get(1)).placeholder(R.drawable.ic_launcher).into(ivThreeRightTop);
                    Picasso.with(mContext).load(mImageList.get(2)).placeholder(R.drawable.ic_launcher).into(ivThreeRightBottom);
                    tvMoreImages.setText(size - 3+"");
                    break;
            }
        }
    }

    public void setOnImageClickListener(OnImageClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onImageClick();
        }
    }


    public interface OnImageClickListener {
        void onImageClick();
    }


}
