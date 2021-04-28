package org.jeecg.paymodule.unifydemo;

import org.jeecg.DemoConstant;
import org.jeecg.paymodule.pojo.wechat.WechatRefundQueryVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeecg.paymodule.utils.MD5Utils;
import org.jeecg.paymodule.utils.SignatureUtil;
import org.jeecg.paymodule.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class WechatRefundQueryDemo {

    private static final String url = "https://218.18.231.137:8065/hou/apiuat/weixinPay/refundStatusQuery" ;

    private static final String appId = "8ab74856-8772-45c9-96db-54cb30ab9f74";

    private static final String appSecret = "5b96f20a-011f-4254-8be8-9a5ceb2f317f";

    public static void main(String []args){
        //初始化请求报文
        String requestString = initRequestParam();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String,String> requestMap = objectMapper.readValue(requestString,Map.class);

            //对请求报文进行加密
            Map<String,String> apiEncMap = new HashMap<>();
            apiEncMap.put("appid",appId);
            apiEncMap.put("secret",appSecret);
            apiEncMap.put("sign",requestMap.get(DemoConstant.SIGN));
            apiEncMap.put("timestamp",""+System.currentTimeMillis()/1000);

            //MD5加密
            String MD5Content = SignatureUtil.getSignContent(apiEncMap);
            String apiEncString = MD5Utils.getMD5Content(MD5Content).toLowerCase();

            //组请求报文头
            Map<String,String> requstHeader = new HashMap<>();
            requstHeader.put("appid",appId);
            requstHeader.put("timestamp",""+System.currentTimeMillis()/1000);
            requstHeader.put("apisign",apiEncString);

            //发送http请求
            Map<String,String> responseMap = Utils.postForEntity(url,requestString,requstHeader);
            //处理响应报文
            GenerateUtil.handleResponse(responseMap);

            return;

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static  String initRequestParam(){
        WechatRefundQueryVo wechatRefundQueryVo = new WechatRefundQueryVo();
        wechatRefundQueryVo.setMerId("308999115200075");
        wechatRefundQueryVo.setCmbOrderId("003220030617423310398695");
//        wechatRefundQueryVo.setOrderId("");
//        wechatRefundQueryVo.setPlantformNo("");
//        wechatRefundQueryVo.setUserId("");
        return GenerateUtil.initRequestParam(wechatRefundQueryVo);
    }

}
