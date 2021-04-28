package org.jeecg.paymodule.orderquery;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeecg.paymodule.utils.SignatureUtil;
import org.jeecg.paymodule.utils.Utils;
import org.jeecg.paymodule.utils.MD5Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class orderqueryDemo {

    private static final String url = "https://api.cmburl.cn:8065/polypay/v1.0/mchorders/orderquery";

    private static final String appId = "8ab74856-8772-45c9-96db-54cb30ab9f74";

    private static final String appSecret = "5b96f20a-011f-4254-8be8-9a5ceb2f317f";


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

            // 返回结果验签
            Boolean checkResult1 = checkSign(mapper.writeValueAsString(response));
            if(!checkResult1){
                return;
            }

            String success = response.get("returnCode");
            System.out.println("返回结果：" + success);
        } catch (Exception e) {
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
            requestPublicParams.put("signMethod", "01");    //签名方法，固定为01，表示签名方式为RSA2(必传)
            //业务要素
            Map<String, String> requestTransactionParams = new HashMap<>();
            requestTransactionParams.put("merId", "3089991074200AF");   //商户号(必传)
            requestTransactionParams.put("cmbOrderId", "100419070210595371147326");  //平台订单号
            requestTransactionParams.put("orderId", "1562036392778"); //商户订单号(必传)
            requestTransactionParams.put("userId", "N003290227");   //收银员(必传)
            ObjectMapper mapper = new ObjectMapper();
            requestPublicParams.put("biz_content", mapper.writeValueAsString(requestTransactionParams));

            System.out.println("加签前的报文内容：" + mapper.writeValueAsString(requestPublicParams));

            //私钥
            String privateKey = "D5F2AFA24E6BA9071B54A8C9AD735F9A1DE9C4657FA386C09B592694BC118B38";
            //对待加签内容进行排序拼接
            String signContent= SignatureUtil.getSignContent(requestPublicParams);
            //加签
            requestPublicParams.put("sign", SignatureUtil.rsaSign(signContent, privateKey, "UTF-8"));

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
            //公钥
            String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjutZyxP2L9eYM6DhZ11jk5lZieyyA6Wsr4baAU7PT+E0fv3KlERoh0edHLsLVff2I4AzuEqSoKDywKIBw1aSkIXGAaESj/FzA/V1jtmorq1RpPFmaqAOGDocMiaqukBBemwFnsYrTegsZUf88fU7KujwEMffLhhpwnM/Vf0NJ2s3ZwEZCgPWDa5cm1YpMLgopzc5HozENI5K9VFL92ThjHiTiutE28Bpi2xgSt6Cx+S8Nxqhy6/r/YVxvfgP66YCccnWOObN3fWo5TXepP6uBReTwjqNajlcSC5JqINqUUEAqief87y3NAFKRbE7Bu312y6zqcJgC/TIrWLXXB1/XQIDAQAB";
            //验签
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> responseBodyMap = objectMapper.readValue(string, Map.class);
            String sign = responseBodyMap.remove("sign");
            String contentStr = SignatureUtil.getSignContent(responseBodyMap);
            boolean result = SignatureUtil.rsaCheck(contentStr, sign, publicKey, "UTF-8");

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
