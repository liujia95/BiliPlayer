package me.liujia95.biliplayer.utils;

import android.content.Context;
import android.content.res.TypedArray;

import me.liujia95.biliplayer.R;

/**
 * Created by Administrator on 2015/12/29 23:50.
 */
public class ToolbarUtils {

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    public static int getTabsHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.tabsHeight);
    }
}
