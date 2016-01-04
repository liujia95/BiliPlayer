package me.liujia95.biliplayer.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import me.liujia95.biliplayer.utils.LogUtils;

/**
 * Created by Administrator on 2016/1/4 9:46.
 */
public class MyCoordinatorLayout extends CoordinatorLayout {

    private static final java.lang.String TAG = "MyCoordinatorLayout";

    public MyCoordinatorLayout(Context context) {
        this(context, null);
    }

    public MyCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    float downY;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //拦截子控件的上下滑动事件
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY();

                float diffY = moveY - downY;
                LogUtils.d(TAG,"downY:"+downY+"::moveY:"+moveY+"---diff:"+diffY);
                if (diffY != 0) {
                    return false;
                }
                downY = moveY;
                break;
        }
        //等自己滑不动了，再把事件给子控件
        return super.onInterceptTouchEvent(ev);
    }

}
