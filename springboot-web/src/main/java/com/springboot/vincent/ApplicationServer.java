package com.springboot.vincent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by weix on 2018/3/5.
 */
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com.")
@PropertySource(value = "classpath:application.properties")
public class ApplicationServer {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        SpringApplication.run(ApplicationServer.class, args);
        long end = System.currentTimeMillis();
        System.out.println("Server start use:" +(end - start)+" ms!");
    }
}
