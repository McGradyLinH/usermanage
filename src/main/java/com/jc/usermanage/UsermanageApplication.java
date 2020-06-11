package com.jc.usermanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.jc.usermanage.dao")
public class UsermanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsermanageApplication.class, args);
    }

}
