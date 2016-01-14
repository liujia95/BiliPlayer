package me.liujia95.biliplayer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.utils.UIUtils;
import me.liujia95.biliplayer.viewholder.FaxianBottomViewHolder;
import me.liujia95.biliplayer.viewholder.FaxianCenterViewHolder;
import me.liujia95.biliplayer.viewholder.FaxianTopViewHolder;

/**
 * Created by Administrator on 2016/1/14 15:07.
 */
public class FaxianAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_TOP_SEARCH = 0;
    private static final int TYPE_CENTER     = 1;
    private static final int TYPE_BOTTOM     = 2;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TOP_SEARCH;
        } else if (position == 1) {
            return TYPE_CENTER;
        } else if (position == 2) {
            return TYPE_BOTTOM;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TOP_SEARCH) {
            View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_faxian_top_search, parent, false);
            return new FaxianTopViewHolder(view);
        } else if (viewType == TYPE_CENTER) {
            View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_faxian_center, parent, false);
            return new FaxianCenterViewHolder(view);
        } else if (viewType == TYPE_BOTTOM) {
            View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_faxian_bottom, parent, false);
            return new FaxianBottomViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
