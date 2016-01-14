package me.liujia95.biliplayer.viewholder;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.liujia95.biliplayer.R;

/**
 * Created by Administrator on 2016/1/14 16:13.
 */
public class FaxianBottomViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.faxian_bottom_quanzi)
    RelativeLayout mQuanzi;
    @InjectView(R.id.faxian_bottom_rank_original)
    RelativeLayout mRankOriginal;
    @InjectView(R.id.faxian_bottom_rank_all)
    RelativeLayout mRankAll;
    @InjectView(R.id.faxian_bottom_game)
    RelativeLayout mGame;
    @InjectView(R.id.faxian_bottom_biliyoo)
    RelativeLayout mBiliyoo;
    @InjectView(R.id.faxian_bottom_discovery_new)
    ImageView      mDiscoveryNew;

    public FaxianBottomViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
        //设置动画
        AnimationDrawable ad = (AnimationDrawable) mDiscoveryNew.getBackground();
        ad.start();


    }

}
