package me.liujia95.biliplayer.view;

import android.app.Activity;
import android.os.Build;
import android.widget.RelativeLayout;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/14 10:00.
 */
public class NavigationViewCompat {


    /**
     * 适配
     */
    public static void compat(Activity activity){
        //版本要在19到21之间
       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

           RelativeLayout nav_header = (RelativeLayout) activity.findViewById(R.id.nav_header_rl);
           RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) nav_header.getLayoutParams();

           lp.height += getStatusBarHeight();
           lp.topMargin += getStatusBarHeight();
           nav_header.setLayoutParams(lp);
       }
    }

    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = UIUtils.getContext().getResources().getIdentifier("status_bar_height", "dimens", "android");
        if (resourceId > 0) {
            result = UIUtils.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
