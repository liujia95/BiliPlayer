package me.liujia95.biliplayer.viewholder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.bean.VideoInfoBean;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/10 16:59.
 */
public class TuiJianViewHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.item_tuijian_icon)
    public ImageView mIcon;
    @InjectView(R.id.item_tuijian_title)
    public TextView  mTitle;
    @InjectView(R.id.item_tuijian_number)
    public TextView  mNumber;
    @InjectView(R.id.item_tuijian_fav)
    public TextView  mFav;

    public TuiJianViewHolder(View parent) {
        super(parent);
        ButterKnife.inject(this, parent);
    }


    public void loadData(VideoInfoBean bean) {
        mIcon.setImageResource(bean.icon1);
        mTitle.setText(bean.title1);
        mNumber.setText(bean.number1);
        mFav.setText(bean.like);
    }

    public static Bitmap decodeSampledBitmapFromResource(int resId, int reqWidth) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(UIUtils.getResources(), resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(UIUtils.getResources(), resId, options);
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
