package me.liujia95.biliplayer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.adapter.viewholder.PanJuRecyclerView1ViewHolder;
import me.liujia95.biliplayer.bean.PanJuBean;
import me.liujia95.biliplayer.utils.LogUtils;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/3 10:32.
 */
public class PanJuRecyclerView1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final java.lang.String TAG = "PanJuRecyclerView1Adapter";
    public List<PanJuBean.LianZaiListEntity> mLianZaiListEntities;
    private List<String> mDatas = new ArrayList<>();

    public PanJuRecyclerView1Adapter(/*List<PanJuBean.LianZaiListEntity> lianZaiListEntities*/) {
        //mLianZaiListEntities = lianZaiListEntities;
        for (int i = 0; i < 30; i++) {
            mDatas.add("item" + i);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_text, null);
        return PanJuRecyclerView1ViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PanJuRecyclerView1ViewHolder viewholder = (PanJuRecyclerView1ViewHolder) holder;
        viewholder.setItemText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        LogUtils.d(TAG,"mDatas.size():"+mDatas.size()+"---------------");
        return mDatas.size();
    }
}
