package com.hamitmizrak.springboot_ecommerce.data.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id",unique = true,nullable = false,insertable = true,updatable = false)
    private Long customerId;

    // DATE
    @CreationTimestamp
    //@Temporal(TemporalType.DATE) // yıl ay gün
    //@Temporal(TemporalType.TIME) // saat dakika saniye
    @Temporal(TemporalType.TIMESTAMP) // yıl ay gün saat dakika saniye
    private Date createdDate = new Date(System.currentTimeMillis());
}
