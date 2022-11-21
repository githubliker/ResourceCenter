package com.surface.resourcecenter.ui.dispatch.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.listener.onItemCheckChangeListener;
import com.surface.resourcecenter.ui.sample.bean.TestItemsBean;

import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: adapter
 * 作者|时间: djj on 2019/4/26 17:16
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */

public class StandardAdapter extends RecyclerView.Adapter<StandardAdapter.MyViewHolder> {

    private List<TestItemsBean> list;
    private Context mContext;
    //用来记录所有checkbox的状态
    private Map<Integer, Boolean> checkStatus = new HashMap<>();

    public StandardAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void initData(List<TestItemsBean> mData) {
        if(list == null){
            list = new ArrayList<TestItemsBean>();
        } else {
            list.clear();
        }
        list = mData;
        initCheck(true);
    }

    //全选
    public void selectAll() {
        initCheck(true);
        notifyDataSetChanged();
    }

    //全不选
    public void unSelectAll() {
        initCheck(false);
        notifyDataSetChanged();
    }

    //更改集合内部存储的状态
    public void initCheck(boolean flag) {
        for (int i = 0; i < list.size(); i++) {
            //更改指定位置的数据
            list.get(i).setChecked(flag);
            checkStatus.put(i, flag);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rc_fragment_standard_page_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        TestItemsBean bean = list.get(position);
        if(bean.getArea() != null && bean.getArea().size() > 0){
            holder.mArea.setText(bean.getArea().get(0).getName()+"...");
            holder.mArea.setTextColor(mContext.getResources().getColor(R.color.app_main_color));
        } else {
            holder.mArea.setText("试验工区");
            holder.mArea.setTextColor(mContext.getResources().getColor(R.color.text_noselected));
        }
        if(bean.getPerson() != null && bean.getPerson().size() >0){
            holder.mPerson.setText(bean.getPerson().get(0).getName());
            holder.mPerson.setTextColor(mContext.getResources().getColor(R.color.app_main_color));
        } else {
            holder.mPerson.setText("试验人员");
            holder.mPerson.setTextColor(mContext.getResources().getColor(R.color.text_noselected));
        }
        if(bean.getInstrument() != null && bean.getInstrument().size() >0){
            holder.mInstrument.setText(bean.getInstrument().get(0).getName());
            holder.mInstrument.setTextColor(mContext.getResources().getColor(R.color.app_main_color));
        } else {
            holder.mInstrument.setText("试验设备");
            holder.mInstrument.setTextColor(mContext.getResources().getColor(R.color.text_noselected));
        }
        holder.checkBox.setText(bean.getName());
        //清除监听器
        holder.checkBox.setOnCheckedChangeListener(null);
        //设置选中状态
        holder.checkBox.setChecked(checkStatus.get(position));
        //再设置一次CheckBox的选中监听器，当CheckBox的选中状态发生改变时，把改变后的状态储存在Map中
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkStatus.put(position, isChecked);
                //check状态一旦改变，保存的check值也要发生相应的变化
                if(onCheckChangeListener != null){
                    onCheckChangeListener.onItemClick(holder.checkBox,position,isChecked);
                }
            }
        });
        holder.mDetailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onCheckChangeListener != null&& checkStatus.get(position)){
                    onCheckChangeListener.onItemClick(holder.mDetailLayout,position,true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    onItemCheckChangeListener onCheckChangeListener;
    public void setOnCheckChangeListener(onItemCheckChangeListener onCheckChangeListener){
        this.onCheckChangeListener = onCheckChangeListener;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBox;
        private LinearLayout mDetailLayout;
        private TextView mArea,mPerson,mInstrument;

        public MyViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
            mDetailLayout = itemView.findViewById(R.id.detail_layout);
            mArea = itemView.findViewById(R.id.area_text);
            mInstrument = itemView.findViewById(R.id.instrument_text);
            mPerson = itemView.findViewById(R.id.person_text);
        }
    }
}