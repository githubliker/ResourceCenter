package com.surface.resourcecenter.ui.usrManager;

import android.graphics.Bitmap;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.widget.Toolbar;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.utils.StatusBarUtil;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.usrManager.bean.ChannelItem;

import java.util.ArrayList;

public class UsrManagerFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private String TAG = "ChannelManagerFragment";
    private TextView mSwitch;
    private LinearLayout mMoreLayout;
    /**
     * 用户栏目的GRIDVIEW
     */
    private DragGrid userGridView;
    /**
     * 其它栏目的GRIDVIEW
     */
    private OtherGridView quyuGridView;
    /**
     * 用户栏目对应的适配器，可以拖动
     */
    DragAdapter groupAdapter;
    /**
     * 其它栏目对应的适配器
     */
    OtherAdapter quyuAdapter;
    /**
     * 其它栏目列表
     */
    ArrayList<ChannelItem> companyList = new ArrayList<ChannelItem>();
    /**
     * 用户栏目列表
     */
    ArrayList<ChannelItem> grouplist = new ArrayList<ChannelItem>();
    /**
     * 是否在移动，由于这边是动画结束后才进行的数据更替，设置这个限制为了避免操作太频繁造成的数据错乱。
     */
    boolean isMove = false;
    private boolean isDeleteMode = false;

    public static UsrManagerFragment newInstance(String param) {

        Bundle args = new Bundle();
        UsrManagerFragment fragment = new UsrManagerFragment();
        args.putString("channel", param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rc_fragment_manager_usr_control;
    }

    @Override
    public void init(View view) {
        userGridView = (DragGrid) view.findViewById(R.id.userGridView);
        quyuGridView = (OtherGridView) view.findViewById(R.id.quyuGridView);
        mSwitch = (TextView) view.findViewById(R.id.switch_mode);
        mMoreLayout = (LinearLayout) view.findViewById(R.id.more_layout);
        userGridView.setOnItemClickListener(this);
        quyuGridView.setOnItemClickListener(this);
        mSwitch.setOnClickListener(this);

        initTitle(view);
        initGroupUsrList();
        initCompanyUsrList();
    }

    private void initTitle(View view) {
        StatusBarUtil.setPaddingSmart(getContext(),view.findViewById(R.id.toolbar));
        final Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        toolbar.setTitle("用户管理");

    }
    void initGroupUsrList(){
        grouplist.clear();
        for(int i =0;i<20;i++){
            ChannelItem item = new ChannelItem();
            item.setName("test"+i);
            grouplist.add(item);
        }
        groupAdapter = new DragAdapter(getContext(), grouplist);
        userGridView.setAdapter(groupAdapter);
    }

    private void initCompanyUsrList() {
        companyList.clear();
        for(int i =0;i<20;i++){
            ChannelItem item = new ChannelItem();
            item.setName("item"+i);
            companyList.add(item);
        }

        quyuAdapter = new OtherAdapter(getContext(), companyList);
        quyuGridView.setAdapter(quyuAdapter);
    }

    /**
     * 点击ITEM移动动画
     *
     * @param moveView
     * @param startLocation
     * @param endLocation
     * @param moveChannel
     * @param clickGridView
     */
    private void MoveAnim(View moveView, int[] startLocation, int[] endLocation, final ChannelItem moveChannel,
                          final GridView clickGridView, final String source) {
        int[] initLocation = new int[2];
        //获取传递过来的VIEW的坐标
        moveView.getLocationInWindow(initLocation);
        //得到要移动的VIEW,并放入对应的容器中
        final ViewGroup moveViewGroup = getMoveViewGroup();
        final View mMoveView = getMoveView(moveViewGroup, moveView, initLocation);
        //创建移动动画
        TranslateAnimation moveAnimation = new TranslateAnimation(
                startLocation[0], endLocation[0], startLocation[1],
                endLocation[1]);
        if (source.equals("cibiao")) {
            moveAnimation.setDuration(500L);//动画时间
        } else {
            moveAnimation.setDuration(300L);//动画时间
        }
        //动画配置
        AnimationSet moveAnimationSet = new AnimationSet(true);
        moveAnimationSet.setFillAfter(false);//动画效果执行完毕后，View对象不保留在终止的位置
        moveAnimationSet.addAnimation(moveAnimation);
        mMoveView.startAnimation(moveAnimationSet);
        moveAnimationSet.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                isMove = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                moveViewGroup.removeView(mMoveView);
                // instanceof 方法判断2边实例是不是一样，判断点击的是DragGrid还是OtherGridView
                if (clickGridView instanceof DragGrid) {
                    if (source.equals("company")) {
                        quyuAdapter.setVisible(true);
                        quyuAdapter.notifyDataSetChanged();
                    }
                    groupAdapter.remove();
                } else {
                    groupAdapter.setVisible(true);
                    groupAdapter.notifyDataSetChanged();
                    if (source.equals("company")) {
                        quyuAdapter.remove();
                    }
                }
                isMove = false;
            }
        });
    }

    /**
     * 获取移动的VIEW，放入对应ViewGroup布局容器
     *
     * @param viewGroup
     * @param view
     * @param initLocation
     * @return
     */
    private View getMoveView(ViewGroup viewGroup, View view, int[] initLocation) {
        int x = initLocation[0];
        int y = initLocation[1];
        viewGroup.addView(view);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams.leftMargin = x;
        mLayoutParams.topMargin = y;
        view.setLayoutParams(mLayoutParams);
        return view;
    }

    /**
     * 创建移动的ITEM对应的ViewGroup布局容器
     */
    private ViewGroup getMoveViewGroup() {
        ViewGroup moveViewGroup = (ViewGroup) getActivity().getWindow().getDecorView();
        LinearLayout moveLinearLayout = new LinearLayout(getContext());
        moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        moveViewGroup.addView(moveLinearLayout);
        return moveLinearLayout;
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
        int id = v.getId();
        if (id == R.id.switch_mode) {
            updateDragList();

        }
    }

    /**
     * 更新已订阅
     */
    private void updateDragList() {
        String mode;
        if (!isDeleteMode) {
            mode = "delete";
            isDeleteMode = true;
        } else {
            mode = "normal";
            isDeleteMode = false;
        }
        for (int i = 0; i < grouplist.size(); i++) {
            ChannelItem item = grouplist.get(i);
            item.setMode(mode);
        }
        if (isDeleteMode) {
            mSwitch.setText("完成");
            mMoreLayout.setVisibility(View.GONE);
        } else {
            mSwitch.setText("排序删除");
            mMoreLayout.setVisibility(View.VISIBLE);
        }
        groupAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        if (isMove) {
            return;
        }
        int i = parent.getId();
        if (i == R.id.userGridView) {
            if ("完成".equals(mSwitch.getText().toString())) {
                final ImageView moveImageView = getView(view);
                if (moveImageView != null) {
                    final ChannelItem channel = ((DragAdapter) parent.getAdapter()).getItem(position);//获取点击的频道内容
                    quyuAdapter.addItem(channel);
                    groupAdapter.setRemove(position);
                    groupAdapter.remove();
                }
            } else {
                String[] ro = new String[]{"收样员", "调度员", "试验员", "编制", "审核", "终审", "未分配"};
                new XPopup.Builder(getContext())
                        .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                        .asCenterList("请分配工作权限", ro,
                                null, 1,
                                new OnSelectListener() {
                                    @Override
                                    public void onSelect(int p, String text) {
                                        Log.d(TAG,"选中position" +p);
                                        grouplist.get(position).setRole(ro[p]);
                                        groupAdapter.notifyDataSetChanged();
                                    }
                                })
                        .show();
            }


        } else if (i == R.id.quyuGridView) {

        }
    }





}
