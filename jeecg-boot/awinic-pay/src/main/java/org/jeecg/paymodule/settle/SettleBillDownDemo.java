package org.jeecg.paymodule.settle;

import org.jeecg.DemoConstant;
import org.jeecg.paymodule.pojo.Pojo;
import org.jeecg.paymodule.unifydemo.GenerateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeecg.paymodule.utils.MD5Utils;
import org.jeecg.paymodule.utils.SignatureUtil;
import org.jeecg.paymodule.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class SettleBillDownDemo {

    private static final String url = "https://api.cmburl.cn:8065/hou/apiuat/bill/settleBillDownload";

    private static final String appId = "8ab74856-8772-45c9-96db-54cb30ab9f74";

    private static final String appSecret = "5b96f20a-011f-4254-8be8-9a5ceb2f317f";

//    测试环境报文头MD5加签秘钥（生产环境在聚合收款服务平台获取）：
//    测试环境APP ID：8ab74856-8772-45c9-96db-54cb30ab9f74
//    测试环境APP SECRET：5b96f20a-011f-4254-8be8-9a5ceb2f317f
//
//
//    测试环境报文体RSA2加签秘钥（生产环境在聚合收款服务平台设置和获取）：
//    商户私钥（商户端联调测试加签使用）：
//    D5F2AFA24E6BA9071B54A8C9AD735F9A1DE9C4657FA386C09B592694BC118B38
//
//    招行公钥（商户端联调测试验签使用）：
//    MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAE6Q+fktsnY9OFP+LpSR5Udbxf5zHCFO0PmOKlFNTxDIGl8jsPbbB/9ET23NV+acSz4FEkzD74sW2iiNVHRLiKHg==


    public static void main(String args[]){
        //初始化请求参数
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

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    private static String initRequestParam(){
        //初始化请求参数
        BillUrlVo billUrlVo = new BillUrlVo();
        billUrlVo.setMerId("308999115200075");
        billUrlVo.setBillDate("20200331");
//        billUrlVo.setPlantfromNo("");
        return GenerateUtil.initRequestParam(billUrlVo);
    }
}

class BillUrlVo extends Pojo {
    String merId;

    String plantformNo;

    String billDate;

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getPlantformNo() {
        return plantformNo;
    }

    public void setPlantformNo(String plantformNo) {
        this.plantformNo = plantformNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
}
