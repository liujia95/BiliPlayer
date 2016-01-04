package me.liujia95.biliplayer.conf;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.utils.LogUtils;
import me.liujia95.biliplayer.utils.UIUtils;

public interface Constants {
    int    DEBUGLEVEL       = LogUtils.LEVEL_ALL;
    String IS_GUID_FINISHED = "is_guid_finished";
    long   DELAYED_TIME     = 30 * 1000;
    String BASE_SERVER      = UIUtils.getString(R.string.url);
}
