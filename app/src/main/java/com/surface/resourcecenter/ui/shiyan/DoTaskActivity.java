package com.surface.resourcecenter.ui.shiyan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.Consts;
import com.surface.resourcecenter.data.network.ApiUrl;
import com.surface.resourcecenter.data.network.HttpListener;
import com.surface.resourcecenter.data.network.NetworkService;
import com.surface.resourcecenter.data.utils.StatusBarUtil;
import com.surface.resourcecenter.ui.BaseActivity;
import com.surface.resourcecenter.ui.dispatch.DispatchTaskActivity;
import com.surface.resourcecenter.ui.dispatch.bean.DispatchBean;
import com.surface.resourcecenter.ui.home.adapter.bean.PageInfo;
import com.surface.resourcecenter.ui.sample.bean.TestItemsBean;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.FanghuDengjiIndexIK17;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.FanghuDengjiIndexIP17;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.GanyingNaiyaIndex7;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.JixieCaozuoIndex10;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.JueyuanshiyanIndex12;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.JueyuanshiyanIndex13;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.JueyuanyeIndex8;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.KaiguanJixiecaozuoIndex14;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.KongzaiSunhaoIndex4;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.MsxsWenshengIndex11;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.OsxsNaishouDianliuIndex16;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.OsxsNeiBuDianHushiyanIndex19;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.OsxsShengjishiyanIndex18;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.OsxsWenshengIndex15;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.WaiguanIndex14;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.WaishiNaiyaIndex6;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.YibanjianchaIndex9;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.ZhiliudianzuBupinighenglvIndex2;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.ZhiliudianzuIndex1;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.ZukangFuzaiSunhaoIndex5;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.ByqKongFuZaiSunHao;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.ByqLeidianChongji;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.ByqNaiyaShiyan;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.ByqRaozuDianzu;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.ByqWenshengGS;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.ByqYaliShengji;
import com.surface.resourcecenter.ui.shiyan.nativeData.diyagui.DiYaYibanJianceIndex1;
import com.surface.resourcecenter.ui.shiyan.nativeData.diyagui.DiyaLeidianChongji;
import com.surface.resourcecenter.ui.shiyan.nativeData.diyagui.DiyaWensheng;
import com.surface.resourcecenter.ui.shiyan.nativeData.diyagui.Diyadianjifanghu;
import com.surface.resourcecenter.ui.shiyan.nativeData.diyagui.Diyagongpinnaiya;
import com.surface.resourcecenter.ui.shiyan.nativeData.fenzhixiang.FenZhiDianjiFanghu;
import com.surface.resourcecenter.ui.shiyan.nativeData.fenzhixiang.FenZhiLeidianChongji;
import com.surface.resourcecenter.ui.shiyan.nativeData.fenzhixiang.FenZhiWensheng;
import com.surface.resourcecenter.ui.shiyan.nativeData.fenzhixiang.FenZhiXiangYibanJianceIndex1;
import com.surface.resourcecenter.ui.shiyan.nativeData.fenzhixiang.FenZhigongpinnaiya;
import com.surface.resourcecenter.ui.shiyan.nativeData.gaoyagui.GaoYaYibanJianceIndex1;
import com.surface.resourcecenter.ui.shiyan.nativeData.gaoyagui.GaoyaGongpinNaiya;
import com.surface.resourcecenter.ui.shiyan.nativeData.gaoyagui.GaoyaHuiludianzu;
import com.surface.resourcecenter.ui.shiyan.nativeData.gaoyagui.GaoyaJixieTexing;
import com.surface.resourcecenter.ui.shiyan.nativeData.gaoyagui.GaoyaJueyuan;
import com.surface.resourcecenter.ui.shiyan.nativeData.gaoyagui.GaoyaLeidianChongji;
import com.surface.resourcecenter.ui.shiyan.nativeData.gaoyagui.GaoyaWensheng;
import com.surface.resourcecenter.ui.shiyan.nativeData.gelikaiguan.GeliGongpinNaiya;
import com.surface.resourcecenter.ui.shiyan.nativeData.gelikaiguan.GeliHuiludianzu;
import com.surface.resourcecenter.ui.shiyan.nativeData.gelikaiguan.GeliJueyuan;
import com.surface.resourcecenter.ui.shiyan.nativeData.gelikaiguan.GeliWensheng;
import com.surface.resourcecenter.ui.shiyan.nativeData.gelikaiguan.GeliYibanJianceIndex;
import com.surface.resourcecenter.ui.shiyan.nativeData.huanwanggui.HWGongpinNaiya;
import com.surface.resourcecenter.ui.shiyan.nativeData.huanwanggui.HWJixieTexing;
import com.surface.resourcecenter.ui.shiyan.nativeData.huanwanggui.HWJuFang;
import com.surface.resourcecenter.ui.shiyan.nativeData.huanwanggui.HWJueyuan;
import com.surface.resourcecenter.ui.shiyan.nativeData.huanwanggui.HWLeidianChongji;
import com.surface.resourcecenter.ui.shiyan.nativeData.huanwanggui.HWWensheng;
import com.surface.resourcecenter.ui.shiyan.nativeData.huanwanggui.HWYibanJianceIndex1;
import com.surface.resourcecenter.ui.shiyan.nativeData.huanwanggui.HuanwangHuiludianzu;
import com.surface.resourcecenter.ui.shiyan.nativeData.jp.JPWensheng_Dianrong;
import com.surface.resourcecenter.ui.shiyan.nativeData.jp.JPWensheng_Peidian;
import com.surface.resourcecenter.ui.shiyan.nativeData.jp.JPYibanJianceIndex1;
import com.surface.resourcecenter.ui.shiyan.nativeData.jp.JPYibanJianceIndex2;
import com.surface.resourcecenter.ui.shiyan.nativeData.jp.JPdianjifanghu;
import com.surface.resourcecenter.ui.shiyan.nativeData.jp.JPgongpinnaiya;
import com.surface.resourcecenter.ui.shiyan.nativeData.zhushang.ZhushagnYibanJiance;
import com.surface.resourcecenter.ui.shiyan.nativeData.zhushang.ZhushangGongpinNaiya;
import com.surface.resourcecenter.ui.shiyan.nativeData.zhushang.ZhushangHuiludianzu;
import com.surface.resourcecenter.ui.shiyan.nativeData.zhushang.ZhushangJixieTexing;
import com.surface.resourcecenter.ui.shiyan.nativeData.zhushang.ZhushangJueyuan;
import com.surface.resourcecenter.ui.shiyan.nativeData.zhushang.ZhushangLeidianChongji;
import com.surface.resourcecenter.ui.shiyan.nativeData.zhushang.ZhushangWensheng;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DoTaskActivity extends BaseActivity {
    private final static String TAG = DoTaskActivity.class.getSimpleName();

    private List<PageInfo> pageInfos = new ArrayList<>();

    public ViewPager viewPager;
    TabLayout tabLayout;
    private MainAdapter pagerAdapter;
    private String TESTNAME = "";
    public DispatchBean dispatchBean;
    public List<TestItemsBean> mStandardList = new ArrayList<>();

    private String[]  yjbyqtest = {"直流绝缘电阻、吸收比测量","直流电阻不平衡率测量","电压比测量和联结组标号检定","空载损耗和空载电流测量","短路阻抗和负载损耗测量",
            "外施耐压试验","感应耐压试验","绝缘液试验","温升试验","雷电冲击试验"};
    private String[]  msxsbdztest = {"直流绝缘电阻测量","直流电阻不平衡率测量","电压比测量和联结组标号检定","空载损耗和空载电流测量","短路阻抗和负载损耗测量",
            "外施耐压试验","感应耐压试验","绝缘液试验","一般检查","机械操作试验","温升试验","雷电冲击试验"};
    private String[]  osxsbdztest = {"绝缘试验","绝缘试验","设计和外观检查","接线正确性检查","接地连续性试验",
            "功能试验","温升试验","耐受电流能力试验","防护等级检验IP","防护等级检验IK","声级试验","内部电弧试验"};

    private String[]  gsbyqtest = {"绕组电阻测量，介损测量","空负载损耗测量","外施耐压感应耐压","温升试验","雷电冲击试验",
            "压力及声级测定"};
    private String[]  gelikaiguantest = {"一般检查","工频耐压试验","主回路电阻测量","温升试验","辅助和控制回路的绝缘试验"};
    private String[]  zhushangkaiguantest = {"一般检查","机械特性试验","温升试验","主回路电阻测量","工频耐压试验",
            "雷电冲击试验","辅助和控制回路的绝缘试验"};
    private String[]  dianlanfenzhixiangtest = {"一般检查","电击防护和保护电路完整性","工频耐压试验","冲击耐受电压试验 ","温升试验"};
    private String[]  huanwangguitest = {"一般检查","主回路电阻测量","机械特性试验","工频耐压试验","辅助和控制回路的绝缘试验","温升试验","雷电冲击电压试验",
            "局部放电试验"};
    private String[]  jpguitest = {"一般检查1","一般检查2","电击防护和保护电路完整性","工频耐压试验","配电回路温升试验","补偿电路温升试验"};
    private String[]  dyguitest = {"一般检查1","电击防护和保护电路完整性","介电性性能试验","温升试验"};
    private String[]  gaoyakaiguanguitest = {"一般检查","主回路电阻测量","机械特性试验","工频耐压试验","绝缘试验","雷电冲击试验","温升试验"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rc_activity_test_layout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        pagerAdapter = new MainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        dispatchBean = (DispatchBean) getIntent().getSerializableExtra("data");
        TESTNAME = dispatchBean.getSampleName();

        if(dispatchBean.getSampleType().equals(Consts.HWKGG)){
            initHuanwangGuiFragment();
        } else if(dispatchBean.getSampleType().equals(Consts.JPKGG)){
            initJPGuiFragment();
        } else if(dispatchBean.getSampleType().equals(Consts.BILEIQI)){

        } else if(dispatchBean.getSampleType().equals(Consts.DIYAKGG)){
            initDiyaGuiFragment();
        } else if(dispatchBean.getSampleType().equals(Consts.DIANLANBAOHUGUAN)){

        } else if(dispatchBean.getSampleType().equals(Consts.DIANLIDIANLAN)){

        } else if(dispatchBean.getSampleType().equals(Consts.DIANNENGJILIANGXIANG)){

        } else if(dispatchBean.getSampleType().equals(Consts.GAOYAKGG)){
            initGaoyaKaiguanguiFragment();
        } else if(dispatchBean.getSampleType().equals(Consts.GELIKGG)){
            initGelikaiguanFragment();
        } else if(dispatchBean.getSampleType().equals(Consts.ZHUSHANGKGG)){
            initZhushangkaiguanFragment();
        } else if(dispatchBean.getSampleType().equals(Consts.GAOYADIANLANFENZHIXIANG)){
            initFenzhixiangFragment();
        } else if(dispatchBean.getSampleType().equals(Consts.GANSHIBYQ)){
            initYjByqFragment();
        }else if(dispatchBean.getSampleType().equals(Consts.YOUJINBYQ)){
            initYjByqFragment();
        }

        initTitle();
        getTestItems(dispatchBean.getSampleType());
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
        toolbar.setTitle(TESTNAME+"试验信息");

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
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }
        });
    }
    private void initYjByqFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(gsbyqtest[i++],new ByqRaozuDianzu()));
        pageInfos.add(new PageInfo(gsbyqtest[i++],new ByqKongFuZaiSunHao()));
        pageInfos.add(new PageInfo(gsbyqtest[i++],new ByqNaiyaShiyan()));
        pageInfos.add(new PageInfo(gsbyqtest[i++],new ByqWenshengGS()));
        pageInfos.add(new PageInfo(gsbyqtest[i++],new ByqLeidianChongji()));
        pageInfos.add(new PageInfo(gsbyqtest[i++],new ByqYaliShengji()));
        pagerAdapter.notifyDataSetChanged();
    }

    private void initMsXsBdzFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(msxsbdztest[i++],new ZhiliudianzuIndex1()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new ZhiliudianzuBupinighenglvIndex2()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new KongzaiSunhaoIndex4()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new ZukangFuzaiSunhaoIndex5()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new WaishiNaiyaIndex6()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new GanyingNaiyaIndex7()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new JueyuanyeIndex8()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new YibanjianchaIndex9()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new JixieCaozuoIndex10()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new MsxsWenshengIndex11()));
        pagerAdapter.notifyDataSetChanged();
    }

    private void initOsXsBdzFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(osxsbdztest[i++],new JueyuanshiyanIndex12()));
        pageInfos.add(new PageInfo(osxsbdztest[i++],new JueyuanshiyanIndex13()));
        pageInfos.add(new PageInfo(osxsbdztest[i++],new WaiguanIndex14(0)));
        pageInfos.add(new PageInfo(osxsbdztest[i++],new WaiguanIndex14(1)));
        pageInfos.add(new PageInfo(osxsbdztest[i++],new WaiguanIndex14(2)));
        pageInfos.add(new PageInfo(osxsbdztest[i++],new KaiguanJixiecaozuoIndex14()));
        pageInfos.add(new PageInfo(osxsbdztest[i++],new OsxsWenshengIndex15()));
        pageInfos.add(new PageInfo(osxsbdztest[i++],new OsxsNaishouDianliuIndex16()));
        pageInfos.add(new PageInfo(osxsbdztest[i++],new FanghuDengjiIndexIP17()));
        pageInfos.add(new PageInfo(osxsbdztest[i++],new FanghuDengjiIndexIK17()));
        pageInfos.add(new PageInfo(osxsbdztest[i++],new OsxsShengjishiyanIndex18()));
        pageInfos.add(new PageInfo(osxsbdztest[i++],new OsxsNeiBuDianHushiyanIndex19()));
        pagerAdapter.notifyDataSetChanged();
    }

    private void initZhushangkaiguanFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(zhushangkaiguantest[i++],new ZhushagnYibanJiance()));
        pageInfos.add(new PageInfo(zhushangkaiguantest[i++],new ZhushangJixieTexing()));
        pageInfos.add(new PageInfo(zhushangkaiguantest[i++],new ZhushangWensheng()));
        pageInfos.add(new PageInfo(zhushangkaiguantest[i++],new ZhushangHuiludianzu()));
        pageInfos.add(new PageInfo(zhushangkaiguantest[i++],new ZhushangGongpinNaiya()));
        pageInfos.add(new PageInfo(zhushangkaiguantest[i++],new ZhushangLeidianChongji()));
        pageInfos.add(new PageInfo(zhushangkaiguantest[i++],new ZhushangJueyuan()));
        pagerAdapter.notifyDataSetChanged();
    }
    private void initGelikaiguanFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new GeliYibanJianceIndex()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new GeliGongpinNaiya()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new GeliHuiludianzu()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new GeliWensheng()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new GeliJueyuan()));
        pagerAdapter.notifyDataSetChanged();
    }

    private void initFenzhixiangFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(dianlanfenzhixiangtest[i++],new FenZhiXiangYibanJianceIndex1()));
        pageInfos.add(new PageInfo(dianlanfenzhixiangtest[i++],new FenZhiDianjiFanghu()));
        pageInfos.add(new PageInfo(dianlanfenzhixiangtest[i++],new FenZhigongpinnaiya()));
        pageInfos.add(new PageInfo(dianlanfenzhixiangtest[i++],new FenZhiLeidianChongji()));
        pageInfos.add(new PageInfo(dianlanfenzhixiangtest[i++],new FenZhiWensheng()));
        pagerAdapter.notifyDataSetChanged();
    }

    private void initGaoyaKaiguanguiFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoYaYibanJianceIndex1()));
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoyaHuiludianzu()));
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoyaJixieTexing()));
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoyaGongpinNaiya()));
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoyaJueyuan()));
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoyaLeidianChongji()));
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoyaWensheng()));
        pagerAdapter.notifyDataSetChanged();
    }

    private void initHuanwangGuiFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(huanwangguitest[i++],new HWYibanJianceIndex1()));
        pageInfos.add(new PageInfo(huanwangguitest[i++],new HuanwangHuiludianzu()));
        pageInfos.add(new PageInfo(huanwangguitest[i++],new HWJixieTexing()));
        pageInfos.add(new PageInfo(huanwangguitest[i++],new HWGongpinNaiya()));
        pageInfos.add(new PageInfo(huanwangguitest[i++],new HWJueyuan()));
        pageInfos.add(new PageInfo(huanwangguitest[i++],new HWWensheng()));
        pageInfos.add(new PageInfo(huanwangguitest[i++],new HWLeidianChongji()));
        pageInfos.add(new PageInfo(huanwangguitest[i++],new HWJuFang()));
        pagerAdapter.notifyDataSetChanged();
    }

    private void initJPGuiFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(jpguitest[i++],new JPYibanJianceIndex1()));
        pageInfos.add(new PageInfo(jpguitest[i++],new JPYibanJianceIndex2()));
        pageInfos.add(new PageInfo(jpguitest[i++],new JPdianjifanghu()));
        pageInfos.add(new PageInfo(jpguitest[i++],new JPgongpinnaiya()));
        pageInfos.add(new PageInfo(jpguitest[i++],new JPWensheng_Peidian()));
        pageInfos.add(new PageInfo(jpguitest[i++],new JPWensheng_Dianrong()));
        pagerAdapter.notifyDataSetChanged();
    }

    private void initDiyaGuiFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(dyguitest[i++],new DiYaYibanJianceIndex1()));
        pageInfos.add(new PageInfo(dyguitest[i++],new Diyadianjifanghu()));
        pageInfos.add(new PageInfo(dyguitest[i++],new Diyagongpinnaiya()));
//        pageInfos.add(new PageInfo(dyguitest[i++],new DiyaLeidianChongji()));
        pageInfos.add(new PageInfo(dyguitest[i++],new DiyaWensheng()));
        pagerAdapter.notifyDataSetChanged();
    }
    class MainAdapter extends FragmentPagerAdapter {

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return pageInfos.get(i).fragment;
        }

        @Override
        public int getCount() {
            return pageInfos.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return pageInfos.get(position).title;
        }
    }

    public static void launch(Context context, DispatchBean bean) {
        Intent intent = new Intent(context, DoTaskActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",bean);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
