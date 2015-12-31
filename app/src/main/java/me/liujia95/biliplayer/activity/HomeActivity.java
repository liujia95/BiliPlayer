package me.liujia95.biliplayer.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.base.BaseActivity;
import me.liujia95.biliplayer.factory.FragmentFactory;
import me.liujia95.biliplayer.utils.UIUtils;

public class HomeActivity extends BaseActivity {

    private Toolbar   mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initToolbar();
        initDrawerLayout();
        initViewPagerAndTabs();
        initData();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle(getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    private void initDrawerLayout() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.home_drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.open, R.string.close);
        toggle.syncState();
        drawerLayout.setDrawerListener(toggle);
    }

    private void initViewPagerAndTabs() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setTabTextColors(getResources().getColor(R.color.white_normal), getResources().getColor(R.color.white_select));

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                
            }
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(HomeActivity.this, "onPageSelected", Toast.LENGTH_SHORT).show();
                FragmentFactory.getFragment(position).loadData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(1);
        mViewPager.setCurrentItem(0);
    }

    List<String> mDatas = new ArrayList<>();

    public void initData() {
        for (int i = 0; i < 5; i++) {
            mDatas.add("页面" + i);
        }
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }


        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.getFragment(position);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return UIUtils.getString(R.string.home_tab_1 + position);
        }
    }
}
