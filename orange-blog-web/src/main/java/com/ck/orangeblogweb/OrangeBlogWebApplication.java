package com.ck.orangeblogweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.ck.*.mapper"})
@ComponentScan(basePackages={"com.ck"})
public class OrangeBlogWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrangeBlogWebApplication.class, args);
    }

}
