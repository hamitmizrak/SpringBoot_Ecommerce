package com.hamitmizrak.springboot_ecommerce.audit;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

// LOMBOK
@Log4j2 // for log

// @Configuration: Classın Bean nesnesi olması için kullanıyoruz.
@Configuration
// Auditing Aktif etmek
// @EnableJpaAuditing(auditorAwareRef = "auditorAwareBeanMethod")
public class AuditorAwareBeanClass {

    // Auditor Aware Bean
    // @SpringBootApplication Application aktif etmelisin.
    // @SpringBootApplication => @EnableJpaAuditing(auditorAwareRef = "auditorAwareBeanMethod")
    //
    @Bean
    public AuditorAware<String> auditorAwareBeanMethod(){
        return new AuditorAwareImpl();
    }

}// end class
