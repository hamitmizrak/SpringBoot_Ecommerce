package com.hamitmizrak.springboot_ecommerce.data.repository;

import com.hamitmizrak.springboot_ecommerce.data.entity.CustomerEntity;
import com.hamitmizrak.springboot_ecommerce.data.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByPersonalInfo_FirstName(String firstName);
    // Derived query: First name ve last name'e göre müşteri bul
    CustomerEntity findByPersonalInfo_FirstNameAndPersonalInfoLastName(String firstName, String lastName);

    // Derived query: Email'e göre müşteri bul
    CustomerEntity findByPersonalInfoEmail(String email);

    // Derived query: Adres şehrine göre müşteri bul
    List<CustomerEntity> findByAddressCity(String city);

    // Derived query: Şehir ve postal kod'a göre müşteri bul
    List<CustomerEntity> findByAddressCityAndAddressPostalCode(String city, String postalCode);

    // Derived query: T.C. numarasına göre müşteri bul
    CustomerEntity findByPersonalInfoTcNumber(String tcNumber);

}
