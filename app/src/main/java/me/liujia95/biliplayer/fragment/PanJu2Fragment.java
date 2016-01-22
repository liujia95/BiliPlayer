package me.liujia95.biliplayer.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import java.util.List;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.adapter.PanJu2Adapter;
import me.liujia95.biliplayer.base.BaseFragment;
import me.liujia95.biliplayer.bean.LianZaiBean;
import me.liujia95.biliplayer.bean.LunboBean;
import me.liujia95.biliplayer.protocol.LianZaiProtocol;
import me.liujia95.biliplayer.protocol.PanJuLunboProtocol;
import me.liujia95.biliplayer.utils.LogUtils;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/22 9:15.
 */
public class PanJu2Fragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView       mRecyclerView;
    SwipeRefreshLayout mRefresh;
    private LunboBean          mLunboBean;
    private PanJu2Adapter      mAdapter;
    private List<LianZaiBean>  mLianZaiBeen;
    private PanJuLunboProtocol mLunboProtocol;
    private LianZaiProtocol    mLianZaiProtocol;

    @Override
    protected View onInitSuccessView() {
        final View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.fragment_panju, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.panju_recyclerview);
        mRefresh = (SwipeRefreshLayout) view.findViewById(R.id.panju_refresh);
        mRefresh.setOnRefreshListener(this);
        mRefresh.setColorSchemeResources(R.color.bili_color_pink);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mRefresh.setRefreshing(true);
                refreshUI();
            }
        });
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new PanJu2Adapter(mLunboBean, mLianZaiBeen);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private void refreshUI() {
        mAdapter.notifyDataSetChanged();
        LogUtils.d("刷新");
        mRefresh.setRefreshing(false);
    }

    @Override
    protected LoadingUI.ResultState onStartLoadData() {
        mLunboProtocol = new PanJuLunboProtocol();
        mLianZaiProtocol = new LianZaiProtocol();
        try {
            mLunboBean = mLunboProtocol.loadData();
            mLianZaiBeen = mLianZaiProtocol.loadData();
            if (mLunboBean == null || mLianZaiBeen == null) {
                return LoadingUI.ResultState.EMPTY;
            }
            LogUtils.d("datas:::" + mLunboBean.list.get(0).img);
            return LoadingUI.ResultState.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return LoadingUI.ResultState.ERROR;
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    mLunboBean = mLunboProtocol.loadData();
                    mLianZaiBeen = mLianZaiProtocol.loadData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                refreshUI();
                Toast.makeText(UIUtils.getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }
}
