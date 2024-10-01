package com.hamitmizrak.springboot_ecommerce.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Getter
@Setter
@ToString


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
    protected Long customerId;

    // AUDITING
    // Kim Ekledi
    @CreatedBy
    @Column(name = "created_user")
    protected String createdUser;

    // Kim Ne Zaman Ekledi
    @CreatedDate
    @Column(name = "created_date")
    protected Date createdDate;

    // Kim Güncelledi
    @LastModifiedBy
    @Column(name = "last_user")
    protected String lastUser;

    // Kim Ne Zaman Güncelledi
    @LastModifiedDate
    @Column(name = "last_date")
    protected Date lastDate;

    // DATE
    @CreationTimestamp
    //@Temporal(TemporalType.DATE) // yıl ay gün
    //@Temporal(TemporalType.TIME) // saat dakika saniye
    @Temporal(TemporalType.TIMESTAMP) // yıl ay gün saat dakika saniye
    protected Date systemDate = new Date(System.currentTimeMillis());
}
