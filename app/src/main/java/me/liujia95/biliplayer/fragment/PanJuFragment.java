package me.liujia95.biliplayer.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.adapter.PanJuAdapter;
import me.liujia95.biliplayer.base.ParentFragment;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/9 20:56.
 */
public class PanJuFragment extends ParentFragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView       mRecyclerView;
    SwipeRefreshLayout mRefresh;

    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_panju, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.panju_recyclerview);
        mRefresh = (SwipeRefreshLayout) view.findViewById(R.id.panju_refresh);
        return view;
    }

    @Override
    public void initData() {
        //recyclerview的设置
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new PanJuAdapter());
    }

    @Override
    public void initListener() {
        mRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefresh.setRefreshing(false);
                Toast.makeText(UIUtils.getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }
}
