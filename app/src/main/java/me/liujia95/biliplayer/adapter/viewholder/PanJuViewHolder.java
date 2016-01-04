package me.liujia95.biliplayer.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import me.liujia95.biliplayer.R;

/**
 * Created by Administrator on 2015/12/31 15:28.
 */
public class PanJuViewHolder extends RecyclerView.ViewHolder {

    static TextView mItemTextView;

    public PanJuViewHolder(View parent, TextView itemView) {
        super(parent);
        mItemTextView = (TextView) itemView;
    }

    public static PanJuViewHolder newInstance(View parent) {
        TextView itemTextView = (TextView) parent.findViewById(R.id.item_tv);
        return new PanJuViewHolder(parent, itemTextView);
    }

    public void setItemText(CharSequence text) {
        if(mItemTextView!=null){
            mItemTextView.setText(text);
        }
    }

}
