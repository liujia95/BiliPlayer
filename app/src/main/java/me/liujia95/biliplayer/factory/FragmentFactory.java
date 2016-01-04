package me.liujia95.biliplayer.factory;

import android.support.v4.util.SparseArrayCompat;

import me.liujia95.biliplayer.base.BaseFragment;
import me.liujia95.biliplayer.fragment.PanJuFragment;
import me.liujia95.biliplayer.fragment.TestFragment;

/**
 * Created by Administrator on 2015/12/31 11:12.
 */
public class FragmentFactory {
    private static SparseArrayCompat<BaseFragment> mCache = new SparseArrayCompat<>(2);

    public static BaseFragment getFragment(int position) {
        BaseFragment fragment = mCache.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {
            case 0:
                fragment = new PanJuFragment();
                break;
            case 1:
                fragment = new TestFragment();
                break;
            case 2:
                fragment = new TestFragment();
                break;
            case 3:
                fragment = new TestFragment();
                break;
            case 4:
                fragment = new TestFragment();
                break;
            default:
                break;
        }
        return fragment;
    }
}
