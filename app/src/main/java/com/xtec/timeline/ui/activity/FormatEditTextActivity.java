package com.xtec.timeline.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xtec.timeline.R;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 武昌丶鱼 on 2017/3/16.
 * Description:
 */

public class FormatEditTextActivity extends BaseActivity {

    @BindView(R.id.topbar_left)
    ImageButton topbarLeft;
    @BindView(R.id.topbar_title)
    TextView topbarTitle;
    @BindView(R.id.topbar_right)
    ImageButton topbarRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_format_edit_text);
        ButterKnife.bind(this);
        View topbar = findViewById(R.id.topbar);
        initTopbar(this, topbar,R.color.white, true);
        topbarTitle.setText("格式化手机/卡号");

        //选择排序,思想就是每循环一次,选择最小/最大的,跟当前指针交换位置,直到列表有序
        int[] arr = {6,1,54,22,0,12,8};
        int index;
        Log.e("reyzarc","排序前数组--->"+ Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            index = i;//当前排序指针,用于记录当前循环所得到的最大/最小数的角标
            for (int j = i+1; j < arr.length; j++) {//比较指针位置与后面每一个数字
                if(arr[j]>arr[index]){//从大到小
                    index = j;
                }
            }
            //一次循环完成后交换数据
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }

        Log.e("reyzarc","从大到小选择排序后数组--->"+ Arrays.toString(arr));

        //冒泡排序,思想是比较相邻两个数字,大或/小的往后移动,直到数组有序
        int[] arr2 = {6,1,54,22,0,12,8};
        int temp2;
        for (int i = 0; i < arr2.length-1; i++) {
            for (int j = 0; j < arr2.length-i-1; j++) {
                if(arr2[j]>arr2[j+1]){//比较相邻两个数据,从小到大
                    temp2 = arr2[j];
                    arr2[j] = arr2[j+1];
                    arr2[j+1] = temp2;
                }
            }
        }
        Log.e("reyzarc","从小到大冒泡排序后数组为----->"+Arrays.toString(arr2));

    }
}
