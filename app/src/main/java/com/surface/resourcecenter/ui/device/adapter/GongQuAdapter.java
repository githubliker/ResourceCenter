package com.surface.resourcecenter.ui.device.adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.dispatch.bean.TestArea;
import com.surface.resourcecenter.ui.home.adapter.bean.HomeItem;

import java.io.IOException;
import java.util.List;

public class GongQuAdapter extends RecyclerView.Adapter<GongQuAdapter.ViewHolder> {

    private List<TestArea> mItemList;
    private RecyclerView mRootRecycler;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        Button del,edit;
        public ViewHolder(View view) {
            super(view);
            itemName = (TextView) view.findViewById(R.id.item_name);
            del = (Button) view.findViewById(R.id.btnDelete);
            edit = (Button) view.findViewById(R.id.btnEdit);
        }

    }

    public GongQuAdapter() {
    }

    public void setData(List<TestArea> fruitList) {
        mItemList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_fragment_device_gongqu_item, parent, false);
        mRootRecycler = (RecyclerView) parent;
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        (holder.itemName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    v.setTag(mItemList.get(position));
                    listener.onClick(v);
                }
            }
        });
        (holder.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick() called with: v = [" + v + "]");
                if(mOnSwipeListener != null){
                    mOnSwipeListener.onEdit(holder.getAdapterPosition());
                }
            }
        });
        (holder.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick() called with: v = [" + v + "]");
                if(mOnSwipeListener != null){
                    mOnSwipeListener.onDel(holder.getAdapterPosition());
                }
            }
        });
        TestArea fruit = mItemList.get(position);
        holder.itemName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mItemList == null ?0:mItemList.size();
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