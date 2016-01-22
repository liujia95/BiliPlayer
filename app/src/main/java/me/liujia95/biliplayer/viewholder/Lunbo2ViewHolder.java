package me.liujia95.biliplayer.viewholder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lidroid.xutils.BitmapUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.bean.LunboBean;
import me.liujia95.biliplayer.utils.UIUtils;
import me.liujia95.biliplayer.view.MyViewPager;

/**
 * Created by Administrator on 2016/1/10 12:54.
 */
public class Lunbo2ViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.lunbo_viewpager)
    MyViewPager  mViewpager;
    @InjectView(R.id.lunbo_point_container)
    LinearLayout mPointContainer;

    private AutoBroadcastPicTask mAutoTask;
    private LunboAdapter         mAdapter;

    private final LunboBean   mLunboBean;
    private final BitmapUtils mBitmapUtils;

    public Lunbo2ViewHolder(View parent, LunboBean lunboBean) {
        super(parent);

        mLunboBean = lunboBean;
        mBitmapUtils = new BitmapUtils(UIUtils.getContext());

        ButterKnife.inject(this, parent);

        initListener();

        //初始化适配器
        if (mAdapter == null) {
            mAdapter = new LunboAdapter();
        }
        //给轮播图设置适配器
        mViewpager.setAdapter(mAdapter);

        //添加点
        for (int i = 0; i < mLunboBean.results; i++) {
            ImageView point = new ImageView(UIUtils.getContext());
            if (i == 0) {
                point.setImageResource(R.drawable.shape_indicator_select);
            } else {
                point.setImageResource(R.drawable.shape_indicator_normal);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = UIUtils.dip2px(6);
            layoutParams.bottomMargin = UIUtils.dip2px(6);
            point.setLayoutParams(layoutParams);
            mPointContainer.addView(point);
        }

        //从中间开始轮播
        int centerCount = Integer.MAX_VALUE / 2;
        centerCount = centerCount - centerCount % mLunboBean.results;
        mViewpager.setCurrentItem(centerCount);

        //开始自动轮播
        if (mAutoTask == null) {
            mAutoTask = new AutoBroadcastPicTask();
        }
        mAutoTask.start();
    }

    public void initData() {
    }


    /**
     * 自动轮播任务
     */
    private class AutoBroadcastPicTask implements Runnable {

        @Override
        public void run() {
            int currentItem = mViewpager.getCurrentItem();
            mViewpager.setCurrentItem(++currentItem);
            UIUtils.postDelayed(this, 2000);
        }

        /**
         * 开始轮播
         */
        public void start() {
            stop();//开始之前先停止，以免重复开始
            UIUtils.postDelayed(this, 2000);
        }

        /**
         * 停止轮播
         */
        public void stop() {
            UIUtils.removeCallbacks(this);
        }
    }

    /**
     * 初始化监听器
     */
    private void initListener() {
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position % mLunboBean.results;

                selectPoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mAutoTask.stop();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        mAutoTask.start();
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 根据角标设置选中的点
     *
     * @param index
     */
    private void selectPoint(int index) {
        for (int i = 0; i < mPointContainer.getChildCount(); i++) {
            ImageView point = (ImageView) mPointContainer.getChildAt(i);
            if (index == i) {
                point.setImageResource(R.drawable.shape_indicator_select);
            } else {
                point.setImageResource(R.drawable.shape_indicator_normal);
            }
        }
    }

    /**
     * 轮播图适配器
     */
    class LunboAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % mLunboBean.results;

            ImageView iv = new ImageView(UIUtils.getContext());

            iv.setScaleType(ImageView.ScaleType.FIT_XY);

            mBitmapUtils.display(iv, mLunboBean.list.get(position).img);
            //iv.setImageResource(R.drawable.lunbo_01 + position);

            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
