package com.xtec.timeline.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.xtec.timeline.R;
import com.xtec.timeline.model.PlanInfoModel;
import com.xtec.timeline.ui.adapter.PlanListAdapter;
import com.xtec.timeline.utils.T;
import com.xtec.timeline.utils.UIUtils;
import com.xtec.timeline.widget.Topbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 武昌丶鱼 on 2017/5/17.
 * Description:时间计划页面,提供的功能包括设置计划列表,到设定时间检查手机屏幕开关状态,
 * 如果是亮屏,则在屏幕上显示一方块,并随着时间的增加不断变大,此时用户可拖动方块,但不可消除,
 * 直到铺满整个屏幕(显示提示文字:保持专注),持续时间结束,则当前周期的这个计划完成.
 * 当计划时间启动时用户可长按方块5s强制取消当前计划(屏幕破碎效果),拟提供延期功能,弹窗提示整个延期还是只延期当前
 * 列表显示,侧滑弹出,编辑,删除
 */

public class TimePlanActivity extends BaseActivity {


    @BindView(R.id.topbar)
    Topbar topbar;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.lv_list)
    SwipeMenuListView lvList;
    private List<PlanInfoModel> mPlanList;
    private PlanListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_plan);
        ButterKnife.bind(this);
        initTopbar(this, topbar);
        initView();
        initData();
    }

    private void initData() {
        //获取计划列表
//        String str = "";
//        mPlanList = new Gson().fromJson(str,new TypeToken<List<PlanInfoModel>>(){}.getType());
        mPlanList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PlanInfoModel model = new PlanInfoModel();
            model.setDuration("" + 100 * i);
            model.setPlanTitle("跑步" + i);
            model.setStartTime("18:00");
            mPlanList.add(model);
        }

        if (!mPlanList.isEmpty()) {
            mAdapter.setData(mPlanList);
        }
    }

    private void initView() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(UIUtils.dip2px(TimePlanActivity.this, 80));
                // set item title
                openItem.setTitle("设为默认");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);


                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(UIUtils.dip2px(TimePlanActivity.this, 80));
                // set a icon
                deleteItem.setIcon(R.drawable.delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        //设置方向
        lvList.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
// set creator
        lvList.setMenuCreator(creator);

        lvList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0://设置为默认
                        T.showShort(TimePlanActivity.this, "设置默认...");


                        break;
                    case 1://删除
                        T.showShort(TimePlanActivity.this, "删除...");


                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        mAdapter = new PlanListAdapter(this);
        lvList.setAdapter(mAdapter);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {//跳转到编辑银行卡界面
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                T.showShort(TimePlanActivity.this, "点击了第..." + position);

            }
        });
    }
}
