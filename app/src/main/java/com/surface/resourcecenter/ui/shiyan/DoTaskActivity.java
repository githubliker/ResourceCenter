package com.surface.resourcecenter.ui.shiyan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.utils.StatusBarUtil;
import com.surface.resourcecenter.ui.BaseActivity;
import com.surface.resourcecenter.ui.home.adapter.bean.PageInfo;
import com.surface.resourcecenter.ui.shiyan.nativeData.biandianzhan.DianyabiTestIndex3;
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
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.DianyabiTestIndex4;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.GanyingNaiyaIndex8;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.GsWenshengIndex10;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.JubuFangdianIndex9;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.JueyuanyeIndex9;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.KongzaiSunhaoIndex5;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.LeidianChongjiIndex11;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.WaishiNaiyaIndex7;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.YjWenshengIndex10;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.ZhiliudianzuBupinighenglvIndex3;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.ZhiliudianzuXishoubiIndex2;
import com.surface.resourcecenter.ui.shiyan.nativeData.byq.ZukangFuzaiSunhaoIndex6;
import com.surface.resourcecenter.ui.shiyan.nativeData.dianlan.FenZhiXiangYibanJianceIndex1;
import com.surface.resourcecenter.ui.shiyan.nativeData.diyagui.DiYaYibanJianceIndex1;
import com.surface.resourcecenter.ui.shiyan.nativeData.diyagui.DiyaLeidianChongji;
import com.surface.resourcecenter.ui.shiyan.nativeData.diyagui.DiyaWensheng;
import com.surface.resourcecenter.ui.shiyan.nativeData.diyagui.Diyadianjifanghu;
import com.surface.resourcecenter.ui.shiyan.nativeData.jp.JPWensheng_Dianrong;
import com.surface.resourcecenter.ui.shiyan.nativeData.jp.JPWensheng_Peidian;
import com.surface.resourcecenter.ui.shiyan.nativeData.jp.JPdianjifanghu;
import com.surface.resourcecenter.ui.shiyan.nativeData.jp.JPgongpinnaiya;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.DlqGongpinNaiyaIndex5;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.DlqWenshengIndex4;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.DuanziJingfuzaiIndex13;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.FanghuDengjiIndex9;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.GaodiWenShiyanIndex12;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.HuiludianzuIndex2;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.JinshuDuCengIndex14;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.JixieCaozuoiIndex10;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.JixieShiyanIndex3;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.JixieWendingxingShiyanIndex11;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.JueyuanshiyanIndex7;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.KaiguanXingnengIndex9;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.LeidianChongjiIndex6;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.LiansuoShiyanIndex11;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.NaishoudianliushiyanIndex8;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguan.WaiguanIndex1;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.GaoyaGongpinNaiyaIndex2;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.GaoyaGuiTiJianCeIndex8;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.GaoyaHuiluDianzuIndex3;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.GaoyaJixieCaozuoIndex6;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.GaoyaJuFangIndex9;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.GaoyaJueyuanShiyanIndex5;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.GaoyaLeidianChongjiIndex1;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.GaoyaLiansuoShiyanIndex7;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.GaoyaWenshengIndex4;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.HuanwangGuiTiJianCeIndex10;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.HuanwangJixieCaozuoIndex12;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.HuanwangJuFangIndex13;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.HuanwangWenshengIndex11;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.JPFanghuDengjiIndex4;
import com.surface.resourcecenter.ui.shiyan.nativeData.jp.JPYibanJianceIndex1;
import com.surface.resourcecenter.ui.shiyan.nativeData.jp.JPYibanJianceIndex2;

import java.util.ArrayList;
import java.util.List;


public class DoTaskActivity extends BaseActivity {
    private final static String TAG = DoTaskActivity.class.getSimpleName();

    private List<PageInfo> pageInfos = new ArrayList<>();

    public ViewPager viewPager;
    TabLayout tabLayout;
    private MainAdapter pagerAdapter;
    private final String YJBYQ = "YJBYQ";
    private String TESTNAME = YJBYQ;

    private String[]  yjbyqtest = {"直流绝缘电阻、吸收比测量","直流电阻不平衡率测量","电压比测量和联结组标号检定","空载损耗和空载电流测量","短路阻抗和负载损耗测量",
            "外施耐压试验","感应耐压试验","绝缘液试验","温升试验","雷电冲击试验"};
    private String[]  gsbyqtest = {"直流绝缘电阻测量","直流电阻不平衡率测量","电压比测量和联结组标号检定","空载损耗和空载电流测量","短路阻抗和负载损耗测量",
            "外施耐压试验","感应耐压试验","局部放电测量","温升试验","雷电冲击试验"};

    private String[]  msxsbdztest = {"直流绝缘电阻测量","直流电阻不平衡率测量","电压比测量和联结组标号检定","空载损耗和空载电流测量","短路阻抗和负载损耗测量",
            "外施耐压试验","感应耐压试验","绝缘液试验","一般检查","机械操作试验","温升试验","雷电冲击试验"};
    private String[]  osxsbdztest = {"绝缘试验","绝缘试验","设计和外观检查","接线正确性检查","接地连续性试验",
            "功能试验","温升试验","耐受电流能力试验","防护等级检验IP","防护等级检验IK","声级试验","内部电弧试验"};

    private String[]  duanluqitest = {"结构、外观检查","主回路电阻测量","机械试验","温升试验","工频耐压试验",
            "雷电冲击试验","绝缘试验"};
    private String[]  gelikaiguantest = {"结构、外观检查","工频耐压试验","雷电冲击试验","主回路电阻测量","温升试验",
            "耐受电流试验","防护等级检验","绝缘试验","接地开关性能试验","机械操作","连锁试验","机械稳定性试验","高低温试验","端子静负载试验","金属镀层检测"};
    private String[]  zhushangkaiguantest = {"结构、外观检查","机械试验","温升试验","主回路电阻测量","工频耐压试验",
            "雷电冲击试验","绝缘试验"};

    private String[]  gaoyakaiguanguitest = {"雷电冲击试验","工频耐压试验","主回路电阻测量","温升试验","绝缘试验",
            "机械特性试验","联锁试验","柜体检测","局部放电测量"};
    private String[]  huanwangguitest = {"雷电冲击试验","工频耐压试验","主回路电阻测量","柜体检测","温升试验","绝缘试验",
            "机械特性试验","联锁试验","局部放电测量"};


    private String[]  jpguitest = {"一般检查1","一般检查2","电击防护和保护电路完整性","工频耐压试验","配电回路温升试验","补偿电路温升试验"};
    private String[]  dyguitest = {"一般检查1","电击防护和保护电路完整性","工频耐压试验","温升试验"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rc_activity_test_layout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        pagerAdapter = new MainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        initTitle();
        initDiyaGuiFragment();
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
        toolbar.setTitle("试验信息");

    }


    private void initYjByqFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(yjbyqtest[i++],new ZhiliudianzuXishoubiIndex2()));
        pageInfos.add(new PageInfo(yjbyqtest[i++],new ZhiliudianzuBupinighenglvIndex3()));
        pageInfos.add(new PageInfo(yjbyqtest[i++],new DianyabiTestIndex4()));
        pageInfos.add(new PageInfo(yjbyqtest[i++],new KongzaiSunhaoIndex5()));
        pageInfos.add(new PageInfo(yjbyqtest[i++],new ZukangFuzaiSunhaoIndex6()));
        pageInfos.add(new PageInfo(yjbyqtest[i++],new WaishiNaiyaIndex7()));
        pageInfos.add(new PageInfo(yjbyqtest[i++],new GanyingNaiyaIndex8()));
        pageInfos.add(new PageInfo(yjbyqtest[i++],new JueyuanyeIndex9()));
        pageInfos.add(new PageInfo(yjbyqtest[i++],new YjWenshengIndex10()));
        pageInfos.add(new PageInfo(yjbyqtest[i++],new LeidianChongjiIndex11()));
        pagerAdapter.notifyDataSetChanged();
    }

    private void initGsByqFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(gsbyqtest[i++],new ZhiliudianzuXishoubiIndex2()));
        pageInfos.add(new PageInfo(gsbyqtest[i++],new ZhiliudianzuBupinighenglvIndex3()));
        pageInfos.add(new PageInfo(gsbyqtest[i++],new DianyabiTestIndex4()));
        pageInfos.add(new PageInfo(gsbyqtest[i++],new KongzaiSunhaoIndex5()));
        pageInfos.add(new PageInfo(gsbyqtest[i++],new ZukangFuzaiSunhaoIndex6()));
        pageInfos.add(new PageInfo(gsbyqtest[i++],new WaishiNaiyaIndex7()));
        pageInfos.add(new PageInfo(gsbyqtest[i++],new GanyingNaiyaIndex8()));
        pageInfos.add(new PageInfo(gsbyqtest[i++],new JubuFangdianIndex9()));
        pageInfos.add(new PageInfo(gsbyqtest[i++],new GsWenshengIndex10()));
        pageInfos.add(new PageInfo(gsbyqtest[i++],new LeidianChongjiIndex11()));
        pagerAdapter.notifyDataSetChanged();
    }

    private void initMsXsBdzFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(msxsbdztest[i++],new ZhiliudianzuIndex1()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new ZhiliudianzuBupinighenglvIndex2()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new DianyabiTestIndex3()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new KongzaiSunhaoIndex4()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new ZukangFuzaiSunhaoIndex5()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new WaishiNaiyaIndex6()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new GanyingNaiyaIndex7()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new JueyuanyeIndex8()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new YibanjianchaIndex9()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new JixieCaozuoIndex10()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new MsxsWenshengIndex11()));
        pageInfos.add(new PageInfo(msxsbdztest[i++],new LeidianChongjiIndex11()));
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

    private void initDuanLuqiFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(duanluqitest[i++],new WaiguanIndex1()));
        pageInfos.add(new PageInfo(duanluqitest[i++],new HuiludianzuIndex2()));
        pageInfos.add(new PageInfo(duanluqitest[i++],new JixieShiyanIndex3()));
        pageInfos.add(new PageInfo(duanluqitest[i++],new DlqWenshengIndex4()));
        pageInfos.add(new PageInfo(duanluqitest[i++],new DlqGongpinNaiyaIndex5()));
        pageInfos.add(new PageInfo(duanluqitest[i++],new LeidianChongjiIndex6()));
        pageInfos.add(new PageInfo(duanluqitest[i++],new JueyuanshiyanIndex7()));
        pagerAdapter.notifyDataSetChanged();
    }

    private void initZhushangkaiguanFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(zhushangkaiguantest[i++],new WaiguanIndex1()));
        pageInfos.add(new PageInfo(zhushangkaiguantest[i++],new JixieShiyanIndex3()));
        pageInfos.add(new PageInfo(zhushangkaiguantest[i++],new DlqWenshengIndex4()));
        pageInfos.add(new PageInfo(zhushangkaiguantest[i++],new HuiludianzuIndex2()));
        pageInfos.add(new PageInfo(zhushangkaiguantest[i++],new DlqGongpinNaiyaIndex5()));
        pageInfos.add(new PageInfo(zhushangkaiguantest[i++],new LeidianChongjiIndex6()));
        pageInfos.add(new PageInfo(zhushangkaiguantest[i++],new JueyuanshiyanIndex7()));
        pagerAdapter.notifyDataSetChanged();
    }
    private void initGelikaiguanFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new WaiguanIndex1()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new DlqGongpinNaiyaIndex5()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new LeidianChongjiIndex6()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new HuiludianzuIndex2()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new DlqWenshengIndex4()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new NaishoudianliushiyanIndex8()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new FanghuDengjiIndex9()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new JueyuanshiyanIndex7()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new KaiguanXingnengIndex9()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new JixieCaozuoiIndex10()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new LiansuoShiyanIndex11()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new JixieWendingxingShiyanIndex11()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new GaodiWenShiyanIndex12()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new DuanziJingfuzaiIndex13()));
        pageInfos.add(new PageInfo(gelikaiguantest[i++],new JinshuDuCengIndex14()));
        pagerAdapter.notifyDataSetChanged();
    }

    private void initGaoyaKaiguanguiFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoyaLeidianChongjiIndex1()));
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoyaGongpinNaiyaIndex2()));
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoyaHuiluDianzuIndex3()));
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoyaWenshengIndex4()));
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoyaJueyuanShiyanIndex5()));
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoyaJixieCaozuoIndex6()));
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoyaLiansuoShiyanIndex7()));
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoyaGuiTiJianCeIndex8()));
        pageInfos.add(new PageInfo(gaoyakaiguanguitest[i++],new GaoyaJuFangIndex9()));
        pagerAdapter.notifyDataSetChanged();
    }

    private void initHuanwangGuiFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(huanwangguitest[i++],new FenZhiXiangYibanJianceIndex1()));
        pageInfos.add(new PageInfo(huanwangguitest[i++],new GaoyaGongpinNaiyaIndex2()));
        pageInfos.add(new PageInfo(huanwangguitest[i++],new GaoyaHuiluDianzuIndex3()));
        pageInfos.add(new PageInfo(huanwangguitest[i++],new HuanwangGuiTiJianCeIndex10()));
        pageInfos.add(new PageInfo(huanwangguitest[i++],new HuanwangWenshengIndex11()));
        pageInfos.add(new PageInfo(huanwangguitest[i++],new GaoyaJueyuanShiyanIndex5()));
        pageInfos.add(new PageInfo(huanwangguitest[i++],new HuanwangJixieCaozuoIndex12()));
        pageInfos.add(new PageInfo(huanwangguitest[i++],new GaoyaLiansuoShiyanIndex7()));
        pageInfos.add(new PageInfo(huanwangguitest[i++],new HuanwangJuFangIndex13()));
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
        pageInfos.add(new PageInfo(dyguitest[i++],new DiyaLeidianChongji()));
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

    public static void launch(Context context) {
        Intent intent = new Intent(context, DoTaskActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
