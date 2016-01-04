package me.liujia95.biliplayer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.liujia95.biliplayer.fragment.LoadingUI;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2015/12/29 15:54.
 */
public abstract class BaseFragment extends Fragment {

    private LoadingUI mLoadingUI;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLoadingUI = new LoadingUI(UIUtils.getContext()) {
            @Override
            protected View onLoadSuccessView() {
                return onInitSuccessView();
            }

            @Override
            protected ResultState onLoadData() {
                return onStartLoadData();
            }
        };

        return mLoadingUI;
    }

    public void loadData() {
        if (mLoadingUI != null) {
            mLoadingUI.loadData();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mLoadingUI != null) {
            mLoadingUI.loadData();
        }

    }

    protected abstract View onInitSuccessView();

    protected abstract LoadingUI.ResultState onStartLoadData();
}
