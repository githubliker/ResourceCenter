package com.surface.resourcecenter.ui.sample;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.ScreenUtils;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.Consts;
import com.surface.resourcecenter.data.network.ApiUrl;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.network.NetworkService;
import com.surface.resourcecenter.data.utils.StatusBarUtil;
import com.surface.resourcecenter.data.utils.ToastUtils;
import com.surface.resourcecenter.ui.BaseActivity;
import com.surface.resourcecenter.ui.dispatch.adapter.StandardAdapter;
import com.surface.resourcecenter.ui.sample.adapter.FullyGridLayoutManager;
import com.surface.resourcecenter.ui.sample.adapter.GridImageAdapter;
import com.surface.resourcecenter.ui.sample.adapter.TestAdapter;
import com.surface.resourcecenter.ui.sample.bean.SampleImageBean;
import com.surface.resourcecenter.ui.sample.bean.SampleTypeBean;
import com.surface.resourcecenter.ui.sample.bean.TestItemsBean;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SampleRecvActivity extends BaseActivity implements View.OnClickListener {
    private final static String TAG = SampleRecvActivity.class.getSimpleName();
    private TextView versionName;
    private GridImageAdapter mImageAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayout mExtraParamsLayout;
    TestAdapter adapter;
    private Spinner mSampleType;
    private String[] mSampleTypeData;
    private TextView mSampleDate,mDataUpload;
    private EditText mSampleName,mSampleXinghao,mSampleId,mSampleImei,mSampleNumber,mSampleZhongliang,mSampleRemark;
    private EditText mSampleClient,mSampleEmail,mSampleSongjian,mSampleProjectName,mSampleAddress,mSamplePhone,mSampleJianzheng,mSampleLeixing;
    private Spinner mSampleYouxianji,mSampleStatus,mSampleChoujian;
    private int mUploadMaxNumber = 0,uploadIndex = 0;

    private List<TestItemsBean> mStandardList = new ArrayList<>();
    private ArrayList<SampleTypeBean> mTypeList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rc_activity_sample_layout);
        mSampleType = findViewById(R.id.sample_type);
        mExtraParamsLayout = findViewById(R.id.extra_params);
        mRecyclerView = findViewById(R.id.recyclerView);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new TestAdapter(this);
        adapter.initData(mStandardList);
        mRecyclerView.setAdapter(adapter);

        RecyclerView mRecyclerView = findViewById(R.id.recycler);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this,
                4, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(4,
                ScreenUtils.dip2px(this, 8), false));
        mImageAdapter = new GridImageAdapter(this, onAddPicClickListener);
        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList("selectorList") != null) {
            mImageAdapter.setList(savedInstanceState.getParcelableArrayList("selectorList"));
        }
        mImageAdapter.setSelectMax(8);
        mRecyclerView.setAdapter(mImageAdapter);
        mImageAdapter.setOnItemClickListener((v, position) -> {
            List<LocalMedia> selectList = mImageAdapter.getData();
            if (selectList.size() > 0) {
                LocalMedia media = selectList.get(position);
                String mimeType = media.getMimeType();
                int mediaType = PictureMimeType.getMimeType(mimeType);
                if(media.isChecked() || !media.isOriginal()){
                    mUploadMaxNumber = mImageAdapter.getData().size();
                    uploadImage(position,media);
                    return;
                }
                switch (mediaType) {
                    case PictureConfig.TYPE_VIDEO:
                        // 预览视频
                        PictureSelector.create(SampleRecvActivity.this)
                                .themeStyle(R.style.picture_default_style)
                                .externalPictureVideo(TextUtils.isEmpty(media.getAndroidQToPath()) ? media.getPath() : media.getAndroidQToPath());
                        break;
                    case PictureConfig.TYPE_AUDIO:
                        // 预览音频
                        PictureSelector.create(SampleRecvActivity.this)
                                .externalPictureAudio(PictureMimeType.isContent(media.getPath()) ? media.getAndroidQToPath() : media.getPath());
                        break;
                    default:
                        // 预览图片 可自定长按保存路径
//                        PictureWindowAnimationStyle animationStyle = new PictureWindowAnimationStyle();
//                        animationStyle.activityPreviewEnterAnimation = R.anim.picture_anim_up_in;
//                        animationStyle.activityPreviewExitAnimation = R.anim.picture_anim_down_out;
                        PictureSelector.create(SampleRecvActivity.this)
                                .themeStyle(R.style.picture_default_style) // xml设置主题
                                //.setPictureWindowAnimationStyle(animationStyle)// 自定义页面启动动画
                                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 设置相册Activity方向，不设置默认使用系统
                                .isNotPreviewDownload(false)// 预览图片长按是否可以下载
                                //.bindCustomPlayVideoCallback(new MyVideoSelectedPlayCallback(getContext()))// 自定义播放回调控制，用户可以使用自己的视频播放界面
                                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                                .openExternalPreview(position, selectList);
                        break;
                }
            }
        });
        initTitle();
        initView();
        getSampleLeixing();
    }

    private void initView(){
        mDataUpload = findViewById(R.id.tv_confirm);
        mSampleName = findViewById(R.id.device_name);
        mSampleXinghao = findViewById(R.id.sample_xinghao);
        mSampleId = findViewById(R.id.sample_id);
        mSampleImei = findViewById(R.id.sample_imei);
        mSampleChoujian = findViewById(R.id.sample_choujian_type);
        mSampleNumber = findViewById(R.id.sample_number);
        mSampleZhongliang = findViewById(R.id.device_zhongliang);
        mSampleRemark = findViewById(R.id.device_remark);
        mSampleYouxianji = findViewById(R.id.youxianji);
        mSampleStatus = findViewById(R.id.sample_status);
        mSampleDate = findViewById(R.id.sample_date);
        mSampleClient = findViewById(R.id.sample_client);
        mSampleEmail = findViewById(R.id.sample_email);
        mSampleSongjian = findViewById(R.id.sample_songjian);
        mSampleProjectName = findViewById(R.id.sample_project_name);
        mSampleAddress = findViewById(R.id.sample_address);
        mSamplePhone = findViewById(R.id.sample_phone);
        mSampleJianzheng = findViewById(R.id.sample_jianzheng);
        mSampleLeixing = findViewById(R.id.sample_leixing);
        mSampleDate.setOnClickListener(this);
        mDataUpload.setOnClickListener(this);
    }
    private void initTitle() {
        StatusBarUtil.setPaddingSmart(this,findViewById(R.id.toolbar));
        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("收样信息");

    }

    private void initTestItemsList(String testName){
        if("".equals(testName)) return;
        String[] items ;
        if(testName.equals(Consts.GAOYAKGG)){
            items = getResources().getStringArray(R.array.kgg_gaoya_test_items);
        } else if(testName.equals(Consts.DIYAKGG)){
            items = getResources().getStringArray(R.array.kgg_diya_test_items);
        }else if(testName.equals(Consts.JPKGG)){
            items = getResources().getStringArray(R.array.kgg_jp_test_items);
        }else if(testName.equals(Consts.GELIKGG)){
            items = getResources().getStringArray(R.array.kgg_geli_test_items);
        }else if(testName.equals(Consts.ZHUSHANGKGG)){
            items = getResources().getStringArray(R.array.kgg_zhushang_test_items);
        }else if(testName.equals(Consts.DIANLIDIANLAN)){
            items = getResources().getStringArray(R.array.dianlidianlan_test_items);
        }else if(testName.equals(Consts.DIANLANBAOHUGUAN)){
            items = getResources().getStringArray(R.array.dianlan_baohuguan_test_items);
        }else if(testName.equals(Consts.DIANLANFENZHIXIANG)){
            items = getResources().getStringArray(R.array.dianlanfenzhixiang_test_items);
        }else if(testName.equals(Consts.DIANNENGJILIANGXIANG)){
            items = getResources().getStringArray(R.array.diannengjiliangxiang_test_items);
        }else if(testName.equals(Consts.GANSHIBYQ)||testName.equals(Consts.YOUJINBYQ)){
            items = getResources().getStringArray(R.array.byq_test_items);
        }else if(testName.equals(Consts.OUSHIBYQ)){
            items = getResources().getStringArray(R.array.os_byq_test_items);
        }else if(testName.equals(Consts.MEISHIBYQ)){
            items = getResources().getStringArray(R.array.ms_byq_test_items);
        } else {
            items = getResources().getStringArray(R.array.kgg_gaoya_test_items);
        }

    }

    private void initExtraParamsLayout(String testName){
        String[] items ;
        if(testName.equals(Consts.GAOYAKGG)){
            items = getResources().getStringArray(R.array.kgg_main_params);
        } else if(testName.equals(Consts.DIYAKGG)){
            items = getResources().getStringArray(R.array.kgg_main_params);
        }else if(testName.equals(Consts.JPKGG)){
            items = getResources().getStringArray(R.array.jp_main_params);
        }else if(testName.equals(Consts.GELIKGG)){
            items = getResources().getStringArray(R.array.kgg_main_params_1);
        }else if(testName.equals(Consts.ZHUSHANGKGG)){
            items = getResources().getStringArray(R.array.kgg_main_params);
        }else if(testName.equals(Consts.DIANLIDIANLAN)){
            items = new String[]{};
        }else if(testName.equals(Consts.DIANLANBAOHUGUAN)){
            items = getResources().getStringArray(R.array.dl_bhg_main_params);
        }else if(testName.equals(Consts.DIANLANFENZHIXIANG)){
            items = getResources().getStringArray(R.array.dl_fzhx_main_params);
        }else if(testName.equals(Consts.DIANNENGJILIANGXIANG)){
            items = getResources().getStringArray(R.array.dn_jlx_main_params);
        }else if(testName.equals(Consts.GANSHIBYQ)||testName.equals(Consts.YOUJINBYQ)){
            items = getResources().getStringArray(R.array.byq_main_params);
        }else if(testName.equals(Consts.OUSHIBYQ)){
            items = getResources().getStringArray(R.array.byq_main_params_1);
        }else if(testName.equals(Consts.MEISHIBYQ)){
            items = getResources().getStringArray(R.array.byq_main_params_1);
        } else {
            items = getResources().getStringArray(R.array.kgg_main_params);
        }

        mExtraParamsLayout.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5,5,5,5);

        LinearLayout leftlayout = new LinearLayout(this);
        leftlayout.setOrientation(LinearLayout.VERTICAL);
        leftlayout.setLayoutParams(params);
        leftlayout.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout rightlayout = new LinearLayout(this);
        rightlayout.setOrientation(LinearLayout.VERTICAL);
        rightlayout.setLayoutParams(params);
        rightlayout.setGravity(Gravity.CENTER_VERTICAL);
        for(int i = 0;i<items.length;i++){
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.HORIZONTAL);

            layout.setLayoutParams(params);
            TextView text = new TextView(this);
            text.setText(items[i]+"：");
            text.setTextSize(16);
            EditText editText = new EditText(this);
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            editText.setTextSize(16);
            params.weight = 1;
            params.setMargins(5,0,5,0);
            editText.setLayoutParams(params);

            LinearLayout layout1 = new LinearLayout(this);
            layout1.setOrientation(LinearLayout.HORIZONTAL);

            layout1.setLayoutParams(params);
            TextView text1 = new TextView(this);
            text1.setText(items[++i]+"：");
            text1.setTextSize(16);
            EditText editText1 = new EditText(this);
            editText1.setInputType(InputType.TYPE_CLASS_TEXT);
            editText1.setTextSize(16);
            params.weight = 1;
            params.setMargins(5,0,5,0);
            editText1.setLayoutParams(params);
            layout.addView(text);
            layout.addView(editText);
            leftlayout.addView(layout);

            layout1.addView(text1);
            layout1.addView(editText1);
            rightlayout.addView(layout1);
        }
        mExtraParamsLayout.addView(leftlayout);
        mExtraParamsLayout.addView(rightlayout);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sample_date:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        SampleRecvActivity.this, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year,
                                          int monthOfYear, int dayOfMonth) {
                        mSampleDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " 12:00:00");
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

                break;
            case R.id.tv_confirm:
                checkAllDataReady();
                mUploadMaxNumber = mImageAdapter.getData().size();
                uploadIndex = 0;
                for(int i = 0; i< mUploadMaxNumber; i++){
                    uploadImage(i,mImageAdapter.getData().get(i));
                }
                break;
            default:
                break;
        }
    }

    private void uploadImage(int index,LocalMedia media){
        if(media.isOriginal()&& !media.isChecked()){
            uploadIndex ++;
            if(uploadIndex == mUploadMaxNumber){
                Log.d(TAG,"所有任务都完成");
                uploadSampleData();
            }
            return;
        }
        File file = new File(media.getRealPath());
        Log.d(TAG,"upload path "+media.getRealPath());
        NetworkService service = new NetworkService();
        service.upload(index,ApiUrl.URL_UPLOAD, file, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+what+" / "+response.get().toString());
                Gson gson = new Gson();
                Type userListType = new TypeToken<SampleImageBean>(){}.getType();
                SampleImageBean bean = gson.fromJson(response.get(), userListType);
                mImageAdapter.setImageStatus(what,bean.getData());
                uploadIndex++;
                if(uploadIndex == mUploadMaxNumber){
                    Log.d(TAG,"所有任务都完成");
                    uploadSampleData();
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                Log.e(TAG,"上传失败");
                mImageAdapter.setImageStatusError(what);
            }
        });
    }
    private void uploadSampleData(){
        String imgs = "";
        for(int i = 0;i<mImageAdapter.getData().size();i++){
            imgs += mImageAdapter.getData().get(i).getOriginalPath()+",";
        }
        JSONObject json = new JSONObject();
        try {
            json.put("additional", "");//额外参数
            json.put("addr",mSampleAddress.getText().toString());//委托方地址
            json.put("checkType", mSampleChoujian.getSelectedItemPosition());//抽检类型
            json.put("code",mSampleId.getText().toString());//样品编号
            json.put("count", mSampleNumber.getText().toString());//样品数量
            json.put("createTime",mSampleDate.getText().toString());//收样时间
            json.put("detectType", mSampleLeixing.getText().toString());//检测类别
            json.put("entrustName",mSampleClient.getText().toString());//委托单位
            json.put("experiments", getCheckedTestItems());//样品预检项
            json.put("id","");//
            json.put("img", imgs);//样品图片
            json.put("name",mSampleName.getText().toString());//样品名称
            json.put("phone", mSamplePhone.getText().toString());//委托方联系方式
            json.put("postalCode","");//委托方邮编
            json.put("priority", mSampleYouxianji.getSelectedItemPosition());//优先级1-正常   2-加急
            json.put("projectName",mSampleProjectName.getText().toString());//工程名称
            json.put("qrCode", "");//二维码编号
            json.put("remark",mSampleRemark.getText().toString());//备注
            json.put("sampleId", mSampleImei.getText().toString());//实物ID
            json.put("sampleType",mTypeList.get(mSampleType.getSelectedItemPosition()).getId());//
            json.put("spqmc", "");//试品区名称
            json.put("state",mSampleStatus.getSelectedItemPosition());//样品状态1-完好  2-缺失  3-破损
            json.put("submitter", mSampleSongjian.getText().toString());//送检人
            json.put("version",mSampleXinghao.getText().toString());//样品型号
            json.put("witness", mSampleJianzheng.getText().toString());//见证人
        }catch (JSONException e){}
        NetworkService service = new NetworkService();
        service.setRequestForJson(0, json.toString(), ApiUrl.URL_SAMPLE_UPLOAD, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    JSONObject json = null;
                    json = new JSONObject(response.get());
                    String msg = json.getString("msg");
                    ToastUtils.ShowCenterToast(SampleRecvActivity.this,msg);
                }catch (Exception e){}
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    private void checkAllDataReady(){
        if(TextUtils.isEmpty(mSampleName.getText().toString())){
            ToastUtils.ShowCenterToast(SampleRecvActivity.this,"请输入设备名称");
            return;
        }
        if(TextUtils.isEmpty(mSampleXinghao.getText().toString())){
            ToastUtils.ShowCenterToast(SampleRecvActivity.this,"请输入设备型号");
            return;
        }
        if(TextUtils.isEmpty(mSampleId.getText().toString())){
            ToastUtils.ShowCenterToast(SampleRecvActivity.this,"请输入样品编号");
            return;
        }
        if(TextUtils.isEmpty(mSamplePhone.getText().toString())){
            ToastUtils.ShowCenterToast(SampleRecvActivity.this,"请输入委托方联系方式");
            return;
        }
        if(TextUtils.isEmpty(mSampleDate.getText().toString())){
            ToastUtils.ShowCenterToast(SampleRecvActivity.this,"请输入到样时间");
            return;
        }
        if(TextUtils.isEmpty(mSampleClient.getText().toString())){
            ToastUtils.ShowCenterToast(SampleRecvActivity.this,"请输入委托单位名称");
            return;
        }
        if( mImageAdapter.getData().size() <= 0){
            ToastUtils.ShowCenterToast(SampleRecvActivity.this,"请上传相关试品图片");
            return;
        }
    }
    private String getCheckedTestItems(){
        String result = "";
        for(int i = 0;i< mStandardList.size();i++){
            if(mStandardList.get(i).isChecked()){
                result += mStandardList.get(i).getId()+",";
            }
        }
        return result;
    }

    private void getSampleLeixing(){
        HashMap params = new HashMap();
        NetworkService service = new NetworkService();
        service.setGetRequestForData(0, params, ApiUrl.URL_SAMPLE_TYPE, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    JSONObject json = new JSONObject(response.get());
                    String datas = json.getString("data");
                    Gson gson = new Gson();
                    Type userListType = new TypeToken<List<SampleTypeBean>>(){}.getType();
                    mTypeList.clear();
                    mTypeList = gson.fromJson(datas, userListType);
                    mSampleTypeData = new String[mTypeList.size()];
                    for(int i= 0;i<mTypeList.size();i++){
                        mSampleTypeData[i] = mTypeList.get(i).getTypeName();
                    }
                    ArrayAdapter adapter = new ArrayAdapter<String>(SampleRecvActivity.this, android.R.layout.simple_list_item_1, mSampleTypeData);
                    mSampleType.setAdapter(adapter);
                    mSampleType.setOnItemSelectedListener(spinnerItemSelectListener);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }

    private void getTestItems(String sampleTypeId){
        HashMap params = new HashMap();
        params.put("type",sampleTypeId);
        NetworkService service = new NetworkService();
        service.setGetRequestForData(0, params, ApiUrl.URL_TEST_ITEMS, CacheMode.ONLY_REQUEST_NETWORK, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.e("TAG",""+response.get().toString());
                try {
                    JSONObject json = new JSONObject(response.get());
                    String datas = json.getString("data");
                    Gson gson = new Gson();
                    Type userListType = new TypeToken<List<TestItemsBean>>(){}.getType();
                    mStandardList.clear();
                    mStandardList = gson.fromJson(datas, userListType);
                    adapter.initData(mStandardList);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }
    private AdapterView.OnItemSelectedListener spinnerItemSelectListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String sampleType = mSampleTypeData[position];
            initExtraParamsLayout(sampleType);
            getTestItems(mTypeList.get(position).getId());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            PictureSelector.create(SampleRecvActivity.this)
                    .openGallery(PictureMimeType.ofImage())
                    .maxSelectNum(8)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                    .selectionData(mImageAdapter.getData())
                    .forResult(new MyResultCallback(mImageAdapter));
        }
    };


    /**
     * 返回结果回调
     */
    private static class MyResultCallback implements OnResultCallbackListener<LocalMedia> {
        private WeakReference<GridImageAdapter> mAdapterWeakReference;

        public MyResultCallback(GridImageAdapter adapter) {
            super();
            this.mAdapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        public void onResult(List<LocalMedia> result) {
            for (LocalMedia media : result) {
                Log.i(TAG, "是否压缩:" + media.isCompressed());
                Log.i(TAG, "压缩:" + media.getCompressPath());
                Log.i(TAG, "原图:" + media.getPath());
                Log.i(TAG, "是否裁剪:" + media.isCut());
                Log.i(TAG, "裁剪:" + media.getCutPath());
                Log.i(TAG, "是否开启原图:" + media.isOriginal());
                Log.i(TAG, "原图路径:" + media.getOriginalPath());
                Log.i(TAG, "Android Q 特有Path:" + media.getAndroidQToPath());
                Log.i(TAG, "宽高: " + media.getWidth() + "x" + media.getHeight());
                Log.i(TAG, "Size: " + media.getSize());
                // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();方法获取一些额外的资源信息，如旋转角度、经纬度等信息
            }
            if (mAdapterWeakReference.get() != null) {
                mAdapterWeakReference.get().setList(result);
                mAdapterWeakReference.get().notifyDataSetChanged();
            }
        }

        @Override
        public void onCancel() {
            Log.i(TAG, "PictureSelector Cancel");
        }
    }
    public static void launch(Context context) {
        Intent intent = new Intent(context, SampleRecvActivity.class);
        context.startActivity(intent);
    }
}
