package com.hamitmizrak.springboot_ecommerce.runners;

import com.hamitmizrak.springboot_ecommerce.business.dto.AddressDto;
import com.hamitmizrak.springboot_ecommerce.business.services.impl.AddressService;
import com.hamitmizrak.springboot_ecommerce.business.services.impl.CustomerService;
import com.hamitmizrak.springboot_ecommerce.data.entity.CustomerEntity;
import com.hamitmizrak.springboot_ecommerce.data.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
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
    private final AddressService iAddressServices;
    private final CustomerService iCustomerServices;
    //private final IOrderServices iOrderServices;
    private final CustomerRepository customerRepository;

    private String[] cityMethod() {
        String[] city = new String[5];
        city[0] = "Malatya";
        city[1] = "Zonguldak";
        city[2] = "İstanbul";
        city[3] = "Ankara";
        city[4] = "Sivas";
        return city;
    }

    // Data set Address
    private void saveAddress() {
        for (int i = 0; i < 5; i++) {
            AddressDto addressDto = new AddressDto();
            addressDto.setCity(cityMethod()[i]);
            addressDto.setState("state"+i);
            addressDto.setStreet("street"+i);
            addressDto.setPostalCode("posta codes"+i);
            iAddressServices.createAddress(addressDto);
        }
    }

    private void saveAddressAndCustomer() {
        //saveAddress();

        /*
        PersonalInfoDto personalInfoDto=new PersonalInfoDto();
        personalInfoDto.setEmail("hamitmizrak@gmail.com");
        personalInfoDto.setFirstName("Hamit");
        personalInfoDto.setLastName("Mızrak");
        personalInfoDto.setTcNumber("123456789");

        CustomerDto customerDto= CustomerDto.builder()
                .address(iAddressServices.getAddressById(1L))
                .personalInfo(personalInfoDto)
                .build();
        iCustomerServices.createCustomer(customerDto);
        */


    }

    private void repositoryQuery() {
        String firstName="hamit",lastName="mizrak";
        CustomerEntity customer = customerRepository.findByPersonalInfo_FirstNameAndPersonalInfoLastName(firstName,lastName);

        if (customer != null) {
            System.out.println("Var: " + customer.  getPersonalInfo().getFirstName() + " " + customer.getPersonalInfo().getLastName());
        } else {
            System.out.println("Yok with firstName: " + firstName + " and lastName: " + lastName);
        }

        // TC NUMBER
        CustomerEntity customer2 = customerRepository.findByPersonalInfoTcNumber("123456789");
        System.out.println(customer2.getPersonalInfo().getTcNumber());

        // CITY
        String city="malatya",  postalCode="44";
        List<CustomerEntity> customers3 = customerRepository.findByAddressCityAndAddressPostalCode(city, postalCode);

        if (!customers3.isEmpty()) {
            customers3.forEach(temp -> {
                System.out.println("Şehirrr: " + temp.getPersonalInfo().getFirstName() + " lives in " + city + " with postal code " + postalCode);
            });
        } else {
            System.out.println("No customers3 found in city44: " + city + " with postal code: " + postalCode);
        }


        /////////
        String tcNumber="123456789";
        CustomerEntity customer4 = customerRepository.findByPersonalInfoTcNumber(tcNumber);

        if (customer4 != null) {
            System.out.println("tcc Customer Found with T.C. Number: " + tcNumber);
        } else {
            System.out.println("No customer4 found with T.C. Number: " + tcNumber);
        }

        ///////////
        // Şehir adına göre müşterileri bul ve konsola yazdır
        String city44="malatya";
        List<CustomerEntity> customers = customerRepository.findByAddressCity(city44);

        if (!customers.isEmpty()) {
            System.out.println("yes Customers in city44: " + city44);
            customers.forEach(customer44 -> {
                System.out.println("Customer: " + customer44.getPersonalInfo().getFirstName() + " " + customer44.getPersonalInfo().getLastName());
            });
        } else {
            System.out.println("No customers found in city44: " + city44);
        }

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
            saveAddressAndCustomer();
        };
    }

    ; //end CommandLineRunner
} //end ECommerceCommandLineRunners





