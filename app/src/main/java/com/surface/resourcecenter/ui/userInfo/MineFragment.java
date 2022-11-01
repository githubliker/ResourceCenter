package com.surface.resourcecenter.ui.userInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.Consts;
import com.surface.resourcecenter.data.network.ApiUrl;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.network.NetworkService;
import com.surface.resourcecenter.data.sp.SpManager;
import com.surface.resourcecenter.data.utils.FileCacheUtils;
import com.surface.resourcecenter.data.utils.ToastUtils;
import com.surface.resourcecenter.ui.BaseFragment;
import com.surface.resourcecenter.ui.aboutus.AboutActivity;
import com.surface.resourcecenter.ui.dispatch.bean.DispatchBean;
import com.surface.resourcecenter.ui.login.LoginActivity;
import com.surface.resourcecenter.ui.view.CustomSignPopup;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;


/**
 * Created by yangzhipeng on 2018/6/26.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private TextView logintxt, loginOut, cacheSize;
    private Context content;
    private ImageView headpic1;
    private RelativeLayout aboutLayout, cacheClear,fixSign;


    public static MineFragment newInstance() {

        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rc_mine_fragment_layout;
    }

    @Override
    public void init(View view) {
        content = getActivity();
        logintxt = (TextView) view.findViewById(R.id.logintxt);
        headpic1 = (ImageView) view.findViewById(R.id.headpic1);
        loginOut = (TextView) view.findViewById(R.id.loginOut);


        cacheClear = view.findViewById(R.id.cacheClear);
        cacheSize = view.findViewById(R.id.cacheSize);

        aboutLayout = view.findViewById(R.id.aboutLayout);
        fixSign = view.findViewById(R.id.fixsign);
        headpic1.setOnClickListener(this);
        logintxt.setOnClickListener(this);
        loginOut.setOnClickListener(this);
        aboutLayout.setOnClickListener(this);
        cacheClear.setOnClickListener(this);
        fixSign.setOnClickListener(this);
        view.findViewById(R.id.fixusrname).setOnClickListener(this);
        view.findViewById(R.id.fixpassword).setOnClickListener(this);

        try {
            String cacheSizeStr = FileCacheUtils.getTotalCacheSize(getActivity());
            cacheSize.setText(cacheSizeStr);
        } catch (Exception e) {
            e.printStackTrace();
            cacheSize.setText("");
        }
        logintxt.setText(SpManager.getInstance().get(Consts.USRNAME));
    }

    public void initData() {

    }
    CenterPopupView popupView;
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i == R.id.fixusrname){
            new XPopup.Builder(getContext())
                    .hasStatusBarShadow(false)
                    //.dismissOnBackPressed(false)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .autoOpenSoftInput(true)
//                        .autoFocusEditText(false) //是否让弹窗内的EditText自动获取焦点，默认是true
                    //.moveUpToKeyboard(false)   //是否移动到软键盘上面，默认为true
                    .asInputConfirm("提示", null, SpManager.getInstance().get(Consts.USRNAME), "请输入您的用户名",
                            new OnInputConfirmListener() {
                                @Override
                                public void onConfirm(String text) {
                                    if(TextUtils.isEmpty(text)){
                                        ToastUtils.ShowCenterToast(getContext(),"请输入您的用户名");
                                    } else {
                                        fixUserInfo(text,null);
                                    }
                                }
                            })
                    .show();
        } else if(i == R.id.fixpassword){
            new XPopup.Builder(getContext())
                    .hasStatusBarShadow(false)
                    //.dismissOnBackPressed(false)
                    .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                    .autoOpenSoftInput(true)
//                        .autoFocusEditText(false) //是否让弹窗内的EditText自动获取焦点，默认是true
                    //.moveUpToKeyboard(false)   //是否移动到软键盘上面，默认为true
                    .asInputConfirm("提示", null, null, "请输入您的密码",
                            new OnInputConfirmListener() {
                                @Override
                                public void onConfirm(String text) {
                                    if(TextUtils.isEmpty(text)){
                                        ToastUtils.ShowCenterToast(getContext(),"请输入您的密码");
                                    } else {
                                        fixUserInfo(null,text);
                                    }
                                }
                            })
                    .show();
        } else if (i == R.id.fixsign) {
            CustomSignPopup customPopup = new CustomSignPopup(getContext());
            new XPopup.Builder(getContext())
                    .autoOpenSoftInput(false)
                    .asCustom(customPopup)
                    .show();
        } else if (i == R.id.loginOut) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            getActivity().startActivity(intent);
            getActivity().finish();
        } else if (i == R.id.aboutLayout) {
            AboutActivity.launch(getActivity());
        } else if (i == R.id.cacheClear) {
               popupView = new XPopup.Builder(getContext())
                    .hasNavigationBar(false) //设置是否显示导航栏，默认是显示的。如果你希望弹窗隐藏导航栏，就设置为true
                    /*
                     * XPopup的弹窗默认是Dialog实现，该方法设置为true则切换为View实现，两者区别如下：
                     * 1. Dialog实现，独立Window渲染，性能是View实现的2倍以上，但部分与输入法交互效果无法做到，
                     *    比如根据输入进行联想搜索的场景，因为输入法也是一个Dialog，Android中无法实现2个Dialog同时获取焦点，
                     *    而设置为View模式即可轻松实现；
                     *    但是Dialog实现有个缺陷是弹窗内部无法使用Fragment，这是Android的限制；
                     *    Dialog的层级高，会覆盖View层
                     * 2. View实现本质是把弹窗挂载到Activity的decorView上面，由于还是View，所以很多与输入法的交互都能实现；
                     *    View实现内部完全可以使用Fragment；
                     *    缺点是和Activity相同渲染线程，性能比Dialog低
                     *
                     * @param viewMode 是否是View实现，默认是false
                     */
                    .isViewMode(true)
                    /*
                     * 是否在弹窗消失后就立即释放资源，杜绝内存泄漏，仅仅适用于弹窗只用一次的场景，默认为false。
                     * 如果你的弹窗对象需是复用的，千万不要开启这个设置
                     */
                    .isDestroyOnDismiss(true)
                    .hasBlurBg(true)//是否设置背景为高斯模糊背景。默认为false
                    .dismissOnTouchOutside(false)//设置点击弹窗外面是否关闭弹窗，默认为true
                    .autoDismiss(false)//设置当操作完毕后是否自动关闭弹窗，默认为true。比如：点击Confirm弹窗的确认按钮默认是关闭弹窗，如果为false，则不关闭
                    .popupAnimation(PopupAnimation.NoAnimation)// 为弹窗设置内置的动画器，默认情况下，已经为每种弹窗设置了效果最佳的动画器；如果你不喜欢，仍然可以修改。
                    .asConfirm("提示", "是否清除设备缓存?",
                            "取消", "确定",
                            new OnConfirmListener() {
                                @Override
                                public void onConfirm() {
                                    popupView.dismiss();
                                    FileCacheUtils.clearAllCache(getContext());
                                    Toast.makeText(getContext(),"清除成功",Toast.LENGTH_LONG).show();
                                    cacheSize.setText("");
                                }

                            }, null, true);
            popupView.show();

        }
    }

    private void fixUserInfo(String name,String password){
        HashMap params = new HashMap();
        params.put("id",SpManager.getInstance().get(Consts.USRID));
        if(!TextUtils.isEmpty(name)){
            params.put("realName",name);
        }
        if(!TextUtils.isEmpty(password)){
            params.put("password",password);
        }

        NetworkService service = new NetworkService();
        service.setRequestForJson(0, params.toString(), ApiUrl.URL_USRINFO_UPDATE, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());

            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

}
