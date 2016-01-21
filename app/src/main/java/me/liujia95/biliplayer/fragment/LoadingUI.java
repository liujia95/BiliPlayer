package me.liujia95.biliplayer.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.manager.ThreadPoolManager;
import me.liujia95.biliplayer.utils.LogUtils;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/6 8:48.
 */
public abstract class LoadingUI extends FrameLayout {

	/**
	 * 四个页面
	 */
	private View mLoadingView;
	private View mErrorView;
	private View mEmptyView;
	private View mSuccessView;


	/**
	 * 页面的五个状态
	 */
	private static final int STATE_NONE    = -1;
	private static final int STATE_LOADING = 0;
	private static final int STATE_ERROR   = 1;
	private static final int STATE_EMPTY   = 2;
	private static final int STATE_SUCCESS = 3;

	private int mCurrentState = STATE_NONE;//当前的状态值

	public LoadingUI(Context context) {
		this(context, null);
	}

	public LoadingUI(Context context, AttributeSet attrs) {
		super(context, attrs);

		if (mLoadingView == null) {
			mLoadingView = View.inflate(UIUtils.getContext(), R.layout.pager_loading, null);
			addView(mLoadingView);
		}
		if (mErrorView == null) {
			mErrorView = View.inflate(UIUtils.getContext(), R.layout.pager_error, null);
			addView(mErrorView);

		}
		if (mEmptyView == null) {
			mEmptyView = View.inflate(UIUtils.getContext(), R.layout.pager_empty, null);
			addView(mEmptyView);
		}

		updateUI(); //UI更新
	}

	/**
	 * 更新UI
	 */
	private void updateUI() {
		//根据当前的状态显示对应的页面
		mLoadingView.setVisibility(mCurrentState == STATE_NONE || mCurrentState == STATE_LOADING ? View.VISIBLE : View.GONE);
		mErrorView.setVisibility(mCurrentState == STATE_ERROR ? View.VISIBLE : View.GONE);
		mEmptyView.setVisibility(mCurrentState == STATE_EMPTY ? View.VISIBLE : View.GONE);

		//加载成功的页面，要在更新UI的时候是因为加载之后才有的成功页面
		if (mSuccessView == null && mCurrentState == STATE_SUCCESS) {
			mSuccessView = onLoadSuccessView();
			addView(mSuccessView);
		}

		if (mSuccessView != null) {
			mSuccessView.setVisibility(mCurrentState == STATE_SUCCESS ? View.VISIBLE : View.GONE);
		}
	}

	/**
	 * 安全的更新UI
	 */
	private void safeUpdateUI() {
		UIUtils.post(new Runnable() {
			@Override
			public void run() {
				updateUI();
			}
		});
	}

	public void loadData() {
		LogUtils.d("mCurrentState:" + mCurrentState + "-----");
		//如果成功或正在加载，不做操作
		if (mCurrentState == STATE_SUCCESS || mCurrentState == STATE_LOADING) {
			return;
		}

		//如果是其余的状态（none,empty,error），则重新加载
		mCurrentState = STATE_LOADING;
		safeUpdateUI();

		//用线程池执行耗时操作
		ThreadPoolManager.getLongPool().execute(new LoadDataTask());
	}


	/**
	 * 加载数据的具体操作
	 */
	private class LoadDataTask implements Runnable {
		@Override
		public void run() {
			ResultState resultState = onLoadData();
			mCurrentState = resultState.getState();
			safeUpdateUI();
		}
	}

	protected abstract ResultState onLoadData();

	protected abstract View onLoadSuccessView();

	/**
	 * 结果状态的枚举
	 */
	public enum ResultState {
		ERROR(STATE_ERROR), EMPTY(STATE_EMPTY), SUCCESS(STATE_SUCCESS);

		private int state;

		ResultState(int state) {
			this.state = state;
		}

		public int getState() {
			return state;
		}
	}

}

