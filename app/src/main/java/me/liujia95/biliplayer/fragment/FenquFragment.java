package me.liujia95.biliplayer.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.adapter.FenquAdapter;
import me.liujia95.biliplayer.base.ParentFragment;
import me.liujia95.biliplayer.bean.FenquBean;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/10 20:46.
 */
public class FenquFragment extends ParentFragment {

    private RecyclerView    mRecyclerView;
    private String[]        mFenquArray;
    private int[]           mIconArray;
    private List<FenquBean> mDatas;

    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_fenqu, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fenqu_recyclerView);
        return view;
    }

    @Override
    public void initData() {
        //获取数据
        mFenquArray = UIUtils.getResources().getStringArray(R.array.fenqu);
        mIconArray = new int[]{R.mipmap.ic_category_01, R.mipmap.ic_category_02, R.mipmap.ic_category_03,
                R.mipmap.ic_category_04, R.mipmap.ic_category_05, R.mipmap.ic_category_06,
                R.mipmap.ic_category_07, R.mipmap.ic_category_08, R.mipmap.ic_category_09, R.mipmap.ic_category_10,
                R.mipmap.ic_category_11, R.mipmap.ic_category_12, R.mipmap.ic_category_13};

        mDatas = new ArrayList<>();
        for(int i = 0;i<mFenquArray.length;i++){
            FenquBean bean = new FenquBean();
            bean.icon = mIconArray[i];
            bean.title = mFenquArray[i];
            mDatas.add(bean);
        }

        mRecyclerView.setLayoutManager(new GridLayoutManager(UIUtils.getContext(), 3));
        mRecyclerView.setAdapter(new FenquAdapter(mDatas));

    }


}
