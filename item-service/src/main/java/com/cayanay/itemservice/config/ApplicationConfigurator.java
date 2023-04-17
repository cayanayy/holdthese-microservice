package com.cayanay.itemservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfigurator {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
