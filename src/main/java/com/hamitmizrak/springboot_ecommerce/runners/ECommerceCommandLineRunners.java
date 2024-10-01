package com.hamitmizrak.springboot_ecommerce.runners;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// LOMBOK
@Log4j2

@Configuration
public class ECommerceCommandLineRunners {

    @Bean
    public CommandLineRunner getCommandLineRunner() {
        return args -> {
            log.info("Command Line Runner Çalıştı");
            System.out.println("Command Line Runner Çalıştı");
        };
    }; //end CommandLineRunner
} //end ECommerceCommandLineRunners
