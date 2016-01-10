package me.liujia95.biliplayer.fragment;

import android.view.LayoutInflater;
import android.view.View;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.base.BaseFragment;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/9 22:53.
 */
public class LeftMenuFragment extends BaseFragment implements View.OnClickListener {


    @Override
    protected View initView(LayoutInflater inflater) {
        View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.fragment_leftmenu, null);
        return view;
    }

    @Override
    public void initListener() {
    }

    @Override
    public void onClick(View v) {
    }
}
