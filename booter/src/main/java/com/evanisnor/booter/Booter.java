package com.evanisnor.booter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.evanisnor")
public class Booter {

    public static void main(String[] args) {
        SpringApplication.run(Booter.class, args);
    }

}
