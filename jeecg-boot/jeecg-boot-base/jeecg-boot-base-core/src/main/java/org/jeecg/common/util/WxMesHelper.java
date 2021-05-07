package org.jeecg.common.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.zhyd.oauth.log.Log;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

public class WxMesHelper {

    /*access_token是公众号的全局唯一接口调用凭据
    公众号调用各接口时都需使用access_token
    开发者需要进行妥善保存
    access_token的存储至少要保留512个字符空间
    access_token的有效期目前为2个小时
    需定时刷新，重复获取将导致上次获取的access_token失效
    */
    public static String getAccessToken() {
        String appId = "wx7ea996aa8c4ec782";
        String appSecret = "a517f345a81fb2bd688426d4603eb018";
        String result = cn.hutool.http.HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret);
        Log.debug("result:"+result);

        JSONObject jsonObject = JSONUtil.parseObj(result);
        return jsonObject.getStr("access_token");
    }

// 账单周期
//{{time3.DATA}}
//应缴金额
//{{amount4.DATA}}
//最晚支付日期
//{{time5.DATA}}
//缴费类型
//{{thing6.DATA}}
//合同编号
//{{thing1.DATA}}

    public static void send(){
        JSONObject body=new JSONObject();
        body.set("touser","用户的openId");
        body.set("template_id","-CCmo85u5RXzVdAVrxSx8emElkrF2hpF0FT2TukjckA");
        JSONObject json=new JSONObject();
        json.set("time3",new JSONObject().set("value","2021/5/1-2021/6/1"));
        json.set("amount4",new JSONObject().set("value", "1700"));
        json.set("time5",new JSONObject().set("value","2021/5/26"));
        json.set("thing6",new JSONObject().set("value", "当月房租"));
        json.set("thing1",new JSONObject().set("value", "#1234567890%"));

        body.set("data",json);
        //发送
        String accessToken= getAccessToken();
        Log.debug("accessToken:"+accessToken);

        String post =  cn.hutool.http.HttpUtil.post("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken, body.toString());
        Log.debug("result:"+post);
    }

    public static void main(String[] args) {
        send();
    }






}
