package com.surface.resourcecenter.ui.home.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.listener.onRecycleViewItemClickListener;
import com.surface.resourcecenter.ui.dispatch.bean.DispatchBean;
import com.surface.resourcecenter.ui.home.adapter.bean.HomeItem;
import com.surface.resourcecenter.ui.home.adapter.bean.TaskItem;

import java.util.List;

public class HomePageToDoTaskAdapter extends RecyclerView.Adapter<HomePageToDoTaskAdapter.ViewHolder> {

    private List<DispatchBean> mItemList;
    private RecyclerView mRootRecycler;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemStartTime;
        TextView itemUpdataTime;
        TextView itemStartPersion;
        TextView itemCurrentPersion;
        TextView itemStatus;

        public ViewHolder(View view) {
            super(view);
            itemName = (TextView) view.findViewById(R.id.item_name);
            itemStartPersion = (TextView) view.findViewById(R.id.start_persion);
            itemStartTime = (TextView) view.findViewById(R.id.start_time);
            itemUpdataTime = (TextView) view.findViewById(R.id.update_time);
            itemCurrentPersion = (TextView) view.findViewById(R.id.current_persion);
            itemStatus = (TextView) view.findViewById(R.id.item_status);

        }

    }

    public HomePageToDoTaskAdapter(List<DispatchBean> fruitList) {
        mItemList = fruitList;
    }

    public void setAdapterData(List<DispatchBean> fruitList){
        mItemList.clear();
        mItemList.addAll(fruitList);
    }
    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_fragment_todo_task_item, parent, false);
        mRootRecycler = (RecyclerView) parent;
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    int position = mRootRecycler.getChildAdapterPosition(v);
                    listener.onClick(v,position);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DispatchBean fruit = mItemList.get(position);
        holder.itemName.setText(fruit.getSampleName()+"1");
        holder.itemStartTime.setText("任务开始时间:"+fruit.getStartTime());
        holder.itemUpdataTime.setText("上次更新时间:"+fruit.getEndTime());
        holder.itemCurrentPersion.setText(fruit.getHandlerName());
        holder.itemStartPersion.setText(fruit.getInitiatorName());
        holder.itemStatus.setText(fruit.getState()+"");
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    private onRecycleViewItemClickListener listener;
    public void setOnClickListener(onRecycleViewItemClickListener listener){
        this.listener = listener;
    }
}