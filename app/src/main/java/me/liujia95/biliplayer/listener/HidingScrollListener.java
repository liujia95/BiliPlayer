package me.liujia95.biliplayer.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2015/12/29 23:27.
 */
public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {
    private static final int     HIDE_THRESHOLD   = 20;
    private              int     scrolledDistance = 0;
    private              boolean controlsVisible  = true;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        if (firstVisibleItem == 0) {
            if (!controlsVisible) {
                onShow();
                controlsVisible = true;
            }
        } else {
            //2.如果总的滚动距离超多了一定值（这个值取决于你自己的设定，越大，
            // 需要滑动的距离越长才能显示或者隐藏），我们就根据其方向显示或者
            // 隐藏Toolbar（dy>0意味着下滚，dy<0意味着上滚）。
            if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
                onHide();//隐藏
                controlsVisible = false;
                scrolledDistance = 0;
            } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
                onShow();//显示
                controlsVisible = true;
                scrolledDistance = 0;
            }
        }
        //基本思路
        //1.计算出滚动的总距离（deltas相加），
        // 但是只在Toolbar隐藏且上滚或者Toolbar未隐藏且下滚的时候，
        // 因为我们只关心这两种情况。
        if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
            scrolledDistance += dy;
        }
    }

    protected abstract void onShow();

    protected abstract void onHide();
}
