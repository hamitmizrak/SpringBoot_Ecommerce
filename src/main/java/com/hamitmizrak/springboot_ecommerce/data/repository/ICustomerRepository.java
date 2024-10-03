package com.hamitmizrak.springboot_ecommerce.data.repository;

import com.hamitmizrak.springboot_ecommerce.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
private String firstName;
private String lastName;
private String email;
private String tcNumber;
*/

// CrudRepository<OrderEntity, Long>
// JpaRepository<OrderEntity, Long>
// PagingAndSortingRepository<OrderEntity, Long>

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

    // İsme Göre Veriyi getir
    //CustomerEntity findByPersonalInfo_FirstName(String firstName);

    // Derived query: First name ve last name'e göre müşteri bul
    //CustomerEntity findByPersonalInfo_FirstNameAndPersonalInfoLastName(String firstName, String lastName);

    // Derived query: Email'e göre müşteri bul
    //CustomerEntity findByPersonalInfoEmail(String email);

    // Derived query: Adres şehrine göre müşteri bul
    //List<CustomerEntity> findByAddressEntity_City(String city);

    // Derived query: Şehir ve postal kod'a göre müşteri bul
    //List<CustomerEntity> findByAddressEntityCityAndAddressPostalCode(String city, String postalCode);

    // Derived query: T.C. numarasına göre müşteri bul
    //CustomerEntity findByPersonalInfoTcNumber(String tcNumber);

    // JOIN JPQL
}
