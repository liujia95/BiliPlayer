package me.liujia95.biliplayer.utils;

import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 2016/1/10 18:52.
 */
public class ImageUtils {

    //获取图片的宽高
    public static int[] getBitmapSize(String path) {
        int[] size = {0, 0};
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;// 不加载bitmap到内存中
        BitmapFactory.decodeFile(path, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        size[0] = outWidth;
        size[1] = outHeight;
        return size;
    }

    public static int[] getBitmapSize(int resId) {
        int[] size = {0, 0};
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;// 不加载bitmap到内存中
        BitmapFactory.decodeResource(UIUtils.getResources(), resId, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        size[0] = outWidth;
        size[1] = outHeight;
        return size;
    }

}
