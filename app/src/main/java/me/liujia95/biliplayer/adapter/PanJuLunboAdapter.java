package me.liujia95.biliplayer.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import me.liujia95.biliplayer.utils.LogUtils;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2015/12/31 22:24.
 */
public class PanJuLunboAdapter extends PagerAdapter {

    private List<String> mLunboPictures; // 轮播图
    private static final String TAG = "PanJuLunboAdapter";

    public PanJuLunboAdapter(List<String> datas) {
        mLunboPictures = datas;
        LogUtils.d(TAG, mLunboPictures.size() + "--------");
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % 3;
        ImageView iv = new ImageView(UIUtils.getContext());
        String picUrl = mLunboPictures.get(position);
        LogUtils.d(TAG, picUrl);
        iv.setImageBitmap(getHttpBitmap(picUrl));
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public static Bitmap getHttpBitmap(final String url) {
        final URL[] myFileURL = new URL[1];
        final Bitmap[] bitmap = {null};
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myFileURL[0] = new URL(url);
                    //获得连接
                    HttpURLConnection conn = null;

                    conn = (HttpURLConnection) myFileURL[0].openConnection();

                    //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
                    conn.setConnectTimeout(6000);
                    //连接设置获得数据流
                    conn.setDoInput(true);
                    //不使用缓存
                    conn.setUseCaches(false);
                    //这句可有可无，没有影响
                    //conn.connect();
                    //得到数据流


                    InputStream is = conn.getInputStream();
                    //解析得到图片
                    bitmap[0] = BitmapFactory.decodeStream(is);
                    //关闭数据流
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        SystemClock.sleep(100);
        return bitmap[0];
    }
}
