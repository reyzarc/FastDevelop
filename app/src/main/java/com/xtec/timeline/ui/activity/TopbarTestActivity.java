package com.xtec.timeline.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xtec.timeline.R;
import com.xtec.timeline.utils.T;
import com.xtec.timeline.widget.Topbar;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 武昌丶鱼 on 2017/4/19.
 * Description:
 */

public class TopbarTestActivity extends BaseActivity {
    @BindView(R.id.topbar)
    Topbar topbar;
    @BindView(R.id.topbar2)
    Topbar topbar2;
    @BindView(R.id.topbar3)
    Topbar topbar3;
    @BindView(R.id.tv_before)
    TextView tvBefore;
    @BindView(R.id.tv_after)
    TextView tvAfter;
    @BindView(R.id.tv_after_bubble)
    TextView tvAfterBubble;

    private int[] arr = {11, 2, 5, 9, 4, 22, 6, 10};
    private int[] result = new int[arr.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topbar_test);
        ButterKnife.bind(this);
        initTopbar(this, topbar);
        topbar.setRightIcon(getResources().getDrawable(R.drawable.ic_launcher), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.showShort(TopbarTestActivity.this, "点击了---->right");
            }
        });

        topbar2.setLeftText("测试", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.showShort(TopbarTestActivity.this, "点击了---->" + ((TextView) view).getText());
            }
        });

        topbar2.setRightText("右边", getResources().getDrawable(R.drawable.ic_back_white), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.showShort(TopbarTestActivity.this, "点击了---->" + ((TextView) view).getText());
            }
        });

        tvBefore.setText("原始数据是:" + Arrays.toString(arr));
        doSort(arr);
//        tvAfter.setText("结果是:" + Arrays.toString(arr));
    }

    private void doSort(int[] arr) {
        //选择排序
        int temp;
        for (int i = 0; i < arr.length; i++) {
            int k = i;//待确定位置
            for (int j = arr.length - 1; j > i; j--) {//当前位置跟后面每个数比较,如果小,则交换角标
                if (arr[j] < arr[k]) {
                    k = j;
                }
            }
            //这样得到的角标k就是最小的数据,这个时候将这个数据放在待确定的位置上
            temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
        }

        tvAfter.setText("选择排序从小到大:" + Arrays.toString(arr));

        //冒泡排序,从大到小排序
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {//比较相邻两数,如果大,则交换位置
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        tvAfterBubble.setText("冒泡排序从大到小:" + Arrays.toString(arr));
    }
}
