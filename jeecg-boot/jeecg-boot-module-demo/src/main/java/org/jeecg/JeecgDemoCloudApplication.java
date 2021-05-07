package org.jeecg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"org.jeecg"})
public class JeecgDemoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(JeecgDemoCloudApplication.class, args);
    }
}
