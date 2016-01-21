package me.liujia95.biliplayer.protocol;

import me.liujia95.biliplayer.base.BaseProtocol;
import me.liujia95.biliplayer.bean.LunboBean;

/**
 * Created by Administrator on 2016/1/19 23:10.
 */
public class PanJuLunboProtocol extends BaseProtocol<LunboBean> {

    @Override
    protected String getInterfacePath() {
        return "slideshow/13.json";
    }

}
