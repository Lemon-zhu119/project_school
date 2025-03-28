package org.one.afternoon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("org.one.afternoon.mapper")
public class AfternoonApplication {

    public static void main(String[] args) {
        SpringApplication.run(AfternoonApplication.class, args);
    }

}
