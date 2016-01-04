package me.liujia95.biliplayer.bean;

import java.util.List;

public class PanJuBean {

    public List<TuijianListEntity> tuijian_list;
    public List<String>            lunbo_list;
    public List<WanjieListEntity>  wanjie_list;
    public List<FenleiListEntity>  fenlei_list;
    public List<LianZaiListEntity> lianzai_list;

    public PanJuBean() {
    }

    public class LianZaiListEntity {
        public String date;// 2015-12-30 22:41
        public String img_url;// lianzai/01.png
        public int    number;// 13
        public int    see_count;//	1889
        public String title;// 画江湖 之灵主
    }

    public class TuijianListEntity {
        /**
         * img_url : tuijian/01.png
         * title : 剑圣的大天使
         * love_count : 32000
         */
        public String img_url;
        public String title;
        public int    love_count;

    }

    public class WanjieListEntity {
        /**
         * img_url : wanjie/01.png
         * title : 拽妹黛薇儿 第二季
         * count : 13
         */
        public String img_url;
        public String title;
        public int    count;

    }

    public class FenleiListEntity {
        /**
         * img_url : fenlei/01.png
         * title : 乙女向
         */
        public String img_url;
        public String title;

    }
}