package com.surface.resourcecenter.ui.device.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.listener.onRecycleViewItemClickListener;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.device.adapter.GongQuAdapter;
import com.surface.resourcecenter.ui.device.adapter.GongQuPersonAdapter;
import com.surface.resourcecenter.ui.device.adapter.bean.person;

import java.util.ArrayList;
import java.util.List;

public class GongQuPersonFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private String TAG = "GongQuPersonFragment";
    private RecyclerView mRecyclerView;
    GongQuPersonAdapter adapter;
    private List<person> itemList = new ArrayList<>();

    public static GongQuPersonFragment newInstance() {

        Bundle args = new Bundle();
        GongQuPersonFragment fragment = new GongQuPersonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rc_fragment_gongqu_usr_control;
    }

    @Override
    public void init(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        initGroupUsrList();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),6);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new GongQuPersonAdapter(itemList);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnClickListener(mItemClickListener);
    }

    onRecycleViewItemClickListener mItemClickListener = new onRecycleViewItemClickListener() {
        @Override
        public void onClick(View v,int position) {
            boolean status = itemList.get(position).isSelected();
            itemList.get(position).setSelected(!status);
            adapter.notifyDataSetChanged();
        }
    };
    void initGroupUsrList() {
        String[] gongQu = getResources().getStringArray(R.array.gongqu_person);
        for(int i = 0;i< gongQu.length;i++){
            person p = new person();
            p.setName(gongQu[i]);
            if(i%3 ==0){
                p.setSelected(true);
            } else
                p.setSelected(false);
            itemList.add(p);
        }
        person p1 = new person();
        p1.setName("<添加>");
        p1.setSelected(false);
        itemList.add(p1);
    }

    /**
     * 获取点击的Item的对应View，
     *
     * @param view
     * @return
     */
    private ImageView getView(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView iv = new ImageView(getContext());
        iv.setImageBitmap(cache);
        return iv;
    }

    @Override
    public void onClick(View v) {
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        int i = parent.getId();
        if (i == R.id.userGridView) {

            String[] ro = new String[]{"收样员", "调度员", "试验员", "编制", "审核", "终审", "未分配"};
            new XPopup.Builder(getContext())
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .asCenterList("请分配工作权限", ro,
                            null, 1,
                            new OnSelectListener() {
                                @Override
                                public void onSelect(int p, String text) {
                                    Log.d(TAG, "选中position" + p);

                                }
                            })
                    .show();



        }
    }


}
