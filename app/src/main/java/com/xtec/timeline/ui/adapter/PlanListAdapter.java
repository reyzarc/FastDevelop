package com.xtec.timeline.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xtec.timeline.R;
import com.xtec.timeline.model.PlanInfoModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 武昌丶鱼 on 2017/5/17.
 * Description:计划列表
 */

public class PlanListAdapter extends BaseAdapter {
    private Context mContext;
    private List<PlanInfoModel> mList;

    public PlanListAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<PlanInfoModel> planList) {
        mList = planList;
        notifyDataSetChanged();
    }

    public void addData(List<PlanInfoModel> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_plan_list, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        PlanInfoModel model = mList.get(i);
        viewHolder.tvTitle.setText(model.getPlanTitle());
        viewHolder.tvDuration.setText(model.getDuration());
        viewHolder.tvStartTime.setText(model.getStartTime());

        return view;
    }


    static class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_start_time)
        TextView tvStartTime;
        @BindView(R.id.tv_duration)
        TextView tvDuration;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
