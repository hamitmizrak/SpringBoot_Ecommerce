package com.hamitmizrak.springboot_ecommerce.runners;

import com.hamitmizrak.springboot_ecommerce.business.dto.AddressDto;
import com.hamitmizrak.springboot_ecommerce.business.dto.CustomerDto;
import com.hamitmizrak.springboot_ecommerce.business.services.IAddressServices;
import com.hamitmizrak.springboot_ecommerce.business.services.ICustomerServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
    CommandLineRunner, Spring Boot'ta uygulama başladıktan hemen sonra çalıştırılmak üzere tasarlanmış bir @FunctionalInterface'tir.
    Spring Boot uygulaması başlatıldığında CommandLineRunner arayüzünü implement eden herhangi bir sınıf, run metodunu içerir ve
    bu metot uygulamanın başlatılması tamamlandığında otomatik olarak çalıştırılır.

    CommandLineRunner genellikle:
    Uygulamanın başlangıç aşamasında veri yüklemek,
    Bir defalık konfigürasyonlar yapmak,
    Uygulama başlatılırken belli işlemleri tetiklemek (örneğin, bir log mesajı yazdırmak veya dış sistemlerle entegrasyon kurmak) gibi işlemler için kullanılır.
    CommandLineRunner Nasıl Kullanılır?
    CommandLineRunner arayüzünü kullanmak için genellikle Spring Boot uygulamanıza bir @Bean olarak eklenir ya da bir sınıf bu arayüzü implement eder.
    Uygulama başlatıldığında, örnek veri setleri veya başlangıç verilerini veritabanına eklemek için kullanılabilir.
    Bu, özellikle test veya demo ortamlarında çok faydalıdır.
     */

// LOMBOK
@Log4j2
@RequiredArgsConstructor

@Configuration
public class ECommerceCommandLineRunners {

    // Injection
    //private final IAddressServices iAddressService;
    //private final ICustomerServices iCustomerServices;
    //private final IOrderServices iOrderServices;

    private String[] cityMethod() {
        String[] city = new String[5];
        city[0] = "Malatya";
        city[1] = "Zonguldak";
        city[2] = "İstanbul";
        city[3] = "Ankara";
        city[4] = "Sivas";
        return city;
    }

    // Data set
    private void saveAddress() {
        for (int i = 0; i < 5; i++) {
            /*
            AddressDto addressDto = AddressDto.builder()
                    .city(cityMethod()[i])
                    .country("Türkiye")
                    .street("cadde" + i)
                    .postalCode("12345" + i)
                    .build();
            */
            //iAddressService.addressServiceCreate(addressDto);
        }
    }

    private void saveAddressCustomer() {


    }


    @Bean
    public CommandLineRunner getCommandLineRunner() {
        return args -> {
            log.info("Command Line Runner Çalıştı");
            System.out.println("Command Line Runner Çalıştı");

            // ADDRESS
            //saveAddress();

            // CUSTOMER
            //saveAddressCustomer();
        };
    }

    ; //end CommandLineRunner
} //end ECommerceCommandLineRunners
