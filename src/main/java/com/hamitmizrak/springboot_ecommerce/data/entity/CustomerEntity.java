package com.hamitmizrak.springboot_ecommerce.data.entity;

import com.hamitmizrak.springboot_ecommerce.data.embedded.EmbeddableCustomerEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data // Getter,Setter,Equals, hashCode
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
@NoArgsConstructor //parametresiz constructor
@AllArgsConstructor //parametresiz constructor
@Builder
@Log4j2

//Customer(1)  ---- Address(1)
@Entity(name = "Customers")
@Table(name = "customer")
public class CustomerEntity extends BaseEntity implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID = 1L;

    // Field
    // Embedded
    @Embedded
    EmbeddableCustomerEntity embeddableCustomerEntity = new EmbeddableCustomerEntity();

    // Parametreli Constructor içinde çağırmamız lazım
    public CustomerEntity(EmbeddableCustomerEntity embeddableCustomerEntity) {
        this.embeddableCustomerEntity = embeddableCustomerEntity;
    }
} //end class CustomerDto
