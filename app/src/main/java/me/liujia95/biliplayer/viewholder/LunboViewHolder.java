package me.liujia95.biliplayer.viewholder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/10 12:54.
 */
public class LunboViewHolder extends RecyclerView.ViewHolder {

    private ViewPager    mViewpager;
    private LinearLayout mPointContainer;

    public LunboViewHolder(View parent) {
        super(parent);
        mViewpager = (ViewPager) parent.findViewById(R.id.lunbo_viewpager);
        mPointContainer = (LinearLayout) parent.findViewById(R.id.lunbo_point_container);
    }

    /**
     * 加载数据
     */
    public void initData() {
        //给轮播图设置适配器
        mViewpager.setAdapter(new LunboAdapter());
        //添加点 TODO
        //自动轮播,无限轮播 TODO

    }

    /**
     * 轮播图适配器
     */
    class LunboAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(UIUtils.getContext());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(R.drawable.lunbo_01 + position);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
