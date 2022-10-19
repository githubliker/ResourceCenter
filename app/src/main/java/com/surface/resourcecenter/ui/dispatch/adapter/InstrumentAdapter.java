package com.surface.resourcecenter.ui.dispatch.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.listener.onRecycleViewItemClickListener;
import com.surface.resourcecenter.ui.dispatch.bean.InstrumentBean;
import com.surface.resourcecenter.ui.dispatch.bean.SystemRole;

import java.util.List;

public class InstrumentAdapter extends RecyclerView.Adapter<InstrumentAdapter.ViewHolder> {

    private List<InstrumentBean> mItemList;
    private RecyclerView mRootRecycler;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        View status;

        public ViewHolder(View view) {
            super(view);
            itemName = (TextView) view.findViewById(R.id.text_item);
            status =  view.findViewById(R.id.text_role);
            status.setBackgroundResource(R.color.holo_orange_light);
        }

    }

    public InstrumentAdapter(List<InstrumentBean> fruitList) {
        mItemList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_fragment_manager_usr_item, parent, false);
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

        InstrumentBean fruit = mItemList.get(position);
        holder.itemName.setText(fruit.getName());
        if(fruit.isSelect()){
            holder.status.setVisibility(View.VISIBLE);
        } else {
            holder.status.setVisibility(View.INVISIBLE);
        }
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