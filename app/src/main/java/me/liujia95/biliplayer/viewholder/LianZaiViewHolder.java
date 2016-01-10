package me.liujia95.biliplayer.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.bean.VideoInfoBean;

/**
 * Created by Administrator on 2016/1/10 15:03.
 */
public class LianZaiViewHolder extends RecyclerView.ViewHolder {

    private ImageView mIcon;
    private TextView  mSeenum;
    private TextView  mTitle;
    private TextView  mNumber;
    private TextView  mDate;
    private ImageView mIcon2;
    private TextView  mSeenum2;
    private TextView  mTitle2;
    private TextView  mNumber2;
    private TextView  mDate2;

    public LianZaiViewHolder(View parent) {
        super(parent);
        mIcon = (ImageView) parent.findViewById(R.id.item_lianzai_icon);
        mSeenum = (TextView) parent.findViewById(R.id.item_lianzai_seenum);
        mTitle = (TextView) parent.findViewById(R.id.item_lianzai_title);
        mNumber = (TextView) parent.findViewById(R.id.item_lianzai_number);
        mDate = (TextView) parent.findViewById(R.id.item_lianzai_date);
        mIcon2 = (ImageView) parent.findViewById(R.id.item_lianzai_icon2);
        mSeenum2 = (TextView) parent.findViewById(R.id.item_lianzai_seenum2);
        mTitle2 = (TextView) parent.findViewById(R.id.item_lianzai_title2);
        mNumber2 = (TextView) parent.findViewById(R.id.item_lianzai_number2);
        mDate2 = (TextView) parent.findViewById(R.id.item_lianzai_date2);
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
