package me.liujia95.biliplayer.viewholder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.bean.VideoInfoBean;
import me.liujia95.biliplayer.utils.LogUtils;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/10 16:59.
 */
public class TuiJianViewHolder extends RecyclerView.ViewHolder {

    public TuiJianViewHolder(View parent) {
        super(parent);
        mIcon = (ImageView) parent.findViewById(R.id.item_tuijian_icon);
        mTitle = (TextView) parent.findViewById(R.id.item_tuijian_title);
        mNumber = (TextView) parent.findViewById(R.id.item_tuijian_number);
        mFav = (TextView) parent.findViewById(R.id.item_tuijian_fav);
    }

    private ImageView mIcon;
    private TextView  mTitle;
    private TextView  mNumber;
    private TextView  mFav;


    public void loadData(VideoInfoBean bean) {
        //        //获取图片的宽高
        //        int[] bitmapSize = ImageUtils.getBitmapSize(bean.icon1);
        //
        //        //图片的宽高比
        //        float ratio = bitmapSize[0] / (bitmapSize[1] + 0.5f);
        //        LogUtils.d("图片的宽高："+bitmapSize[0]+"--"+bitmapSize[1]+"|||图片宽高比："+ratio);
        //
        //        //计算自己的宽高
        //        int screenWidth = UIUtils.getScreenWidth();
        //        int myWidth = (screenWidth - UIUtils.dip2px(16))/2;
        //
        //        LogUtils.d("自己的宽："+myWidth);
        //
        //        //自己之后的高度
        //        int height = (int) (myWidth/ratio);
        //        mIcon.setLayoutParams(new RelativeLayout.LayoutParams(mIcon.getMeasuredWidth(),height));
        int screenWidth = UIUtils.getScreenWidth();
        int myWidth = (screenWidth - UIUtils.dip2px(16))/2;
        //目标宽度
        Bitmap bitmap = decodeSampledBitmapFromResource(bean.icon1, myWidth);

        LogUtils.d("之前宽度："+myWidth+"--之后宽度："+bitmap.getWidth());

        mIcon.setImageBitmap(bitmap);
        mTitle.setText(bean.title1);
        mNumber.setText(bean.number1);
        mFav.setText(bean.like);
    }

    public static Bitmap decodeSampledBitmapFromResource(int resId, int reqWidth) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(UIUtils.getResources(),resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(UIUtils.getResources(),resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth) {
        // 源图片的宽度
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (width > reqWidth) {
            // 计算出实际宽度和目标宽度的比率
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = widthRatio;
        }
        return inSampleSize;
    }

}
