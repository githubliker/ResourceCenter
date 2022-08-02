package com.surface.resourcecenter.ui.device.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.home.adapter.HomePageTaskAdapter;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {

    private List<String> mItemList;
    private RecyclerView mRootRecycler;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        Button del,edit;
        public ViewHolder(View view) {
            super(view);
            itemName = (TextView) view.findViewById(R.id.item_name);
        }

    }

    public DeviceAdapter(List<String> fruitList) {
        mItemList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_fragment_device_detail_item, parent, false);
        mRootRecycler = (RecyclerView) parent;
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    int position = mRootRecycler.getChildAdapterPosition(v);
                    v.setTag(mItemList.get(position));
                    listener.onClick(v);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String fruit = mItemList.get(position);
        holder.itemName.setText(fruit);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
    public interface onSwipeListener {
        void onDel(int pos);
        void onEdit(int pos);
    }

    private onSwipeListener mOnSwipeListener;

    public void setOnSwipeListener(onSwipeListener listener) {
        mOnSwipeListener = listener;
    }
    private View.OnClickListener listener;
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
}