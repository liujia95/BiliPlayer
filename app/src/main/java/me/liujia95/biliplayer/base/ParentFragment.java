package me.liujia95.biliplayer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/1/20 10:04.
 */
public abstract class ParentFragment extends Fragment {
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = initView(inflater);
        return mRootView;
    }

    public View getRootView() {
        if (mRootView != null) {
            return mRootView;
        }
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();
        initListener();
        loadData();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * @param inflater
     * @return
     * @des 初始化view，需要子类复写
     */
    protected abstract View initView(LayoutInflater inflater);

    public void loadData() {

    }

    /**
     * @des 初始化数据
     */
    public void initData() {
    }

    /**
     * @des 初始化事件
     */
    public void initListener() {
    }
}
