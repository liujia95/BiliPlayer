package me.liujia95.biliplayer.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.bean.LianZaiBean;
import me.liujia95.biliplayer.bean.LunboBean;
import me.liujia95.biliplayer.bean.VideoInfoBean;
import me.liujia95.biliplayer.data.PanJuData;
import me.liujia95.biliplayer.utils.UIUtils;
import me.liujia95.biliplayer.viewholder.FenleiViewHolder;
import me.liujia95.biliplayer.viewholder.LianZai2ViewHolder;
import me.liujia95.biliplayer.viewholder.Lunbo2ViewHolder;
import me.liujia95.biliplayer.viewholder.TitleViewHolder;
import me.liujia95.biliplayer.viewholder.TuiJianViewHolder;
import me.liujia95.biliplayer.viewholder.WanJieViewHolder;

/**
 * Created by Administrator on 2016/1/9 21:13.
 */
public class PanJu2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_LUNBO       = 0;
    private static final int TYPE_NORMAL_LIST = 1;
    private static final int TYPE_TITLE       = 2;
    private static final int TYPE_FENLEI      = 3;
    private static final int TYPE_LIANZAI     = 4;
    private static final int TYPE_TUIJIAN     = 5;
    private static final int TYPE_WANJIE      = 6;

    LunboBean mLunboBean;

    private List<VideoInfoBean> mTitles;
    private List<LianZaiBean>   mLianZaiBean;

    public PanJu2Adapter(LunboBean lunboBean, List<LianZaiBean> lianZaiBean) {
        mLunboBean = lunboBean;
        mLianZaiBean = lianZaiBean;
        mTitles = PanJuData.createTitles();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_LUNBO;
        } else if (position == 1) {
            return TYPE_TITLE;
        } else {
            //减去轮播图的一个
            //减去标题的一个
            position = position - 2;
            return TYPE_LIANZAI;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LUNBO) {
            View view = getViewHolder(R.layout.item_lunbo, parent, true);
            return new Lunbo2ViewHolder(view, mLunboBean);
        } else if (viewType == TYPE_TITLE) {
            View view = getViewHolder(R.layout.item_title, parent, true);
            return new TitleViewHolder(view);
        } else if (viewType == TYPE_LIANZAI) {
            View view = getViewHolder(R.layout.item_lianzai, parent, true);
            return new LianZai2ViewHolder(view);
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
            Lunbo2ViewHolder viewholder = (Lunbo2ViewHolder) holder;
            viewholder.initData();
        } else {
            //减去轮播图的一个
            position = position - 1;
            if (viewType == TYPE_TITLE) {
                TitleViewHolder viewholder = (TitleViewHolder) holder;
                viewholder.loadData(mTitles.get(position));
            } else if (viewType == TYPE_LIANZAI) {
                position = position - 1;
                LianZai2ViewHolder viewholder = (LianZai2ViewHolder) holder;
                viewholder.loadData(mLianZaiBean.get(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mLianZaiBean.size() + mTitles.size() + 1;
    }
}
