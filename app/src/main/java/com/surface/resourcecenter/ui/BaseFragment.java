package com.surface.resourcecenter.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lxj.statelayout.StateLayout;
import com.surface.resourcecenter.ui.sample.bean.TestItemsBean;
import com.surface.resourcecenter.ui.shiyan.DoTaskActivity;


/**
 * Description:
 * Create by dance, at 2018/12/9
 */
public abstract class BaseFragment extends Fragment {
    View view;
    boolean isInit = false;
    StateLayout stateLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(getLayoutId(), container, false);
            stateLayout = new StateLayout(getContext()).wrap(view).showLoading(false);
        }
        return stateLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        safeInit();
    }

    private void safeInit() {
        if (getUserVisibleHint() && view!=null) {
            if (!isInit) {
                isInit = true;
                init(view);
                stateLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stateLayout.showContent();
                    }
                },100);
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        safeInit();
    }

    protected abstract int getLayoutId();
    public abstract void init(View view);

    protected TestItemsBean getTestItems(String id){
        DoTaskActivity activity = (DoTaskActivity) getActivity();
        for(int i =0;i< activity.mStandardList.size();i++){
            if(activity.mStandardList.get(i).getId().equals(id)){
                return activity.mStandardList.get(i);
            }
        }
        return new TestItemsBean();
    }
    protected void saveShiyanData(String params){

    }

}
