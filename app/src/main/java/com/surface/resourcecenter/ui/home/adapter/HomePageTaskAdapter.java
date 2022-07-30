package com.surface.resourcecenter.ui.home.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.ui.home.adapter.bean.HomeItem;

import java.util.List;

public class HomePageTaskAdapter extends RecyclerView.Adapter<HomePageTaskAdapter.ViewHolder> {

    private List<HomeItem> mItemList;
    private RecyclerView mRootRecycler;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;

        public ViewHolder(View view) {
            super(view);
            itemImage = (ImageView) view.findViewById(R.id.item_image);
            itemName = (TextView) view.findViewById(R.id.item_name);
        }

    }

    public HomePageTaskAdapter(List<HomeItem> fruitList) {
        mItemList = fruitList;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_fragment_home_page_item, parent, false);
        mRootRecycler = (RecyclerView) parent;
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    int position = mRootRecycler.getChildAdapterPosition(v);
                    v.setTag(mItemList.get(position).getItemName());
                    listener.onClick(v);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        HomeItem fruit = mItemList.get(position);
        holder.itemImage.setImageResource(fruit.getItemId());
        holder.itemName.setText(fruit.getItemName());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    private View.OnClickListener listener;
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
}