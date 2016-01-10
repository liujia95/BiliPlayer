package me.liujia95.biliplayer.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;

import me.liujia95.biliplayer.utils.LogUtils;

/**
 * Created by Administrator on 2016/1/10 18:10.
 */
public class RatioImageView extends ImageView {


    public RatioImageView(Context context) {
        super(context);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private float mRatio;

    int mWidthMeasureSpec;
    int mHeightMeasureSpec;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mWidthMeasureSpec = widthMeasureSpec;
        mHeightMeasureSpec = heightMeasureSpec;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        //高度是根据图片的比例来缩放的
        int height = (int) (widthSize / mRatio + 0.5f);

        LogUtils.d("ratio height:" + height);

        //设置自己的宽高
        setMeasuredDimension(widthSize, height);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setImageResource(int resId) {
        //获取图片的宽高
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        LogUtils.d("img width & height:" + width + "<-->" + height);
        //得到图片的宽高比
        mRatio = width / height;

        //再次调用测量方法
        measure(mWidthMeasureSpec, mHeightMeasureSpec);

        //super.setImageResource(resId);
    }
}
