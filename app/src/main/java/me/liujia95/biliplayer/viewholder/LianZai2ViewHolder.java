package me.liujia95.biliplayer.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.bean.LianZaiBean;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/10 15:03.
 */
public class LianZai2ViewHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.item_lianzai_icon)
    ImageView mIcon;
    @InjectView(R.id.item_lianzai_seenum)
    TextView  mSeenum;
    @InjectView(R.id.item_lianzai_title)
    TextView  mTitle;
    @InjectView(R.id.item_lianzai_number)
    TextView  mNumber;
    @InjectView(R.id.item_lianzai_date)
    TextView  mDate;
    @InjectView(R.id.item_lianzai_icon2)
    ImageView mIcon2;
    @InjectView(R.id.item_lianzai_seenum2)
    TextView  mSeenum2;
    @InjectView(R.id.item_lianzai_title2)
    TextView  mTitle2;
    @InjectView(R.id.item_lianzai_number2)
    TextView  mNumber2;
    @InjectView(R.id.item_lianzai_date2)
    TextView  mDate2;
    private final BitmapUtils mBitmapUtils;

    public LianZai2ViewHolder(View parent) {
        super(parent);
        ButterKnife.inject(this, parent);
        mBitmapUtils = new BitmapUtils(UIUtils.getContext());
    }


    public void loadData(LianZaiBean bean) {
        mBitmapUtils.display(mIcon, bean.pic);
        mSeenum.setText(bean.seeNum);
        mTitle.setText(bean.title);
        mNumber.setText(bean.number);
        mDate.setText(bean.data);

        mBitmapUtils.display(mIcon2, bean.pic2);
        mSeenum2.setText(bean.seeNum2);
        mTitle2.setText(bean.title2);
        mNumber2.setText(bean.number2);
        mDate2.setText(bean.data2);
    }
}
