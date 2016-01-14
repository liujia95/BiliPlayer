package me.liujia95.biliplayer.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.InjectView;
import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.adapter.FaxianAdapter;
import me.liujia95.biliplayer.base.BaseFragment;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/14 16:03.
 */
public class FaxianFragment extends BaseFragment {

    @InjectView(R.id.faxian_recyclerview)
    RecyclerView mRecyclerView;

    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_faxian, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.faxian_recyclerview);
        return view;
    }

    @Override
    public void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(UIUtils.getContext()));
        mRecyclerView.setAdapter(new FaxianAdapter());
    }
}
