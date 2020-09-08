package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zyl
 */
@SpringBootApplication
@EnableScheduling
public class BuyshoesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuyshoesApplication.class, args);
    }

}
