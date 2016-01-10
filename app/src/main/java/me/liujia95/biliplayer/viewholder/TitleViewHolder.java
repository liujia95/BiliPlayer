package me.liujia95.biliplayer.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.bean.VideoInfoBean;

/**
 * Created by Administrator on 2016/1/10 14:10.
 */
public class TitleViewHolder extends RecyclerView.ViewHolder {

    private TextView  mTv;
    private ImageView mIcon;

    public TitleViewHolder(View parent) {
        super(parent);
        mTv = (TextView) parent.findViewById(R.id.item_title_tv);
        mIcon = (ImageView) parent.findViewById(R.id.item_title_icon);
    }

    public void loadData(VideoInfoBean bean) {
        mTv.setText(bean.title1);
        mIcon.setImageResource(bean.icon1);
    }
}
