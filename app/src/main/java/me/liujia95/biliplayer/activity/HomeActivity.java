package me.liujia95.biliplayer.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.base.BaseActivity;
import me.liujia95.biliplayer.base.BaseFragment;
import me.liujia95.biliplayer.fragment.FenquFragment;
import me.liujia95.biliplayer.fragment.LeftMenuFragment;
import me.liujia95.biliplayer.fragment.PanJuFragment;
import me.liujia95.biliplayer.utils.UIUtils;

public class HomeActivity extends BaseActivity {

    @InjectView(R.id.home_drawerlayout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.home_fl_leftmenu)
    FrameLayout  mFlLeftmenu;
    @InjectView(R.id.home_toolbar)
    Toolbar      mToolbar;
    @InjectView(R.id.home_tablayout)
    TabLayout    mTablayout;
    @InjectView(R.id.home_viewpager)
    ViewPager    mViewPager;

    private String[] mTabsArray;

    List<BaseFragment> mFragments;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.inject(this);
        //初始化
        initToolbar();
        initDrawerLayout();
        initFragment();
        //初始化事件
        initListener();
        //数据加载
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
    }

    /**
     * 初始化左侧菜单
     */
    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.home_fl_leftmenu, new LeftMenuFragment());
        ft.commit();
    }

    /**
     * 初始化事件
     */
    private void initListener() {
    }

    /**
     * 加载数据
     */
    private void initData() {
        //获得tabs的标题
        mTabsArray = UIUtils.getStringArray(R.array.home_tabs);

        //添加数据
        mFragments = new ArrayList<>();
        mFragments.add(new PanJuFragment());
        mFragments.add(new PanJuFragment());
        mFragments.add(new FenquFragment());
        mFragments.add(new PanJuFragment());
        mFragments.add(new PanJuFragment());

        //tab和viewpager绑定
        //给viewpager设置适配器
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mTablayout.setupWithViewPager(mViewPager);
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
