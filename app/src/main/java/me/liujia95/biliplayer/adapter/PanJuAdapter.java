package me.liujia95.biliplayer.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.bean.VideoInfoBean;
import me.liujia95.biliplayer.data.PanJuData;
import me.liujia95.biliplayer.utils.UIUtils;
import me.liujia95.biliplayer.viewholder.LianZaiViewHolder;
import me.liujia95.biliplayer.viewholder.LunboViewHolder;
import me.liujia95.biliplayer.viewholder.TitleViewHolder;
import me.liujia95.biliplayer.viewholder.TuiJianViewHolder;

/**
 * Created by Administrator on 2016/1/9 21:13.
 */
public class PanJuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_LUNBO       = 0;
    private static final int TYPE_NORMAL_LIST = 1;
    private static final int TYPE_TITLE       = 2;
    private static final int TYPE_STAGGERED   = 3;
    private static final int TYPE_LIANZAI     = 4;
    private static final int TYPE_TUIJIAN     = 5;


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
            }
            return TYPE_TITLE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LUNBO) {
            View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_lunbo, parent, false);
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            layoutParams.setFullSpan(true);
            view.setLayoutParams(layoutParams);
            return new LunboViewHolder(view);
        } else if (viewType == TYPE_TITLE) {
            View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_title, parent, false);
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            layoutParams.setFullSpan(true);
            view.setLayoutParams(layoutParams);
            return new TitleViewHolder(view);
        } else if (viewType == TYPE_LIANZAI) {
            View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_lianzai, parent, false);
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            layoutParams.setFullSpan(true);
            view.setLayoutParams(layoutParams);
            return new LianZaiViewHolder(view);
        } else if (viewType == TYPE_TUIJIAN) {
            View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_tuijian, parent, false);
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            layoutParams.setFullSpan(false);
            view.setLayoutParams(layoutParams);
            return new TuiJianViewHolder(view);
        }
        return null;
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
            } else if (viewType == TYPE_TUIJIAN) {
                TuiJianViewHolder viewholder = (TuiJianViewHolder) holder;
                viewholder.loadData(mDatas.get(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }
}
