package me.liujia95.biliplayer.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.bean.VideoInfoBean;

/**
 * Created by Administrator on 2016/1/10 14:10.
 */
public class TitleViewHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.item_title_icon)
    ImageView mIcon;
    @InjectView(R.id.item_title_tv)
    TextView  mTv;


    public TitleViewHolder(View parent) {
        super(parent);
        ButterKnife.inject(this,parent);
    }


    public void loadData(VideoInfoBean bean) {
        mTv.setText(bean.title1);
        mIcon.setImageResource(bean.icon1);
    }

}
