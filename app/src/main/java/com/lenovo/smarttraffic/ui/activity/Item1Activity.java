package com.lenovo.smarttraffic.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.adapter.BasePagerAdapter;
import com.lenovo.smarttraffic.util.CommonUtil;

import butterknife.BindView;

/**
 * @author Amoly
 * @date 2019/4/11.
 * description：
 */

public class Item1Activity extends BaseActivity {
    @BindView(R.id.tab_layout_list)
    TabLayout tabLayoutList;
    @BindView(R.id.header_layout)
    LinearLayout headerLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitView();
        InitData();
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_list_tab;
    }

    private void InitView() {
        initToolBar(findViewById(R.id.toolbar), true, getString(R.string.item1));
        tabLayoutList.setupWithViewPager(viewPager);
        tabLayoutList.setTabMode(TabLayout.MODE_SCROLLABLE);
        headerLayout.setBackgroundColor(CommonUtil.getInstance().getColor());
    }

    private void InitData() {
        BasePagerAdapter basePagerAdapter = new BasePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(basePagerAdapter);
        viewPager.setOffscreenPageLimit(2);
    }


    @Override
    protected void onResume() {
        super.onResume();
        headerLayout.setBackgroundColor(CommonUtil.getInstance().getColor());
    }

}
