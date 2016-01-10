package me.liujia95.biliplayer.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import me.liujia95.biliplayer.R;

/**
 * Created by Administrator on 2016/1/9 21:23.
 */
public class Test1Holder extends RecyclerView.ViewHolder {

    TextView mTv;
    CardView mCardView;
    public Test1Holder(View parent, TextView tv, CardView cardView) {
        super(parent);
        mTv = tv;
        mCardView = cardView;
    }

    public static Test1Holder newInstance(View parent) {
        TextView tv = (TextView) parent.findViewById(R.id.test1_tv);
        CardView cardView = (CardView) parent.findViewById(R.id.test1_cardview);
        return new Test1Holder(parent, tv,cardView);
    }

    public void setData(String data) {
        mTv.setText(data);
    }

}
