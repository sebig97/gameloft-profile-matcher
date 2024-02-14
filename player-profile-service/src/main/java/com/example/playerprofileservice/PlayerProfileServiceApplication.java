package com.example.playerprofileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PlayerProfileServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayerProfileServiceApplication.class, args);
    }

}
