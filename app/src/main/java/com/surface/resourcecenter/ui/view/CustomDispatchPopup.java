package com.surface.resourcecenter.ui.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lxj.xpopup.core.CenterPopupView;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.listener.onRecycleViewItemClickListener;
import com.surface.resourcecenter.ui.dispatch.AreaAdapter;
import com.surface.resourcecenter.ui.dispatch.adapter.InstrumentAdapter;
import com.surface.resourcecenter.ui.dispatch.adapter.PersonAdapter;
import com.surface.resourcecenter.ui.dispatch.bean.InstrumentBean;
import com.surface.resourcecenter.ui.dispatch.bean.SystemRole;
import com.surface.resourcecenter.ui.dispatch.bean.TestArea;

import java.util.ArrayList;
import java.util.List;

public class CustomDispatchPopup extends CenterPopupView {
    public CustomDispatchPopup(@NonNull Context context) {
        super(context);
    }

    private TextView mHeader;
    private RecyclerView mAreaRecycle,mPersonRecycle,mInstrumentRecycle;
    private List<TestArea> mAreaList = new ArrayList<>();
    private List<SystemRole> mRoleList = new ArrayList<>();
    private List<InstrumentBean> mInstrumentList = new ArrayList<>();
    PersonAdapter personAdapter;
    AreaAdapter areaAdapter;
    InstrumentAdapter instrumentAdapter;
    private int index;
    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_custom_dispatch;
    }
    @Override
    protected void onCreate() {
        super.onCreate();
        mHeader = (TextView) findViewById(R.id.dispatch_title);
        mAreaRecycle = (RecyclerView) findViewById(R.id.gongqu_recycle);
        mPersonRecycle = (RecyclerView) findViewById(R.id.person_recycle);
        mInstrumentRecycle = (RecyclerView) findViewById(R.id.instrument_recycle);

        GridLayoutManager layoutManager1 = new GridLayoutManager(getContext(),2);
        mAreaRecycle.setLayoutManager(layoutManager1);

        GridLayoutManager layoutManager2 = new GridLayoutManager(getContext(),4);
        mPersonRecycle.setLayoutManager(layoutManager2);

        GridLayoutManager layoutManager3 = new GridLayoutManager(getContext(),1);
        mInstrumentRecycle.setLayoutManager(layoutManager3);

        personAdapter = new PersonAdapter(mRoleList);
        mPersonRecycle.setAdapter(personAdapter);

        areaAdapter = new AreaAdapter(mAreaList);
        mAreaRecycle.setAdapter(areaAdapter);

        instrumentAdapter = new InstrumentAdapter(mInstrumentList);
        mInstrumentRecycle.setAdapter(instrumentAdapter);

        areaAdapter.setOnClickListener(mItemClickListener1);
        personAdapter.setOnClickListener(mItemClickListener2);
        instrumentAdapter.setOnClickListener(mItemClickListener3);
    }

    onRecycleViewItemClickListener mItemClickListener1 = new onRecycleViewItemClickListener() {
        @Override
        public void onClick(View v,int position) {
            boolean status = mAreaList.get(position).isSelect();
            mAreaList.get(position).setSelect(!status);
            areaAdapter.notifyDataSetChanged();
        }
    };
    onRecycleViewItemClickListener mItemClickListener2 = new onRecycleViewItemClickListener() {
        @Override
        public void onClick(View v,int position) {
            boolean status = mRoleList.get(position).isSelect();
            mRoleList.get(position).setSelect(!status);
            personAdapter.notifyDataSetChanged();
        }
    };
    onRecycleViewItemClickListener mItemClickListener3 = new onRecycleViewItemClickListener() {
        @Override
        public void onClick(View v,int position) {
            boolean status = mInstrumentList.get(position).isSelect();
            mInstrumentList.get(position).setSelect(!status);
            instrumentAdapter.notifyDataSetChanged();
        }
    };
    public void setData(String title,int index,List<TestArea> mAreaList, List<SystemRole> mRoleList,List<InstrumentBean> mInstrumentList){
        this.index = index;
        this.mAreaList.clear();
        this.mRoleList.clear();
        this.mInstrumentList.clear();
        this.mAreaList.addAll(mAreaList);
        this.mRoleList.addAll(mRoleList);
        this.mInstrumentList.addAll(mInstrumentList);

        personAdapter.notifyDataSetChanged();
        areaAdapter.notifyDataSetChanged();
        instrumentAdapter.notifyDataSetChanged();
        if(mHeader != null){
            mHeader.setText(title);
        }
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        if(listener != null){
            listener.onDismiss(index);
        }
    }

    PopupDismisListener listener;
    public void setOnDismisListener(PopupDismisListener lis){
        listener = lis;
    }
    protected void onShow() {
        super.onShow();
    }
    public interface PopupDismisListener {
        void onDismiss(int index);
    }
}
