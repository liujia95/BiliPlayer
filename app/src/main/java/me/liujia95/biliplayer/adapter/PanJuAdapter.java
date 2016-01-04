package me.liujia95.biliplayer.adapter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.adapter.viewholder.PanJuViewHolder;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2015/12/31 15:27.
 */
public class PanJuAdapter extends RecyclerView.Adapter implements SwipeRefreshLayout.OnRefreshListener {

    List<String> mDatas;
    private SwipeRefreshLayout mSwipeRefresh;

    public PanJuAdapter(List<String> datas) {
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.fragment_text, parent, false);
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.test_swiperefresh);

        //设置刷新圆圈的颜色
        mSwipeRefresh.setColorSchemeColors(UIUtils.getColor(R.color.color_accent_pink));
        mSwipeRefresh.setOnRefreshListener(this);
        return PanJuViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDatas != null) {
            PanJuViewHolder viewholder = (PanJuViewHolder) holder;
            viewholder.setItemText(mDatas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public void onRefresh() {
        mSwipeRefresh.setRefreshing(false);
        Toast.makeText(UIUtils.getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
    }

}
