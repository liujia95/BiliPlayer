package me.liujia95.biliplayer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.bean.FenquBean;
import me.liujia95.biliplayer.utils.UIUtils;
import me.liujia95.biliplayer.viewholder.FenquViewHolder;

/**
 * Created by Administrator on 2016/1/14 10:15.
 */
public class FenquAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<FenquBean> mDatas;

    public FenquAdapter(List<FenquBean> datas) {
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_fenqu, parent,false);
        return new FenquViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FenquViewHolder viewholder = (FenquViewHolder) holder;
        viewholder.loadData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
