package me.liujia95.biliplayer.bean;

/**
 * Created by Administrator on 2016/1/10 15:06.
 */
public class VideoInfoBean {

    public static final int TYPE_TITLE   = 0;
    public static final int TYPE_LIANZAI = 1;
    public static final int TYPE_TUIJIAN = 2;
    public static final int TYPE_WANJIE  = 4;
    public static final int TYPE_FENLEI  = 5;

    /**
     * [标题]
     */
    public VideoInfoBean(int icon, String title) {
        this.icon1 = icon;
        this.title1 = title;
        this.type = TYPE_TITLE;
    }

    /**
     * [完结]
     */
    public VideoInfoBean(int icon1, String title1, String number1, int icon2, String title2, String number2, int icon3, String title3, String number3, float ratio) {
        this.icon1 = icon1;
        this.title1 = title1;
        this.number1 = number1;
        this.icon2 = icon2;
        this.title2 = title2;
        this.number2 = number2;
        this.icon3 = icon3;
        this.title3 = title3;
        this.number3 = number3;
        this.ratio = ratio;

        this.type = TYPE_WANJIE;
    }

    /**
     * [推荐]
     */
    public VideoInfoBean(int icon, String title, String number, String like, float ratio) {
        this.icon1 = icon;
        this.title1 = title;
        this.number1 = number;
        this.like = like;
        this.ratio = ratio;

        this.type = TYPE_TUIJIAN;
    }

    public VideoInfoBean(int icon1, String title1, int icon2, String title2, int icon3, String title3) {
        this.icon1 = icon1;
        this.title1 = title1;
        this.icon2 = icon2;
        this.title2 = title2;
        this.icon3 = icon3;
        this.title3 = title3;

        this.type = TYPE_FENLEI;
    }

    /**
     * [连载]
     */
    public VideoInfoBean(int icon1, String title1, String number1, String date1, String seeNum1, int icon2, String title2, String number2, String date2, String seeNum2) {
        this.icon1 = icon1;
        this.title1 = title1;
        this.number1 = number1;
        this.date1 = date1;
        this.seeNum1 = seeNum1;
        this.icon2 = icon2;
        this.title2 = title2;
        this.number2 = number2;
        this.date2 = date2;
        this.seeNum2 = seeNum2;

        this.type = TYPE_LIANZAI;
    }

    public int type;

    public int    icon1;
    public String title1;
    public String number1;
    public String date1;
    public String seeNum1;

    public int    icon2;
    public String title2;
    public String number2;
    public String date2;
    public String seeNum2;

    public int    icon3;
    public String title3;
    public String number3;

    public String like;
    public float  ratio; // 图片比例
}
