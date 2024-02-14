package com.example.playerprofileservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerProfileConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
