package com.xtec.timeline.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xtec.timeline.R;
import com.xtec.timeline.utils.L;
import com.xtec.timeline.widget.MultiImageView;
import com.xtec.timeline.widget.Topbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author       : hulong
 * e-mail       : reyzarc@163.com
 * create date  : 2018/11/1
 * description  :
 */


public class MultiImageViewActivity extends BaseActivity {

    @BindView(R.id.topbar)
    Topbar topbar;
    @BindView(R.id.multi_image_view)
    MultiImageView multiImageView;
    @BindView(R.id.btn_image_one)
    Button btnImageOne;
    @BindView(R.id.btn_image_two)
    Button btnImageTwo;
    @BindView(R.id.btn_image_three)
    Button btnImageThree;
    @BindView(R.id.btn_image_more)
    Button btnImageMore;

    private List<String> mImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_image);
        ButterKnife.bind(this);
        initTopbar(this, topbar);

        mImageList = new ArrayList<>();

        multiImageView.setOnImageClickListener(new MultiImageView.OnImageClickListener() {
            @Override
            public void onImageClick() {
                L.e("reyzarc","======hahah=====?");
            }
        });


    }

    @OnClick({R.id.btn_image_one, R.id.btn_image_two, R.id.btn_image_three, R.id.btn_image_more})
    public void onViewClicked(View view) {
        mImageList.clear();
        switch (view.getId()) {
            case R.id.btn_image_one:
                mImageList.add("http://pic.58pic.com/58pic/15/63/07/42Q58PIC42U_1024.jpg");
                break;
            case R.id.btn_image_two:
                mImageList.add("http://img4.imgtn.bdimg.com/it/u=2095807035,495047869&fm=26&gp=0.jpg");
                mImageList.add("http://img0.imgtn.bdimg.com/it/u=3598550315,1737316217&fm=26&gp=0.jpg");
                break;
            case R.id.btn_image_three:
                mImageList.add("http://pic19.nipic.com/20120210/7827303_221233267358_2.jpg");
                mImageList.add("http://img3.imgtn.bdimg.com/it/u=2909963326,666451865&fm=26&gp=0.jpg");
                mImageList.add("http://img2.imgtn.bdimg.com/it/u=4240798935,1418320635&fm=26&gp=0.jpg");
                break;
            case R.id.btn_image_more:
                mImageList.add("http://img1.imgtn.bdimg.com/it/u=3363188967,2176324184&fm=26&gp=0.jpg");
                mImageList.add("http://img2.imgtn.bdimg.com/it/u=3950012880,3404836230&fm=200&gp=0.jpg");
                mImageList.add("http://img1.imgtn.bdimg.com/it/u=845960666,1454575331&fm=200&gp=0.jpg");
                mImageList.add("http://img1.imgtn.bdimg.com/it/u=1358850884,4247519559&fm=200&gp=0.jpg");
                mImageList.add("http://img1.imgtn.bdimg.com/it/u=1790454820,2803815505&fm=26&gp=0.jpg");
                mImageList.add("http://img1.imgtn.bdimg.com/it/u=2388983070,3514068383&fm=200&gp=0.jpg");
                mImageList.add("http://img3.imgtn.bdimg.com/it/u=4038700540,2625172863&fm=200&gp=0.jpg");


                break;
        }
        multiImageView.setImageList(mImageList);
    }
}
