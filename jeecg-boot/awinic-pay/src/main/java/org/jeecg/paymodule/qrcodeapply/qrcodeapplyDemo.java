package org.jeecg.paymodule.qrcodeapply;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeecg.paymodule.sm2.SM2Util;
import org.jeecg.paymodule.utils.MD5Utils;
import org.jeecg.paymodule.utils.SignatureUtil;
import org.jeecg.paymodule.utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class qrcodeapplyDemo {

    //测试环境参数：
    //商户号;  3089991074200AF
    //
    //商户名：艾为电子技术股份有限公司
    //
    //收银员：N003290227
    //文档使用聚合支付2.8文档接口就行

    private static String privateKey = "D5F2AFA24E6BA9071B54A8C9AD735F9A1DE9C4657FA386C09B592694BC118B38";

    private static String publicKey = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAE6Q+fktsnY9OFP+LpSR5Udbxf5zHCFO0PmOKlFNTxDIGl8jsPbbB/9ET23NV+acSz4FEkzD74sW2iiNVHRLiKHg==";

//    private static final String url = "https://api.cmburl.cn:8065/polypay/v1.0/mchorders/qrcodeapply";  //UAT
//    private static final String url = "https://api.cmbchina.com/polypay/v1.0/mchorders/qrcodeapply";  //pRD
    private static final String url = "https://api.cmburl.cn:8065/polypay/v1.0/mchorders/onlinepay";


    private static final String appId = "8ab74856-8772-45c9-96db-54cb30ab9f74";

    private static final String appSecret = "5b96f20a-011f-4254-8be8-9a5ceb2f317f";

    private static final String notifyUrl = "https://www.baidu.com";

    public static void main(String[] args){
        // 组装requestBody并加签
        String signResult= signMethod();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String,String> signResultMap = mapper.readValue(signResult, Map.class);

            // 组apiSign加密Map
            Map<String,String> apiSign = new TreeMap<>();
            apiSign.put("appid", appId);
            apiSign.put("secret", appSecret);
            apiSign.put("sign", signResultMap.get("sign"));
            apiSign.put("timestamp", "" + System.currentTimeMillis()/1000);

            // MD5加密
            String MD5Content = SignatureUtil.getSignContent(apiSign);
            String apiSignString = MD5Utils.getMD5Content(MD5Content).toLowerCase();

            // 组request头部Map
            Map<String, String> apiHeader = new HashMap<>();
            apiHeader.put("appid", appId);
            apiHeader.put("timestamp", "" + System.currentTimeMillis()/1000);
            apiHeader.put("apisign", apiSignString);

            // 发送HTTP post请求
            Map<String,String> response = Utils.postForEntity(url, signResult, apiHeader);

            System.out.println(mapper.writeValueAsString(response));
            // 返回结果验签
            Boolean checkResult1 = checkSign(mapper.writeValueAsString(response));
            if(!checkResult1){
                return;
            }

            String success = response.get("returnCode");
            System.out.println("返回结果：" + success);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

    private static String signMethod(){
        Map<String, String> requestPublicParams = new TreeMap<>();
        String requestStr = null;
        try {
            //公共请求参数
            requestPublicParams.put("version", "0.0.1");    //版本号，固定为0.0.1(必传字段)
            requestPublicParams.put("encoding", "UTF-8");   //编码方式，固定为UTF-8(必传)
            requestPublicParams.put("signMethod", "02");
            //业务要素
            Map<String, String> requestTransactionParams = new HashMap<>();
            requestTransactionParams.put("body", "艾为公寓-租金");
            requestTransactionParams.put("currencyCode", "156");    //交易币种，默认156，目前只支持人民币（156）
            requestTransactionParams.put("merId", "3089991074200AF");   //商户号(必传)
            requestTransactionParams.put("notifyUrl", "http://wlhost1.paas.cmbchina.cn/mch_notify/api/notify");  //交易通知地址(必传)
            requestTransactionParams.put("orderId", "AAA20200214AAABA91123123"); //商户订单号(必传)
            requestTransactionParams.put("payValidTime", "2400"); //支付有效时间
//            requestTransactionParams.put("termId", "12345678");  //终端号
            requestTransactionParams.put("spbillCreateIp", "127.0.0.1"); //交易场景
            requestTransactionParams.put("subAppId", "wxa29e77abb6465bda");
            requestTransactionParams.put("subOpenId", "wxa29e77abb6465bda");
            requestTransactionParams.put("tradeScene", "OFFLINE"); //交易场景
            requestTransactionParams.put("tradeType", "JSAPI"); //交易场景
            requestTransactionParams.put("txnAmt", "1");  //交易金额,单位为分(必传)
            requestTransactionParams.put("userId","N003290227");   //收银员
            ObjectMapper mapper = new ObjectMapper();
            requestPublicParams.put("biz_content", mapper.writeValueAsString(requestTransactionParams));

            System.out.println("加签前的报文内容：" + mapper.writeValueAsString(requestPublicParams));


            ObjectMapper objectMapper = new ObjectMapper();


            System.out.println(objectMapper.writeValueAsString(requestPublicParams));

            //对待加签内容进行排序拼接
            String signContent = SignatureUtil.getSignContent(requestPublicParams);
            //加签
            String sign = SM2Util.sm2Sign(signContent,privateKey);
            requestPublicParams.put("sign",sign);


            requestStr = mapper.writeValueAsString(requestPublicParams);

            System.out.println("加签后的报文内容：" + requestStr);
            return requestStr;

        }catch (Exception e){
            System.out.println("加签发生异常！");
            e.printStackTrace();
            return requestStr;
        }
    }

    private static Boolean checkSign(String string){
        System.out.println("要验签的报文内容：" + string);
        try {
            //验签
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> responseBodyMap = objectMapper.readValue(string, Map.class);
            String sign = responseBodyMap.remove("sign");
            String contentStr = SignatureUtil.getSignContent(responseBodyMap);
            boolean result = SM2Util.sm2Check(contentStr,sign,publicKey);

            if (result) {
                System.out.println("报文验签成功!");
            } else {
                System.out.println("报文验签失败!");
            }
            return result;
        }catch (Exception e){
            System.out.println("验签发生异常！");
            e.printStackTrace();
            return false;
        }
    }
}