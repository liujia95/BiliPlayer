package me.liujia95.biliplayer.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.base.BaseFragment;
import me.liujia95.biliplayer.utils.UIUtils;

/**
 * Created by Administrator on 2016/1/10 20:46.
 */
public class FenquFragment extends BaseFragment {

    private GridView mGridView;
    private String[] mFenquArray;
    private int[]    mIconArray;

    @Override
    protected View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_fenqu, null);
        mGridView = (GridView) view.findViewById(R.id.fenqu_gridview);
        return view;
    }

    @Override
    public void initData() {
        //获取数据
        mFenquArray = UIUtils.getResources().getStringArray(R.array.fenqu);
        mIconArray = new int[]{R.mipmap.ic_category_01, R.mipmap.ic_category_02, R.mipmap.ic_category_03,
                R.mipmap.ic_category_04, R.mipmap.ic_category_05,R.mipmap.ic_category_06,
                R.mipmap.ic_category_07, R.mipmap.ic_category_08, R.mipmap.ic_category_09,R.mipmap.ic_category_10,
                R.mipmap.ic_category_11, R.mipmap.ic_category_12, R.mipmap.ic_category_13};

        mGridView.setAdapter(new GridAdapter());

    }

    class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mFenquArray.length;
        }

        @Override
        public Object getItem(int position) {
            return mFenquArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(UIUtils.getContext(), R.layout.item_fenqu, null);
                holder.icon = (ImageView) convertView.findViewById(R.id.item_fenqu_icon);
                holder.tv = (TextView) convertView.findViewById(R.id.item_fenqu_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.icon.setImageResource(mIconArray[position]);
            holder.tv.setText(mFenquArray[position]);
            return convertView;
        }
    }

    class ViewHolder {
        ImageView icon;
        TextView  tv;
    }

}
