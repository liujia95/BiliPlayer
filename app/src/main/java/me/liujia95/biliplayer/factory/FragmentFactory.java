package me.liujia95.biliplayer.factory;

import android.support.v4.util.LruCache;

import me.liujia95.biliplayer.fragment.BaseFragment;
import me.liujia95.biliplayer.fragment.PanJuFragment;

/**
 * Created by Administrator on 2015/12/31 11:12.
 */
public class FragmentFactory {
    private static LruCache<Integer, BaseFragment> mCache = new LruCache<>(2);

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
                fragment = new PanJuFragment();
                break;
            case 2:
                fragment = new PanJuFragment();
                break;
            case 3:
                fragment = new PanJuFragment();
                break;
            case 4:
                fragment = new PanJuFragment();
                break;
            default:
                break;
        }
        return fragment;
    }
}
