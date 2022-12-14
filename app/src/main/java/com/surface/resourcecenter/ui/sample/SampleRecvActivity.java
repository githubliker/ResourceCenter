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
                        // ????????????
                        PictureSelector.create(SampleRecvActivity.this)
                                .themeStyle(R.style.picture_default_style)
                                .externalPictureVideo(TextUtils.isEmpty(media.getAndroidQToPath()) ? media.getPath() : media.getAndroidQToPath());
                        break;
                    case PictureConfig.TYPE_AUDIO:
                        // ????????????
                        PictureSelector.create(SampleRecvActivity.this)
                                .externalPictureAudio(PictureMimeType.isContent(media.getPath()) ? media.getAndroidQToPath() : media.getPath());
                        break;
                    default:
                        // ???????????? ???????????????????????????
//                        PictureWindowAnimationStyle animationStyle = new PictureWindowAnimationStyle();
//                        animationStyle.activityPreviewEnterAnimation = R.anim.picture_anim_up_in;
//                        animationStyle.activityPreviewExitAnimation = R.anim.picture_anim_down_out;
                        PictureSelector.create(SampleRecvActivity.this)
                                .themeStyle(R.style.picture_default_style) // xml????????????
                                //.setPictureWindowAnimationStyle(animationStyle)// ???????????????????????????
                                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// ????????????Activity????????????????????????????????????
                                .isNotPreviewDownload(false)// ????????????????????????????????????
                                //.bindCustomPlayVideoCallback(new MyVideoSelectedPlayCallback(getContext()))// ???????????????????????????????????????????????????????????????????????????
                                .imageEngine(GlideEngine.createGlideEngine())// ??????????????????????????????????????????
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
        toolbar.setTitle("????????????");

    }

    private void initExtraParamsLayout(String testName){
        String[] items ;
        if(testName.equals(Consts.GAOYAKGG)){
            items = getResources().getStringArray(R.array.kgg_main_params);
        } else if(testName.equals(Consts.DIYAKGG)){
            items = getResources().getStringArray(R.array.kgg_main_params);
        }else if(testName.equals(Consts.HWKGG)){
            items = getResources().getStringArray(R.array.jp_main_params);
        }else if(testName.equals(Consts.GELIKGG)){
            items = getResources().getStringArray(R.array.kgg_main_params_1);
        }else if(testName.equals(Consts.ZHUSHANGKGG)){
            items = getResources().getStringArray(R.array.kgg_main_params);
        }else if(testName.equals(Consts.DIANLIDIANLAN)){
            items = new String[]{};
        }else if(testName.equals(Consts.DIANLANBAOHUGUAN)){
            items = getResources().getStringArray(R.array.dl_bhg_main_params);
        }else if(testName.equals(Consts.GAOYADIANLANFENZHIXIANG)){
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
        for(int i = 0;i<items.length-1;i++){
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.HORIZONTAL);

            layout.setLayoutParams(params);
            TextView text = new TextView(this);
            text.setText(items[i]+"???");
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
            text1.setText(items[++i]+"???");
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
                Log.d(TAG,"?????????????????????");
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
                    Log.d(TAG,"?????????????????????");
                    uploadSampleData();
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                Log.e(TAG,"????????????");
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
            json.put("additional", "");//????????????
            json.put("addr",mSampleAddress.getText().toString());//???????????????
            json.put("checkType", mSampleChoujian.getSelectedItemPosition());//????????????
            json.put("code",mSampleId.getText().toString());//????????????
            json.put("count", mSampleNumber.getText().toString());//????????????
            json.put("createTime",mSampleDate.getText().toString());//????????????
            json.put("detectType", mSampleLeixing.getText().toString());//????????????
            json.put("entrustName",mSampleClient.getText().toString());//????????????
            json.put("experiments", getCheckedTestItems());//???????????????
            json.put("id","");//
            json.put("img", imgs);//????????????
            json.put("name",mSampleName.getText().toString());//????????????
            json.put("phone", mSamplePhone.getText().toString());//?????????????????????
            json.put("postalCode","");//???????????????
            json.put("priority", mSampleYouxianji.getSelectedItemPosition());//?????????1-??????   2-??????
            json.put("projectName",mSampleProjectName.getText().toString());//????????????
            json.put("qrCode", "");//???????????????
            json.put("remark",mSampleRemark.getText().toString());//??????
            json.put("sampleId", mSampleImei.getText().toString());//??????ID
            json.put("sampleType",mTypeList.get(mSampleType.getSelectedItemPosition()).getId());//
            json.put("spqmc", "");//???????????????
            json.put("state",mSampleStatus.getSelectedItemPosition());//????????????1-??????  2-??????  3-??????
            json.put("submitter", mSampleSongjian.getText().toString());//?????????
            json.put("version",mSampleXinghao.getText().toString());//????????????
            json.put("witness", mSampleJianzheng.getText().toString());//?????????
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
            ToastUtils.ShowCenterToast(SampleRecvActivity.this,"?????????????????????");
            return;
        }
        if(TextUtils.isEmpty(mSampleXinghao.getText().toString())){
            ToastUtils.ShowCenterToast(SampleRecvActivity.this,"?????????????????????");
            return;
        }
        if(TextUtils.isEmpty(mSampleId.getText().toString())){
            ToastUtils.ShowCenterToast(SampleRecvActivity.this,"?????????????????????");
            return;
        }
        if(TextUtils.isEmpty(mSamplePhone.getText().toString())){
            ToastUtils.ShowCenterToast(SampleRecvActivity.this,"??????????????????????????????");
            return;
        }
        if(TextUtils.isEmpty(mSampleDate.getText().toString())){
            ToastUtils.ShowCenterToast(SampleRecvActivity.this,"?????????????????????");
            return;
        }
        if(TextUtils.isEmpty(mSampleClient.getText().toString())){
            ToastUtils.ShowCenterToast(SampleRecvActivity.this,"???????????????????????????");
            return;
        }
        if( mImageAdapter.getData().size() <= 0){
            ToastUtils.ShowCenterToast(SampleRecvActivity.this,"???????????????????????????");
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
            initExtraParamsLayout(mTypeList.get(position).getId());
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
                    .maxSelectNum(8)// ????????????????????????
                    .minSelectNum(1)// ??????????????????
                    .imageEngine(GlideEngine.createGlideEngine())// ??????????????????????????????????????????
                    .selectionData(mImageAdapter.getData())
                    .forResult(new MyResultCallback(mImageAdapter));
        }
    };


    /**
     * ??????????????????
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
                Log.i(TAG, "????????????:" + media.isCompressed());
                Log.i(TAG, "??????:" + media.getCompressPath());
                Log.i(TAG, "??????:" + media.getPath());
                Log.i(TAG, "????????????:" + media.isCut());
                Log.i(TAG, "??????:" + media.getCutPath());
                Log.i(TAG, "??????????????????:" + media.isOriginal());
                Log.i(TAG, "????????????:" + media.getOriginalPath());
                Log.i(TAG, "Android Q ??????Path:" + media.getAndroidQToPath());
                Log.i(TAG, "??????: " + media.getWidth() + "x" + media.getHeight());
                Log.i(TAG, "Size: " + media.getSize());
                // TODO ????????????PictureSelectorExternalUtils.getExifInterface();??????????????????????????????????????????????????????????????????????????????
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
