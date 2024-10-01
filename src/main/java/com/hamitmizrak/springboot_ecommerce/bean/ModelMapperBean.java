package com.hamitmizrak.springboot_ecommerce.bean;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperBean {

    @Bean
    public ModelMapper getModelMapperBeanMethod() {
        return new ModelMapper();
    }
}
