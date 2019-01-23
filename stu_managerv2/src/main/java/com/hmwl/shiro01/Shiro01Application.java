package com.hmwl.shiro01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.hmwl")
@MapperScan("com.hmwl.dao")
public class Shiro01Application {

    public static void main(String[] args) {
        SpringApplication.run(Shiro01Application.class, args);
    }

}

