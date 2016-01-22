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
        list.add(new VideoInfoBean(R.drawable.lianzai_01, "画江湖 之灵主", "第13话", "上周六", "1074人在看", R.drawable.lianzai_02, "游戏王 重制版 决斗这王国", "第1话", "上周六", "1074人在看"));
        list.add(new VideoInfoBean(R.drawable.lianzai_03, "爱神巧克力", "第2话", "上周六", "1074人在看", R.drawable.lianzai_04, "我们这一家 新篇", "第1话", "上周六", "1074人在看"));
        list.add(new VideoInfoBean(R.drawable.lianzai_05, "动画锻炼！EX", "第3话", "上周六", "1074人在看", R.drawable.lianzai_06, "高中女生给你做饭了", "第1话", "上周六", "1074人在看"));
        list.add(new VideoInfoBean(R.drawable.lianzai_05, "动画锻炼！EX", "第3话", "上周六", "1074人在看", R.drawable.lianzai_06, "高中女生给你做饭了", "第1话", "上周六", "1074人在看"));

        list.add(new VideoInfoBean(R.drawable.ic_wanjie, "完结动画"));
        list.add(new VideoInfoBean(R.drawable.wanjie_01, "恶棍之家 第六季", "12话全", R.drawable.wanjie_02, "恶棍之家 第五季", "18话全", R.drawable.wanjie_03, "恶棍之家 第四季", "28话全", 0.768f));

        list.add(new VideoInfoBean(R.drawable.ic_fenlei, "分类推荐"));
        list.add(new VideoInfoBean(R.drawable.fenlei_01, "乙女向", R.drawable.fenlei_02, "治愈", R.drawable.fenlei_03, "日常"));

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
        list.add(new VideoInfoBean(R.drawable.tuijian_01, "粗点心战争", "第一话", "5", 0.656f));
        list.add(new VideoInfoBean(R.drawable.tuijian_02, "粗点心战争", "第一话", "5", 0.667f));
        list.add(new VideoInfoBean(R.drawable.tuijian_03, "粗点心战争", "第一话", "5", 0.667f));
        list.add(new VideoInfoBean(R.drawable.tuijian_04, "粗点心战争", "第一话", "5", 0.775f));
        list.add(new VideoInfoBean(R.drawable.tuijian_05, "粗点心战争", "第一话", "5", 1.6f));
        list.add(new VideoInfoBean(R.drawable.tuijian_06, "粗点心战争", "第一话", "5", 0.775f));
        list.add(new VideoInfoBean(R.drawable.tuijian_07, "粗点心战争", "第一话", "5", 1.6f));
        list.add(new VideoInfoBean(R.drawable.tuijian_08, "粗点心战争", "第一话", "5", 1.6f));
        list.add(new VideoInfoBean(R.drawable.tuijian_09, "粗点心战争", "第一话", "5", 1.6f));
        list.add(new VideoInfoBean(R.drawable.tuijian_10, "粗点心战争", "第一话", "5", 1.6f));
        return list;
    }

    public static List<Integer> createLunboDatas() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(R.drawable.lunbo_01 + i);
        }
        return list;
    }

    public static List<VideoInfoBean> createTitles() {
        List<VideoInfoBean> list = new ArrayList<>();
        list.add(new VideoInfoBean(R.drawable.ic_lianzai, "新番连载"));
        return list;
    }
}
