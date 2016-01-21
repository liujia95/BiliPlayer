package me.liujia95.biliplayer.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import me.liujia95.biliplayer.fragment.LoadingUI;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/9 20:56.
 */
public abstract class BaseFragment extends ParentFragment {


    private LoadingUI mLoadingUI;

    @Override
    protected View initView(LayoutInflater inflater) {
        if (mLoadingUI == null)
        {
            mLoadingUI = new LoadingUI(UIUtils.getContext()) {

                @Override
                protected ResultState onLoadData()
                {
                    return onStartLoadData();
                }

                @Override
                protected View onLoadSuccessView()
                {
                    return onInitSuccessView();
                }
            };
        }
        else
        {
            // 移除view
            ViewParent parent = mLoadingUI.getParent();
            // ViewGroup
            if (parent instanceof ViewGroup)
            {
                ((ViewGroup) parent).removeView(mLoadingUI);
            }
        }
        return mLoadingUI;
    }

    /**
     * 加载数据的方法
     */
    public void loadData()
    {
        if (mLoadingUI != null)
        {
            // 去网络加载数据--->
            mLoadingUI.loadData();
        }
    }

    protected abstract View onInitSuccessView();


    protected abstract LoadingUI.ResultState onStartLoadData();
}
