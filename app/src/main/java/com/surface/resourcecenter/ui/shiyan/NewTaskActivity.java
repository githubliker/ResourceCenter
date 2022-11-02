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
import com.surface.resourcecenter.ui.BaseActivity;
import com.surface.resourcecenter.ui.home.adapter.bean.PageInfo;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.GaoyaJueyuanShiyanIndex5;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.GaoyaLiansuoShiyanIndex7;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.HuanwangJixieCaozuoIndex12;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.HuanwangJuFangIndex13;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.HuanwangWenshengIndex11;
import com.surface.resourcecenter.ui.shiyan.nativeData.kaiguangui.JPFanghuDengjiIndex4;
import com.surface.resourcecenter.ui.shiyan.nativeData.jp.JPYibanJianceIndex1;
import com.surface.resourcecenter.ui.shiyan.nativeData.jp.JPYibanJianceIndex2;
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
        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("试验信息");
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
