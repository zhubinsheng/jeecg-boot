package org.jeecg.modules.pdf.src.com.sign;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Scanner;


public class GetPkcs implements Pkcs {

    public static String filePath = "D:\\Users\\zhubinsheng\\Documents\\GitHub\\jeecg-boot\\jeecg-boot\\jeecg-boot-module-demo\\src\\main\\java\\org\\jeecg\\modules\\pdf\\file";


    public static void main(String[] args) throws Exception{
        String issuerStr ="CN=西部CA,OU=研发部,O=gitbook有限公司,C=CN,E=gitbook@sina.com,L=银川,ST=宁夏";
        String subjectStr ="CN=西部CA,OU=研发部,O=gitbook有限公司,C=CN,E=gitbook@sina.com,L=银川,ST=宁夏";
        String certificateCRL="https://www.cwca.com.cn";
        System.out.println("请输入密码");
        Scanner pwd=new Scanner(System.in);
        String password = pwd.nextLine();
       Map<String,byte[]> result = Pkcs.createCert(password,issuerStr,subjectStr,certificateCRL);
        FileOutputStream outPutStream = new FileOutputStream(filePath+"\\keystore.p12"); // ca.jks
        outPutStream.write(result.get("keyStoreData"));
        outPutStream.close();
        FileOutputStream fos = new FileOutputStream(new File(filePath+"\\keystore.cer"));
        fos.write(result.get("certificateData"));
        fos.flush();
        fos.close();
    }

}
