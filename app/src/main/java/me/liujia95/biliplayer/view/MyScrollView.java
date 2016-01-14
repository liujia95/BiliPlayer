package me.liujia95.biliplayer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import me.liujia95.biliplayer.utils.LogUtils;

/**
 * Created by Administrator on 2016/1/14 17:54.
 */
public class MyScrollView extends ScrollView {

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private boolean canScroll;

    public void setCanScroll(boolean isCan) {
        canScroll = isCan;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.d("scrollY:" + getScrollY() + "---height:" + getHeight());
        if (canScroll) {
            getParent().requestDisallowInterceptTouchEvent(true);

        }
        return super.dispatchTouchEvent(ev);
    }
}
