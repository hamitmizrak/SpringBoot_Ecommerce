package com.hamitmizrak.springboot_ecommerce.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Map;

// LOMBOK
@Data
@Builder
@AllArgsConstructor

// JSON NOT NULL
public class ApiResult {

    // sem pvc
    private Integer status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> validationErrors;
    private Date createdDate;

    // Parametresiz Constructor
    public ApiResult() {
    }

    // Parametreli Constructor (pms)
    public ApiResult(String path, String message, Integer status) {
        this.path = path;
        this.message = message;
        this.status = status;
    }

    // Parametreli Constructor (pmes)
    public ApiResult(String path, String message,String error, Integer status) {
        this.path = path;
        this.message = message;
        this.error = error;
        this.status = status;
    }
} //end ApiResult
