package me.liujia95.biliplayer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/1/9 20:56.
 */
public abstract class BaseFragment extends Fragment {

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
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * @return
     * @des 初始化view，需要子类复写
     * @param inflater
     */
    protected abstract View initView(LayoutInflater inflater);

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
