package com.rminfo.main;

import com.alibaba.fastjson.JSONArray;
import com.rminfo.model.wx.Article;
import com.rminfo.model.wx.TextCard;
import com.rminfo.util.wx.ParamesAPI;
import com.rminfo.util.wx.WeixinUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Users: SeaRan
 * Date: 2019/6/4
 * Time: 14:07
 * 项目名：springboot
 * 描述：发送消息
 * Description: No Description
 */
public class SendMessage {

    //发送接口
    public static String POST_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";

    public static void main(String[] args) throws IOException {
        // 调取凭证
        String access_token = WeixinUtil.getAccessToken(ParamesAPI.corpId, ParamesAPI.secret).getToken();

        // 新建图文
        Article article1 = new Article();
        article1.setTitle("news消息测试-1");
        article1.setDescription("图文信息");
        article1.setPicUrl("http://i2.w.yun.hjfile.cn/doc/201303/78ebff0b-3b4b-4695-93b7-4b5f62312ce6_08.jpg");
        article1.setUrl("http://i2.w.yun.hjfile.cn/doc/201303/78ebff0b-3b4b-4695-93b7-4b5f62312ce6_08.jpg");
        Article article2 = new Article();
        article2.setTitle("news消息测试-2");
        article2.setDescription("");
        article2.setPicUrl("http://i2.w.yun.hjfile.cn/doc/201303/78ebff0b-3b4b-4695-93b7-4b5f62312ce6_08.jpg");
        article2.setUrl("http://i2.w.yun.hjfile.cn/doc/201303/78ebff0b-3b4b-4695-93b7-4b5f62312ce6_08.jpg");
        // 整合图文
        List<Article> list = new ArrayList<Article>();
        list.add(article1);
        list.add(article2);



        //文本卡片
        TextCard textCard = new TextCard();
        textCard.setTitle("领奖通知");
        textCard.setDescription("<div class=\"gray\">2019年6月7日</div> <div class=\"normal\">恭喜你抽中iPhone X一台，领奖码：38384389438</div><div class=\"highlight\">请于2019年6月7日前联系行政同事领取</div>");
        textCard.setUrl("https://weboa.rminfo.net/RmOaWechat/index.html?keypage=4&rcdcode=21001&rcdtype=KDREQS&rcdorg=1&openid=ohjpM1Xp3JEsQ5uND02cci33xREc");
        textCard.setBtntxt("更多");
        List<TextCard> cardList = new ArrayList<TextCard>();
        cardList.add(textCard);


        // 转json
        String articlesList = JSONArray.toJSONString(cardList);

        // Post的数据
        String PostData = SNewsMsg("RHL", "", "", "1000005", articlesList);

        int result = WeixinUtil.PostMessage(access_token, "POST", POST_URL, PostData);
        // 打印结果
        if(0==result){
            System.out.println("操作成功");
        }
        else {
            System.out.println("操作失败");
        }

    }



    /**
     * news(图文消息)消息
     * @param touser UserID列表（消息接收者，多个接收者用‘|’分隔）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送――――"touser": "UserID1|UserID2|UserID3"
     * @param toparty PartyID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数――――"toparty": " PartyID1 | PartyID2 "
     * @param totag TagID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数――――"totag": " TagID1 | TagID2 "
     * msgtype 消息类型，此时固定为：news
     * @param agentid 企业应用的id，整型。可在应用的设置页面查看
     * @param articlesList 图文集合
     */
    public static String SNewsMsg(String touser,String toparty,String totag,String agentid , String articlesList){
        String postData = "{\"touser\": \"%s\",\"toparty\": \"%s\",\"totag\": \"%s\",\"msgtype\": \"news\",\"agentid\": \"%s\",\"news\": {\"articles\":%s}}";
        return String.format(postData, touser,toparty,totag,agentid,articlesList);
    }
}
