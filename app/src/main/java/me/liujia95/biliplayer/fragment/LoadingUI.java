package me.liujia95.biliplayer.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.utils.LogUtils;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2015/12/31 9:23.
 */
public abstract class LoadingUI extends FrameLayout {

    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mSuccessView;

    private static final int STATE_NONE    = 0;
    private static final int STATE_LOADING = 1;
    private static final int STATE_EMPTY   = 2;
    private static final int STATE_ERROR   = 3;
    private static final int STATE_SUCCESS = 4;

    private int mCurrentState;

    public LoadingUI(Context context) {
        this(context, null);
    }

    public LoadingUI(Context context, AttributeSet attrs) {
        super(context, attrs);

        //添加view到容器
        if (mLoadingView == null) {
            mLoadingView = View.inflate(getContext(), R.layout.pager_loading, null);
            addView(mLoadingView);
        }

        if (mErrorView == null) {
            mErrorView = View.inflate(getContext(), R.layout.pager_error, null);
            addView(mErrorView);
        }

        if (mEmptyView == null) {
            mEmptyView = View.inflate(getContext(), R.layout.pager_empty, null);
            addView(mEmptyView);
        }

        updateUI();
    }

    private void updateUI() {
        mLoadingView.setVisibility(mCurrentState == STATE_LOADING || mCurrentState == STATE_NONE ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(mCurrentState == STATE_ERROR ? View.VISIBLE : View.GONE);
        mEmptyView.setVisibility(mCurrentState == STATE_EMPTY ? View.VISIBLE : View.GONE);

        if (mSuccessView == null && mCurrentState == STATE_SUCCESS) {
            mSuccessView = onLoadSuccessView();

            addView(mSuccessView);
        }

        if(mSuccessView!=null){
            mSuccessView.setVisibility(mCurrentState == STATE_SUCCESS?View.VISIBLE:View.GONE);
        }
    }


    public void loadData() {
        LogUtils.d("LoadingUI", "loadData state::" + mCurrentState);

        if (mCurrentState == STATE_LOADING || mCurrentState == STATE_SUCCESS) {
            return;
        }

        mCurrentState = STATE_LOADING;
        safeUpdateUI();

        new Thread(new LoadDataTask()).start();
    }

    private class LoadDataTask implements Runnable {
        @Override
        public void run() {
            //去加载数据
            ResultState state = onLoadData();

            mCurrentState = state.getState();

            safeUpdateUI();
        }
    }

    private void safeUpdateUI() {
        UIUtils.post(new Runnable() {
            @Override
            public void run() {
                updateUI();
            }
        });
    }

    protected abstract View onLoadSuccessView();

    protected abstract ResultState onLoadData();

    public enum ResultState {
        EMPTY(STATE_EMPTY), ERROR(STATE_ERROR), SUCCESS(STATE_SUCCESS);
        private int state;

        private ResultState(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }
}
