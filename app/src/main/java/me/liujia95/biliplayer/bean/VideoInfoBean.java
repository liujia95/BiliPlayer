package me.liujia95.biliplayer.bean;

/**
 * Created by Administrator on 2016/1/10 15:06.
 */
public class VideoInfoBean {

    public static final int TYPE_TITLE   = 0;
    public static final int TYPE_LIANZAI = 1;
    public static final int TYPE_TUIJIAN = 2;

    /**
     * [标题]
     */
    public VideoInfoBean(int icon, String title) {
        this.icon1 = icon;
        this.title1 = title;
        this.type = TYPE_TITLE;
    }

    /**
     * [推荐]
     */
    public VideoInfoBean(int icon, String title, String number, String like) {
        this.icon1 = icon;
        this.title1 = title;
        this.number1 = number;
        this.like = like;

        this.type = TYPE_TUIJIAN;
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

    public String like;
}
