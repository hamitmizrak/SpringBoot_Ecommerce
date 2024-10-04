package com.hamitmizrak.springboot_ecommerce.runners;

import com.hamitmizrak.springboot_ecommerce.business.dto.AddressDto;
import com.hamitmizrak.springboot_ecommerce.business.dto.CustomerDto;
import com.hamitmizrak.springboot_ecommerce.business.dto.RegisterDto;
import com.hamitmizrak.springboot_ecommerce.business.services.IAddressServices;
import com.hamitmizrak.springboot_ecommerce.business.services.ICustomerServices;
import com.hamitmizrak.springboot_ecommerce.business.services.IRegisterServices;
import com.hamitmizrak.springboot_ecommerce.business.services.IRoleService;
import com.hamitmizrak.springboot_ecommerce.data.entity.CustomerEntity;
import com.hamitmizrak.springboot_ecommerce.data.entity.RoleEntity;
import com.hamitmizrak.springboot_ecommerce.data.repository.CustomerRepository;
import com.hamitmizrak.springboot_ecommerce.data.repository.IRegisterRepository;
import com.hamitmizrak.springboot_ecommerce.data.repository.IRoleRepository;
import com.hamitmizrak.springboot_ecommerce.role.ERole;
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
    //private final IAddressServices iAddressService;
    //private final ICustomerServices iCustomerServices;
    //private final IOrderServices iOrderServices;
    private final CustomerRepository customerRepository;


    // Injection
    private final IRoleRepository iRoleRepository;
    private final IRoleService iRoleService;

    private final IRegisterRepository iRegisterRepository;
    private final IRegisterServices iRegisterServices;

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

    // Role ve Register Ekleme
    private void roleAndRegisterCreate(){
        //log.info("role And Register Create");
        System.out.println("role And Register Create");
        synchronized (this){
            Long adminRoleID= iRoleRepository.save(RoleEntity.builder().roleId(0L).roleName(ERole.ADMIN.toString()).build()).getRoleId();
            Long writerRoleID= iRoleRepository.save(RoleEntity.builder().roleId(0L).roleName(ERole.WRITER.toString()).build()).getRoleId();
            Long userRoleID= iRoleRepository.save(RoleEntity.builder().roleId(0L).roleName(ERole.USER.toString()).build()).getRoleId();

            // Thread
            Thread thread= new Thread(new Runnable() {
                @Override
                public void run() {

                }
            });

            for (long i = 1; i <=1 ; i++) {
                // REGISTER
                RegisterDto registerDto=new RegisterDto();
                registerDto.setRegisterNickName("nickname"+i);
                registerDto.setRegisterName("name"+i);
                registerDto.setRegisterSurname("surname"+i);
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("email").append("@gmail.com");
                registerDto.setRegisterEmail(stringBuilder.toString());
                registerDto.setRegisterPassword("Java12345@.");

                // USER DETAILS
                registerDto.setIsEnabled(true);
                registerDto.setIsCredentialsNonExpired(true);
                registerDto.setIsAccountNonExpired(true);
                registerDto.setIsAccountNonLocked(true);

                // KAYDET
                iRegisterServices.registerServiceCreate(i,registerDto);
                System.out.println("EKLENDI");
            }

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

            roleAndRegisterCreate();
        };
    }

    ; //end CommandLineRunner
} //end ECommerceCommandLineRunners





