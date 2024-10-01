package com.hamitmizrak.springboot_ecommerce.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;
// Ortak kolonları buraya ekliyoruz.
@MappedSuperclass
@JsonIgnoreProperties(value = {"created_date","last_date"},allowGetters = true) // Frontend gitmeyecek kolonlar
abstract public class BaseEntity implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
    private Long customerId;

    // AUDITING
    // Kim Ekledi
    @CreatedBy
    @Column(name = "created_user")
    private String createdUser;

    // Kim Ne Zaman Ekledi
    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    // Kim Güncelledi
    @LastModifiedBy
    @Column(name = "last_user")
    private String lastUser;

    // Kim Ne Zaman Güncelledi
    @LastModifiedDate
    @Column(name = "last_date")
    private Date lastDate;

    // DATE
    @CreationTimestamp
    //@Temporal(TemporalType.DATE) // yıl ay gün
    //@Temporal(TemporalType.TIME) // saat dakika saniye
    @Temporal(TemporalType.TIMESTAMP) // yıl ay gün saat dakika saniye
    private Date systemDate = new Date(System.currentTimeMillis());
}
