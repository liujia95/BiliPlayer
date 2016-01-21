package me.liujia95.biliplayer.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.bean.VideoInfoBean;
import me.liujia95.biliplayer.data.PanJuData;
import me.liujia95.biliplayer.utils.UIUtils;
import me.liujia95.biliplayer.viewholder.FenleiViewHolder;
import me.liujia95.biliplayer.viewholder.LianZaiViewHolder;
import me.liujia95.biliplayer.viewholder.LunboViewHolder;
import me.liujia95.biliplayer.viewholder.TitleViewHolder;
import me.liujia95.biliplayer.viewholder.TuiJianViewHolder;
import me.liujia95.biliplayer.viewholder.WanJieViewHolder;

/**
 * Created by Administrator on 2016/1/9 21:13.
 */
public class PanJuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_LUNBO       = 0;
    private static final int TYPE_NORMAL_LIST = 1;
    private static final int TYPE_TITLE       = 2;
    private static final int TYPE_FENLEI      = 3;
    private static final int TYPE_LIANZAI     = 4;
    private static final int TYPE_TUIJIAN     = 5;
    private static final int TYPE_WANJIE      = 6;


    public List<VideoInfoBean> mDatas = new ArrayList<>();

    public PanJuAdapter() {
        mDatas = PanJuData.createData();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_LUNBO;
        } else {
            //减去轮播图的一个
            position = position - 1;
            int type = mDatas.get(position).type;
            if (type == VideoInfoBean.TYPE_TITLE) {
                return TYPE_TITLE;
            } else if (type == VideoInfoBean.TYPE_LIANZAI) {
                return TYPE_LIANZAI;
            } else if (type == VideoInfoBean.TYPE_TUIJIAN) {
                return TYPE_TUIJIAN;
            } else if (type == VideoInfoBean.TYPE_WANJIE) {
                return TYPE_WANJIE;
            } else if (type == VideoInfoBean.TYPE_FENLEI) {
                return TYPE_FENLEI;
            }
            return TYPE_TITLE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LUNBO) {
            View view = getViewHolder(R.layout.item_lunbo, parent, true);
            return new LunboViewHolder(view);
        } else if (viewType == TYPE_TITLE) {
            View view = getViewHolder(R.layout.item_title, parent, true);
            return new TitleViewHolder(view);
        } else if (viewType == TYPE_LIANZAI) {
            View view = getViewHolder(R.layout.item_lianzai, parent, true);
            return new LianZaiViewHolder(view);
        } else if (viewType == TYPE_WANJIE) {
            View view = getViewHolder(R.layout.item_wanjie, parent, true);
            return new WanJieViewHolder(view);
        } else if (viewType == TYPE_FENLEI) {
            View view = getViewHolder(R.layout.item_fenlei, parent, true);
            return new FenleiViewHolder(view);
        } else if (viewType == TYPE_TUIJIAN) {
            View view = getViewHolder(R.layout.item_tuijian, parent, false);
            return new TuiJianViewHolder(view);
        }
        return null;
    }

    @NonNull
    private View getViewHolder(int resource, ViewGroup parent, boolean isFullSpan) {
        View view = LayoutInflater.from(UIUtils.getContext()).inflate(resource, parent, false);
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        layoutParams.setFullSpan(isFullSpan);
        view.setLayoutParams(layoutParams);
        return view;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_LUNBO) {
            LunboViewHolder viewholder = (LunboViewHolder) holder;
            viewholder.initData();
        } else {
            //减去轮播图的一个
            position = position - 1;
            if (viewType == TYPE_TITLE) {
                TitleViewHolder viewholder = (TitleViewHolder) holder;
                viewholder.loadData(mDatas.get(position));
            } else if (viewType == TYPE_LIANZAI) {
                LianZaiViewHolder viewholder = (LianZaiViewHolder) holder;
                viewholder.loadData(mDatas.get(position));
            } else if (viewType == TYPE_WANJIE) {
                WanJieViewHolder viewholder = (WanJieViewHolder) holder;

                int screenWidth = UIUtils.getScreenWidth();
                int myWidth = (screenWidth - UIUtils.dip2px(24)) / 3;
                int height = (int) (myWidth / mDatas.get(position).ratio + 0.5f);

                viewholder.mIvIcon.setLayoutParams(new LinearLayout.LayoutParams(myWidth, height));
                viewholder.mIvIcon2.setLayoutParams(new LinearLayout.LayoutParams(myWidth, height));
                viewholder.mIvIcon3.setLayoutParams(new LinearLayout.LayoutParams(myWidth, height));

                viewholder.loadData(mDatas.get(position));
            } else if (viewType == TYPE_FENLEI) {
                FenleiViewHolder viewholder = (FenleiViewHolder) holder;
                viewholder.loadData(mDatas.get(position));
            } else if (viewType == TYPE_TUIJIAN) {
                TuiJianViewHolder viewholder = (TuiJianViewHolder) holder;
                //实现根据图片的宽度计算
                int screenWidth = UIUtils.getScreenWidth();
                int myWidth = (screenWidth - UIUtils.dip2px(16)) / 2;
                int height = (int) (myWidth / mDatas.get(position).ratio + 0.5f);

                viewholder.mIcon.setLayoutParams(new RelativeLayout.LayoutParams(myWidth, height));
                viewholder.loadData(mDatas.get(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }
}
