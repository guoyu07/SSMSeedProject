package com.github.izhangzhihao.SSMSeedProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@EnableAsync(proxyTargetClass = true)
@EnableAspectJAutoProxy
@EnableCaching
@SpringBootApplication(scanBasePackages = "com.github.izhangzhihao.SSMSeedProject")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
