package com.shuai;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DeamApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeamApplication.class,args);
       /* SpringApplication springApplication=new SpringApplication(DeamApplication.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);*/
    }
}
