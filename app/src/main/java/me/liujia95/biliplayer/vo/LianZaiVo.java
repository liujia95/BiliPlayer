package me.liujia95.biliplayer.vo;

import java.util.List;

/**
 * Created by Administrator on 2016/1/22 10:29.
 */
public class LianZaiVo {

    /**
     * hot
     */
    public HotEntity hot;

    public static class HotEntity {
        /**
         * note : 统计7日内新投稿的数据综合得分，每十分钟更新一次。
         * code : 0
         * pages : 1
         * num : 10
         * list :
         */
        public String           note;
        public int              code;
        public int              pages;
        public int              num;
        public List<ListEntity> list;

        public static class ListEntity {
            /**
             * favorites : 1926（收藏量）
             * play : 579057（播放量）
             * coins : （硬币的数量）
             * author : 哔哩哔哩番剧
             * description : #02 大豆棒与无酒精啤酒与…
             * mid : 928123
             * badgepay : false
             * pic : http://i1.hdslb.com/320_200/video/8d/8d2a1d9d078165d98d1ff6ec686956ed.jpg
             * title : 【1月】粗点心战争 02【独家正版】
             * pts : 579057
             * duration : 24:30
             * review : 3024（评论数）
             * subtitle :
             * create : 2016-01-15 15:06（创建的时间）
             * video_review : 35727（弹幕量）
             * aid : 3585703
             */
            public int     favorites;
            public int     play;
            public int     coins;
            public String  author;
            public String  description;
            public int     mid;
            public boolean badgepay;
            public String  pic;
            public String  title;
            public int     pts;
            public String  duration;
            public int     review;
            public String  subtitle;
            public String  create;
            public int     video_review;
            public String  aid;
        }
    }
}
