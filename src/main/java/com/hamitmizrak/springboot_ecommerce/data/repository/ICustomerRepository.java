package com.hamitmizrak.springboot_ecommerce.data.repository;

import com.hamitmizrak.springboot_ecommerce.data.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// CrudRepository<CustomerEntity,Long>
// JpaRepository<CustomerEntity,Long>
// PagingAndSortingRepository<CustomerEntity,Long>

/*
private Long customerId;
private String customerName;
private String customerSurname;
private String customerTcNumber;
private String customerVatNumber;
private String customerNotes;
private String customerGender;
private String customerEmail;
private Date systemDate
 */

@Repository
public interface ICustomerRepository extends CrudRepository<CustomerEntity,Long> {
    // Delivered Query

    // isimler müşterileri bulmak
    List<CustomerEntity> findByCustomerName(String customerName);

    // Email ile Müşteri bulmak
    CustomerEntity findByCustomerEmail(String customerEmail);

    // Tc kimlik numarasına göre müşteri bulmak

    // Şehirdeki bütün müşterileri getir

    // isim ve soyisime göre verileri getir.

    // JPQL
    // Native Query
    // JDBC Template
}