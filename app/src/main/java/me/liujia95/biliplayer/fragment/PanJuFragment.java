package me.liujia95.biliplayer.fragment;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.adapter.PanJuLunboAdapter;
import me.liujia95.biliplayer.adapter.PanJuRecyclerView1Adapter;
import me.liujia95.biliplayer.base.BaseFragment;
import me.liujia95.biliplayer.bean.PanJuBean;
import me.liujia95.biliplayer.conf.Constants;
import me.liujia95.biliplayer.utils.LogUtils;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2015/12/31 17:13.
 */
public class PanJuFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final java.lang.String TAG = "PanJuFragment";
    private ViewPager    mViewpager;
    private RecyclerView mRecyclerView1;
    private RecyclerView mRecyclerView2;
    private PanJuBean    mPanJuBean;

    private SwitchPicTask mSwitchPicTask;

    private List<String> mLunboPictures;    // 轮播图片对应的数据

    private List<PanJuBean.FenleiListEntity>  mFenleiListEntities; //分类对应的数据
    private List<PanJuBean.TuijianListEntity> mTuijianListEntities;//推荐对应的数据
    private List<PanJuBean.WanjieListEntity>  mWanjieListEntities;//完结对应的数据
    private List<PanJuBean.LianZaiListEntity> mLianZaiListEntities;//连载对应的数据
    private SwipeRefreshLayout                mSwipeRefresh;

    @Override
    protected View onInitSuccessView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.fragment_panju, null);
        mViewpager = (ViewPager) view.findViewById(R.id.panju_viewpager);
        mRecyclerView1 = (RecyclerView) view.findViewById(R.id.panju_recyclerview1);
        mRecyclerView2 = (RecyclerView) view.findViewById(R.id.panju_recyclerview1);
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.panju_swiprefresh);
        //设置刷新圆圈的颜色
        mSwipeRefresh.setColorSchemeColors(UIUtils.getColor(R.color.color_accent_pink));
        mSwipeRefresh.setOnRefreshListener(this);
        createDatas();
        initLunbo();
        initRecyclerView1();
        return view;
    }

    public void createDatas() {
        mLunboPictures = new ArrayList<>();
        mLunboPictures.add(Constants.BASE_SERVER + "panju//lunbo//01.png");
        mLunboPictures.add(Constants.BASE_SERVER + "panju//lunbo//02.png");
        mLunboPictures.add(Constants.BASE_SERVER + "panju//lunbo//03.png");
    }

    /**
     * 加载轮播图
     */
    private void initLunbo() {
        mViewpager.setAdapter(new PanJuLunboAdapter(mLunboPictures));
        // 设置viewpager选中中间页面
        int middle = Integer.MAX_VALUE / 2;
        int extra = middle % mLunboPictures.size();
        int item = middle - extra;
        mViewpager.setCurrentItem(item);

        // 开启自动轮播
        if (mSwitchPicTask == null) {
            mSwitchPicTask = new SwitchPicTask();
        }
        mSwitchPicTask.start();

        // 设置viewpager的监听
        mViewpager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mSwitchPicTask.stop();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mSwitchPicTask.start();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void initRecyclerView1() {
        LogUtils.d(TAG, "initRecyclerView1");
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(UIUtils.getContext()));
        mRecyclerView1.setAdapter(new PanJuRecyclerView1Adapter());
    }

    @Override
    protected LoadingUI.ResultState onStartLoadData() {
        return LoadingUI.ResultState.SUCCESS;
    }

    @Override
    public void onRefresh() {
        mRecyclerView1.getAdapter().notifyDataSetChanged();
        mSwipeRefresh.setRefreshing(false);
        Toast.makeText(UIUtils.getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
    }

    class SwitchPicTask implements Runnable {

        @Override
        public void run() {
            // 设置选中下一页
            int item = mViewpager.getCurrentItem();
            mViewpager.setCurrentItem(++item);

            UIUtils.postDelayed(this, 7000);
        }

        public void start() {
            stop();
            UIUtils.postDelayed(this, 7000);
        }

        public void stop() {
            UIUtils.removeCallbacks(this);
        }
    }
}
