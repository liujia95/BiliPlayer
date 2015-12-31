package me.liujia95.biliplayer.pages;

import android.view.View;

/**
 * Created by Administrator on 2015/12/30 19:20.
 */
public abstract class BasePager {

    protected View mRootView;
    public BasePager(){
        mRootView = initView();
    }

    protected abstract View initView();


}
