package me.liujia95.biliplayer.utils;

import me.liujia95.biliplayer.R;

/**
 * Created by Administrator on 2016/1/9 20:06.
 */
public interface Constants {
    int DEBUGLEVEL = LogUtils.LEVEL_ALL;
    String BASE_SERVER=UIUtils.getString(R.string.urlstring);

    int    CACHE_TIME = 30 * 1000;//缓存的保存时长
    String CACHE_DIR  = FileUtils.getDir("json");//缓存的根目录
}
