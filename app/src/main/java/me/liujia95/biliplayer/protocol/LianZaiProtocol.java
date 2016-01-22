package me.liujia95.biliplayer.protocol;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import me.liujia95.biliplayer.base.BaseProtocol;
import me.liujia95.biliplayer.bean.LianZaiBean;
import me.liujia95.biliplayer.vo.LianZaiVo;

/**
 * Created by Administrator on 2016/1/22 11:09.
 */
public class LianZaiProtocol extends BaseProtocol<List<LianZaiBean>> {

    @Override
    protected String getInterfacePath() {
        return "catalogy/33-week.json";
    }

    @Override
    protected List<LianZaiBean> parseJson(String json) {
        Gson gson = new Gson();
        LianZaiVo lianZaiVo = gson.fromJson(json, LianZaiVo.class);
        List<LianZaiVo.HotEntity.ListEntity> list = lianZaiVo.hot.list;

        List<LianZaiBean> lianzaiList = new ArrayList<>();

        //除以二
        for (int i = 0; i < list.size(); i++) {
            LianZaiVo.HotEntity.ListEntity listEntity = list.get(i);
            String pic = listEntity.pic;
            String title = listEntity.title;
            String data = listEntity.create;
            String number = "第13话";
            String seeNum = "1074人在看";

            i++;
            LianZaiVo.HotEntity.ListEntity listEntity2 = list.get(i);
            String pic2 = listEntity2.pic;
            String title2 = listEntity2.title;
            String data2 = listEntity2.create;
            String number2 = "第13话";
            String seeNum2 = "1074人在看";

            LianZaiBean bean = new LianZaiBean(pic, title, seeNum, number, data, pic2, title2, seeNum2, number2, data2);
            lianzaiList.add(bean);
        }
        return lianzaiList;
    }
}
