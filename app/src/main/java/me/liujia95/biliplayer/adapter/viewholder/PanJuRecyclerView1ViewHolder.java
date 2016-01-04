package me.liujia95.biliplayer.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.utils.LogUtils;

/**
 * Created by Administrator on 2016/1/3 10:33.
 */
public class PanJuRecyclerView1ViewHolder extends RecyclerView.ViewHolder {
    private static final java.lang.String TAG = "PanJuRecyclerView1ViewHolder";
    static TextView mItemTextView;
    public PanJuRecyclerView1ViewHolder(View parent, TextView itemView) {
        super(parent);
        mItemTextView = (TextView) itemView;
    }

    public static PanJuRecyclerView1ViewHolder newInstance(View parent) {
        TextView itemTextView = (TextView) parent.findViewById(R.id.item_tv);
        return new PanJuRecyclerView1ViewHolder(parent, itemTextView);
    }

    public void setItemText(CharSequence text) {
        if(mItemTextView!=null){
            mItemTextView.setText(text);
            LogUtils.d(TAG,text+"-------");
        }
    }

}
