package me.liujia95.biliplayer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.base.BaseActivity;
import me.liujia95.biliplayer.base.ParentFragment;
import me.liujia95.biliplayer.fragment.FaxianFragment;
import me.liujia95.biliplayer.fragment.FenquFragment;
import me.liujia95.biliplayer.fragment.PanJu2Fragment;
import me.liujia95.biliplayer.fragment.PanJuFragment;
import me.liujia95.biliplayer.utils.LogUtils;
import me.liujia95.biliplayer.utils.UIUtils;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @InjectView(R.id.home_drawerlayout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.home_toolbar)
    Toolbar      mToolbar;
    @InjectView(R.id.home_tablayout)
    TabLayout    mTablayout;
    @InjectView(R.id.home_viewpager)
    ViewPager    mViewPager;

    private String[] mTabsArray;

    List<ParentFragment> mFragments;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.inject(this);
        initListener();

        initToolbar();
        initDrawerLayout();
        initData();
    }

    /**
     * 初始化toolbar
     */
    private void initToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("BiliPlayer");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

    }

    /**
     * 初始化抽屉
     */
    private void initDrawerLayout() {
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

        mToolbar.setNavigationIcon(R.drawable.ic_navigation_drawer);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * 初始化事件
     */
    private void initListener() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ParentFragment fragment = mFragments.get(position);
                fragment.loadData();
                LogUtils.d("---(" + position + ")加载中......");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 加载数据
     */
    private void initData() {
        //获得tabs的标题
        mTabsArray = UIUtils.getStringArray(R.array.home_tabs);

        //添加fragment
        mFragments = new ArrayList<>();
        mFragments.add(new PanJu2Fragment());
        mFragments.add(new PanJuFragment());
        mFragments.add(new FenquFragment());
        mFragments.add(new PanJuFragment());
        mFragments.add(new FaxianFragment());

        //tab和viewpager绑定
        //给viewpager设置适配器
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mTablayout.setupWithViewPager(mViewPager);

        //因为viewpager初始化是不会走onPageSelected事件，要手动让它加载一次
        mViewPager.setCurrentItem(1);
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.home_drawerlayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_game:
                Toast.makeText(HomeActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.action_download:
                Toast.makeText(HomeActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_download) {

        } else if (id == R.id.nav_start) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_people) {

        } else if (id == R.id.nav_shop) {

        } else if (id == R.id.nav_color) {

        } else if (id == R.id.nav_app) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.home_drawerlayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * viewpager的适配器
     */
    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabsArray[position];
        }
    }
}