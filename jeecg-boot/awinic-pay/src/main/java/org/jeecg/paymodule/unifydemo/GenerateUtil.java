package org.jeecg.paymodule.unifydemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.DemoConstant;
import org.jeecg.paymodule.pojo.Pojo;
import org.jeecg.paymodule.utils.SignatureUtil;

import java.util.HashMap;
import java.util.Map;

//构造请求报文工具类
@Slf4j
public class GenerateUtil {

    //商户私钥
    private static final String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCc540quYC9xzCMZeFOe8UmE3W5LWrqFd/2DDSHQASxq8vmOiwFRSG2hsVXtjfmNLQNhtpTR0SGDUjkCsx+SJH0JDnOfQ2xXHasO65Rnv2wrHs64P6U0aUrMWjgapjkmLwzRV12AKNAX77MGIocpcB0KZhk+0AVc6oQCBybV65JTGu+pAyFLMJRtIP5kH3VMuXmig6VeiZAsjEewD/emxgK3cXejMQvqlNYFnCLsZ7ovAhr+bhz6SHkOws3p80O6zfQbKfLzdSVaZK8FnwNPznUxAK77bRZN0zF3V9mL9+zrarvFPD5VkcVHNLj0DRzLmr2c5TbiCigs4+I+NMfhpoLAgMBAAECggEAbM8GzoImDXV87WAZhtu+NFF6ahhc9EiHL5H3O3PhzXRdyiK9NEpkvrdnUxRCX5pc4qSJ8waRNoUv7zSt60VYMf6NN+zw+fYtNfONR30CYOq76nDtGzbnW7TADiDeNmjU2plX3uVCUPoUzmSWIpevht7xl9XE8xtq7AM0E2YSrzEADcxtqQslM0uVOf+ki1eu0/OwCz13FzPlPtnDwt2Lw9xxCxWqTgpN4oD5m6EWTqbognUIJ0EFD0dHXjrYnHXc+/Za5e+CDXYApHuhR9bifa1e4HMN084oLY+rkSXUV3+Te0APPCfbeEeqvubziDmKxxKaWUq1wPbYi4c06ZQdgQKBgQDhF7zDWgiJFTgrLGmExJRKiR/3QZN4sugYE1itdRDJmiPV4xhWPXSsND3WtqR5+0otb/hbzRa3cyl/RXV/1ZmBbE46fX2DKnmLQ1gP74iOuqWpfxjh/qpk+3kEY9aP57le/O0QEEPsJmqCsGM7XnzfNsxGAFYaDHooRbcGtv++AwKBgQCycuvRUQjV4dxTuRJuwFbmdq4odSBMu7yCS4i5I9I73d3TGZBWfiXQWFmuiPh+pf3HdvMbgyA243Uv/NGapSmNvARXm0/eEyfTxV7+GVdwLf3sSe8DQMCR1eJA9VzuS+jhCrHkFgyW3V/3ki66W8YITENlgC+VebOatfFE8i/ZWQKBgQCZ2VmhxFX1LFW53J86qgoZb+QzYdTkOJQ+cGq6FDunL/2yYYfu2g527TYfHbMJ1OH8cH22cVVHiiUg4l7PQzWqqlZF0CQLlOqCb0MvkS8rLxOv6DkfrqrUXrV2dK7gqSegbwuxYQyryg4eyWTp3UlIX/H7Hpu7LjAIeq4Anu/p9QKBgFMtpiYHM6segGi2F5VwKhF6uGs7TTb3O0MwmiZSQCiPnlpLzC/E1TNsO0FTryC5lrVnCKKGWHm9RF595eXDnr7mKM/9IRlOrH3VvhWLEmrDxVxiifpmMFzJ6ZCFzi91SrO7HHhIns2jmpv3k7hiFsi/Y5roSUXPWJyAull82jjhAoGAaKujjF4HL91UXZFetkkKiBIpIrH5+XbiX9z7H9/Tv8NSy/zTvXp3hFl3dr9gO722i/96dTq4th23Gqtih4cA9x8Wd7RChR9yAK/ffSj1lW6RhBWj1j2JCPFCm1TJD5iO3bIeuHm2sAuafKKoWT/VCUkKRwt9Wwh9yF20vMQ3kFw=";
    //招行公钥
    private static final String cmbPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjutZyxP2L9eYM6DhZ11jk5lZieyyA6Wsr4baAU7PT+E0fv3KlERoh0edHLsLVff2I4AzuEqSoKDywKIBw1aSkIXGAaESj/FzA/V1jtmorq1RpPFmaqAOGDocMiaqukBBemwFnsYrTegsZUf88fU7KujwEMffLhhpwnM/Vf0NJ2s3ZwEZCgPWDa5cm1YpMLgopzc5HozENI5K9VFL92ThjHiTiutE28Bpi2xgSt6Cx+S8Nxqhy6/r/YVxvfgP66YCccnWOObN3fWo5TXepP6uBReTwjqNajlcSC5JqINqUUEAqief87y3NAFKRbE7Bu312y6zqcJgC/TIrWLXXB1/XQIDAQAB";


    public static String initRequestParam(Pojo pojo){

        //设置公共请求参数
        Map<String,String> resultPublicParam = new HashMap<>();
        resultPublicParam.put(DemoConstant.VERSION,DemoConstant.VERSION_VALUE);
        resultPublicParam.put(DemoConstant.ENCODING,DemoConstant.ENCODING_VALUE);
        resultPublicParam.put(DemoConstant.SIGN_METHOD,DemoConstant.SIGN_METHOD_VALUE);

        //初始化业务请求参数

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String bizContent = objectMapper.writeValueAsString(pojo);
            System.out.println(bizContent);
            resultPublicParam.put(DemoConstant.BIZ_CONTENT,bizContent);

            //对待加签内容进行排序拼接
            String signStr = SignatureUtil.getSignContent(resultPublicParam);
            //加签
            String sign = SignatureUtil.rsaSign(signStr,privateKey,DemoConstant.UTF_8);

            resultPublicParam.put(DemoConstant.SIGN,sign);

            String requestStr  = objectMapper.writeValueAsString(resultPublicParam);
            log.info("加签后的报文内容：" + requestStr);
            return requestStr;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 处理响应报文
     * @param responseMap
     * @return
     */
    public static void handleResponse(Map<String,String> responseMap){

        if( null == responseMap){
            log.equals("响应的内容为空");
            return ;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            log.info("收到响应的内容为：" + objectMapper.writeValueAsString(responseMap));
            //验签
            String sign = responseMap.remove(DemoConstant.SIGN);
            //生成验签字符串
            String signStr = SignatureUtil.getSignContent(responseMap);
            boolean flag = SignatureUtil.rsaCheck(signStr,sign,cmbPubKey,DemoConstant.UTF_8);
            if( flag ){
                log.info("验签成功！");
            } else {
                log.error("验签失败！");
            }
            return;

        }catch (Exception ex){
            log.equals("系统异常");
            return;
        }
    }


}
