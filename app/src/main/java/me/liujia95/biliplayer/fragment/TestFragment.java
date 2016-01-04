package me.liujia95.biliplayer.fragment;

import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.adapter.PanJuAdapter;
import me.liujia95.biliplayer.base.BaseFragment;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2015/12/30 22:52.
 */
public class TestFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<String>       datas;
    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView       mRecyclerView;

    @Override
    protected View onInitSuccessView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.fragment_text, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.test_recyclerview);
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.test_swiperefresh);

        //设置刷新圆圈的颜色
        mSwipeRefresh.setColorSchemeColors(UIUtils.getColor(R.color.color_accent_pink));
        mSwipeRefresh.setOnRefreshListener(this);

        datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("item" + i);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(UIUtils.getContext()));
        mRecyclerView.setAdapter(new PanJuAdapter(datas));
        return view;
    }


    @Override
    protected LoadingUI.ResultState onStartLoadData() {
        SystemClock.sleep(2000);
        return LoadingUI.ResultState.SUCCESS;
    }

    @Override
    public void onRefresh() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mSwipeRefresh.setRefreshing(false);
        Toast.makeText(UIUtils.getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
    }
}
