package me.liujia95.biliplayer.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/1/19 22:32.
 */
public class LunboBean {

    /**
     * list : [{"img":"http://i1.hdslb.com/u_user/573a4db61c5a1dcd28204c235df79e69.jpg","simg":"http://i0.hdslb.com/u_user/9bcb366065d19cbb4af52d8f76f81941.jpg","link":"http://www.bilibili.com/video/av579138/?br","title":"备长炭"},{"img":"http://i0.hdslb.com/u_user/a7885a149f63224e27962caf7c1beef9.jpg","simg":"http://i0.hdslb.com/u_user/6e945f475b8bdba2eeb05ea4255bc5cc.jpg","link":"http://www.bilibili.com/video/av3608429/?br","title":"机器人少女Z"},{"img":"http://i2.hdslb.com/u_user/ac1f5f02cf2362437d8f688b4def5fb3.jpg","simg":"http://i2.hdslb.com/u_user/22b0c17b9a16b5695ca6e3370b99a9e6.jpg","link":"http://www.bilibili.com/bangumi/i/904/","title":"猫神八百万"},{"img":"http://i0.hdslb.com/u_user/6d6cba26a233d246e35344f855b4a43e.jpg","simg":"http://i2.hdslb.com/u_user/e597048b1aee8d94970a08e86752cd2a.jpg","link":"http://www.bilibili.com/video/av3594894/?br","title":"京骚戏画 OVA/WEB版"},{"img":"http://i2.hdslb.com/u_user/966c170ba1909e8d9be95aa94145b357.jpg","simg":"http://i1.hdslb.com/u_user/de368f513c1d7078f703cb9a43a54c97.jpg","link":"http://www.bilibili.com/video/av3609472/?br","title":"苍彼 第2话"},{"img":"http://i2.hdslb.com/u_user/c513a706b503ded036c28e1ce31d1a45.jpg","simg":"http://i0.hdslb.com/u_user/c61680fe038f2cafd1ada7c5ff99aee3.jpg","link":"http://www.bilibili.com/topic/1012.html","title":"bilibili moe 总结篇"}]
     * results : 6
     */
    public List<ListEntity> list;
    public int              results;

    public static class ListEntity {
        /**
         * img : http://i1.hdslb.com/u_user/573a4db61c5a1dcd28204c235df79e69.jpg
         * simg : http://i0.hdslb.com/u_user/9bcb366065d19cbb4af52d8f76f81941.jpg
         * link : http://www.bilibili.com/video/av579138/?br
         * title : 备长炭
         */
        public String img;
        public String simg;
        public String link;
        public String title;

    }
}
