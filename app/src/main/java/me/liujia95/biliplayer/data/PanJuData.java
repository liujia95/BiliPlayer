package me.liujia95.biliplayer.data;

import java.util.ArrayList;
import java.util.List;

import me.liujia95.biliplayer.R;
import me.liujia95.biliplayer.bean.VideoInfoBean;

/**
 * Created by Administrator on 2016/1/10 15:10.
 */
public class PanJuData {

    public static List<VideoInfoBean> createData() {
        List<VideoInfoBean> list = new ArrayList<>();
        list.add(new VideoInfoBean(R.drawable.ic_lianzai, "新番连载"));
        list.add(new VideoInfoBean(R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看", R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看"));
        list.add(new VideoInfoBean(R.drawable.lianzai_03, "爱神巧克力", "第2话", "上周六", "1074人在看", R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看"));
        list.add(new VideoInfoBean(R.drawable.lianzai_03, "爱神巧克力", "第3话", "上周六", "1074人在看", R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看"));

        list.add(new VideoInfoBean(R.drawable.ic_wanjie, "完结动画"));
        list.add(new VideoInfoBean(R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看", R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看"));
        list.add(new VideoInfoBean(R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看", R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看"));
        list.add(new VideoInfoBean(R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看", R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看"));

        list.add(new VideoInfoBean(R.drawable.ic_fenlei, "分类推荐"));
        list.add(new VideoInfoBean(R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看", R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看"));
        list.add(new VideoInfoBean(R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看", R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看"));
        list.add(new VideoInfoBean(R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看", R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看"));

        list.add(new VideoInfoBean(R.drawable.ic_zhuanti, "马猴烧月"));
        list.add(new VideoInfoBean(R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看", R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看"));
        list.add(new VideoInfoBean(R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看", R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看"));
        list.add(new VideoInfoBean(R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看", R.drawable.lianzai_03, "爱神巧克力", "第1话", "上周六", "1074人在看"));

        list.add(new VideoInfoBean(R.drawable.ic_tuijian, "番剧推荐"));

        list.addAll(createTuiJianDatas());

        return list;
    }

    public static List<VideoInfoBean> createTuiJianDatas() {
        List<VideoInfoBean> list = new ArrayList<>();
        list.add(new VideoInfoBean(R.drawable.tuijian_01, "粗点心战争", "第一话", "5"));
        list.add(new VideoInfoBean(R.drawable.tuijian_02, "粗点心战争", "第一话", "5"));
        list.add(new VideoInfoBean(R.drawable.tuijian_03, "粗点心战争", "第一话", "5"));
        list.add(new VideoInfoBean(R.drawable.tuijian_04, "粗点心战争", "第一话", "5"));
        list.add(new VideoInfoBean(R.drawable.tuijian_05, "粗点心战争", "第一话", "5"));
        list.add(new VideoInfoBean(R.drawable.tuijian_06, "粗点心战争", "第一话", "5"));
        list.add(new VideoInfoBean(R.drawable.tuijian_07, "粗点心战争", "第一话", "5"));
        list.add(new VideoInfoBean(R.drawable.tuijian_08, "粗点心战争", "第一话", "5"));
        list.add(new VideoInfoBean(R.drawable.tuijian_09, "粗点心战争", "第一话", "5"));
        list.add(new VideoInfoBean(R.drawable.tuijian_10, "粗点心战争", "第一话", "5"));
        return list;
    }

}
