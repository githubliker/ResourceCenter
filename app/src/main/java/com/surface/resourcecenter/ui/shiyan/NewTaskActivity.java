package com.surface.resourcecenter.ui.shiyan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.surface.resourcecenter.R;
import com.surface.resourcecenter.data.Consts;
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
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.JPYibanJianceIndex1;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.JPYibanJianceIndex2;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.JPYibanJianceIndex3;

import java.util.ArrayList;
import java.util.List;


public class NewTaskActivity extends BaseActivity {
    private final static String TAG = NewTaskActivity.class.getSimpleName();

    private List<PageInfo> pageInfos = new ArrayList<>();

    public ViewPager viewPager;
    TabLayout tabLayout;
    private MainAdapter pagerAdapter;
    private final String YJBYQ = "YJBYQ";
    private String TESTNAME = YJBYQ;

    private String[]  jpguitest = {"一般检查1","一般检查2","一般检查3","成套设备的防护等级","电气间隙与爬电距离","电击防护和保护电路完整性","介电性能","温升验证（配电回路）","温升验证（电容补偿回路）"
            ,"短时耐受强度","放电试验","涌流试验","动态响应时间检测","抑制谐波或滤波功能"};

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
        initJPGuiFragment();
    }


    private void initTitle() {
        ImageView leftButton = findViewById(R.id.leftButton);
        leftButton.setVisibility(View.VISIBLE);
        leftButton.setImageResource(R.mipmap.common_back);
        TextView title = findViewById(R.id.title);
        if(TESTNAME.equals(Consts.YJSBYQ)){
            title.setText("油浸式变压器试验");
        } else {
            title.setText("试验信息");
        }
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initJPGuiFragment(){
        pageInfos.clear();
        int i = 0;
        pageInfos.add(new PageInfo(jpguitest[i++],new JPYibanJianceIndex1()));
        pageInfos.add(new PageInfo(jpguitest[i++],new JPYibanJianceIndex2()));
        pageInfos.add(new PageInfo(jpguitest[i++],new JPYibanJianceIndex3()));
        pageInfos.add(new PageInfo(jpguitest[i++],new JPFanghuDengjiIndex4()));
        pageInfos.add(new PageInfo(jpguitest[i++],new HuanwangWenshengIndex11()));
        pageInfos.add(new PageInfo(jpguitest[i++],new GaoyaJueyuanShiyanIndex5()));
        pageInfos.add(new PageInfo(jpguitest[i++],new HuanwangJixieCaozuoIndex12()));
        pageInfos.add(new PageInfo(jpguitest[i++],new GaoyaLiansuoShiyanIndex7()));
        pageInfos.add(new PageInfo(jpguitest[i++],new HuanwangJuFangIndex13()));
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
        Intent intent = new Intent(context, NewTaskActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
