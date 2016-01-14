package me.liujia95.biliplayer.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.activity.SearchActivity;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/14 16:07.
 */
public class FaxianTopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @InjectView(R.id.faxian_top_search)
    TextView  mSearch;
    @InjectView(R.id.faxian_top_scan)
    ImageView mScan;

    public FaxianTopViewHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
        initListener();
    }

    private void initListener() {
        mSearch.setOnClickListener(this);
        mScan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSearch) {
            Intent intent = new Intent(UIUtils.getContext(), SearchActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            UIUtils.getContext().startActivity(intent);
        } else if (v == mScan) {

        }
    }
}
