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
 * Created by Administrator on 2016/1/10 15:03.
 */
public class LianZaiViewHolder extends RecyclerView.ViewHolder {


    @InjectView(R.id.item_lianzai_icon)
    ImageView mIcon;
    @InjectView(R.id.item_lianzai_seenum)
    TextView mSeenum;
    @InjectView(R.id.item_lianzai_title)
    TextView mTitle;
    @InjectView(R.id.item_lianzai_number)
    TextView mNumber;
    @InjectView(R.id.item_lianzai_date)
    TextView mDate;
    @InjectView(R.id.item_lianzai_icon2)
    ImageView mIcon2;
    @InjectView(R.id.item_lianzai_seenum2)
    TextView mSeenum2;
    @InjectView(R.id.item_lianzai_title2)
    TextView mTitle2;
    @InjectView(R.id.item_lianzai_number2)
    TextView mNumber2;
    @InjectView(R.id.item_lianzai_date2)
    TextView mDate2;

    public LianZaiViewHolder(View parent) {
        super(parent);
        ButterKnife.inject(this,parent);
    }


    public void loadData(VideoInfoBean bean) {

        mIcon.setImageResource(bean.icon1);
        mSeenum.setText(bean.seeNum1);
        mTitle.setText(bean.title1);
        mNumber.setText(bean.number1);
        mDate.setText(bean.date1);

        mIcon2.setImageResource(bean.icon2);
        mSeenum2.setText(bean.seeNum2);
        mTitle2.setText(bean.title2);
        mNumber2.setText(bean.number2);
        mDate2.setText(bean.date2);
    }
}
