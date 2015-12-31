package me.liujia95.biliplayer.fragment;

import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2015/12/30 22:52.
 */
public class PanJuFragment extends BaseFragment {

    @Override
    protected View onInitSuccessView() {
        TextView tv = new TextView(UIUtils.getContext());
        tv.setText("首页");
        return tv;
    }

    @Override
    protected LoadingUI.ResultState onStartLoadData() {

        LoadingUI.ResultState[] states = new LoadingUI.ResultState[]{LoadingUI.ResultState.EMPTY, LoadingUI.ResultState.ERROR, LoadingUI.ResultState.SUCCESS};

        SystemClock.sleep(2000);
        Random random = new Random();
        return states[random.nextInt(states.length)];
    }
}
