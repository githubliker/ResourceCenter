package com.surface.resourcecenter.ui.shiyan.nativeData.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kelin.scrollablepanel.library.PanelAdapter;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.listener.onPanelItemClickListener;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelin on 16-11-18.
 */

public class ClickablePanelAdapter extends PanelAdapter {
    private static final int TITLE_TYPE = 4;
    private static final int ROOM_TYPE = 0;
    private static final int DATE_TYPE = 1;
    private static final int ORDER_TYPE = 2;

    private List<String> roomInfoList=new ArrayList<>();
    private List<String> dateInfoList = new ArrayList<>();
    private List<List<DataInfo>> ordersList =new ArrayList<>();


    @Override
    public int getRowCount() {
        return roomInfoList.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return dateInfoList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {
        int viewType = getItemViewType(row, column);
        switch (viewType) {
            case DATE_TYPE:
                setDateView(column, (ViewInXHolder) holder);
                break;
            case ROOM_TYPE:
                setRoomView(row, (ViewInYHolder) holder);
                break;
            case ORDER_TYPE:
                setOrderView(row, column, (DataViewHolder) holder);
                break;
            case TITLE_TYPE:
                break;
            default:
                setOrderView(row, column, (DataViewHolder) holder);
        }
    }

    public int getItemViewType(int row, int column) {
        if (column == 0 && row == 0) {
            return TITLE_TYPE;
        }
        if (column == 0) {
            return ROOM_TYPE;
        }
        if (row == 0) {
            return DATE_TYPE;
        }
        return ORDER_TYPE;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case DATE_TYPE:
                return new ViewInXHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_x_info, parent, false));
            case ROOM_TYPE:
                return new ViewInYHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_y_info, parent, false));
            case ORDER_TYPE:
                return new DataViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_data_info, parent, false));
            case TITLE_TYPE:
                return new TitleViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_title, parent, false));
            default:
                break;
        }
        return new DataViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_data_info, parent, false));
    }


    private void setDateView(int pos, ViewInXHolder viewHolder) {
        String  dataInfo = dateInfoList.get(pos - 1);
        if (dataInfo != null && pos > 0) {
            viewHolder.dateTextView.setText(dataInfo);
        }
    }

    private void setRoomView(int pos, ViewInYHolder viewHolder) {
        String roomInfo = roomInfoList.get(pos - 1);
        if (roomInfo != null && pos > 0) {
            viewHolder.roomTypeTextView.setText(roomInfo);
        }
    }

    private void setOrderView(final int row, final int column, DataViewHolder viewHolder) {
        final DataInfo orderInfo = ordersList.get(row - 1).get(column - 1);
        if (orderInfo != null) {
            viewHolder.view.setBackgroundResource(R.drawable.bg_white_gray_stroke);
            if(TextUtils.isEmpty(orderInfo.getDate()) ||orderInfo.getDate().equals("--")||orderInfo.getDate().contains("jpg")){
                viewHolder.statusTextView.setText(orderInfo.getDate());
            } else {
                String r = new BigDecimal(orderInfo.getDate()).toPlainString();
                viewHolder.statusTextView.setText(r);
            }
            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onClick(v,row-1,column - 1);
                    }

                }
            });
        }
    }
    onPanelItemClickListener listener;
    public void setOnItemClickListener(onPanelItemClickListener listener){
        this.listener = listener;
    }
    private static class ViewInXHolder extends RecyclerView.ViewHolder {
        public TextView dateTextView;
        public TextView weekTextView;

        public ViewInXHolder(View itemView) {
            super(itemView);
            this.dateTextView = (TextView) itemView.findViewById(R.id.date);
        }

    }

    private static class ViewInYHolder extends RecyclerView.ViewHolder {
        public TextView roomTypeTextView;

        public ViewInYHolder(View view) {
            super(view);
            this.roomTypeTextView = (TextView) view.findViewById(R.id.room_type);
        }
    }

    private static class DataViewHolder extends RecyclerView.ViewHolder {
        public TextView statusTextView;
        public View view;

        public DataViewHolder(View view) {
            super(view);
            this.view = view;
            this.statusTextView = (TextView) view.findViewById(R.id.status);
        }
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public TitleViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.title);
        }
    }


    public void setYDataInfoList(List<String> roomInfoList) {
        this.roomInfoList = roomInfoList;
    }

    public void setXDateInfoList(List<String> dateInfoList) {
        this.dateInfoList = dateInfoList;
    }

    public void setDatasList(List<List<DataInfo>> ordersList) {
        this.ordersList = ordersList;
    }
}
