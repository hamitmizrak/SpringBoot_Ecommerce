package com.hamitmizrak.springboot_ecommerce.business.dto;

import com.hamitmizrak.springboot_ecommerce.audit.AuditingAwareBaseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Getter
@Setter
@ToString

// BaseDTO
abstract public class BaseDto extends AuditingAwareBaseDto implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID=1L;

    // ID
    protected Long id;

    @Builder.Default
    protected  Date systemCreatedDate=new Date(System.currentTimeMillis());

}
