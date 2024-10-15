package com.hamitmizrak.springboot_ecommerce.error;

import lombok.RequiredArgsConstructor;
import com.hamitmizrak.springboot_ecommerce.utils.frontend.ReactFrontend;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LOMBOK
@Log4j2 // Loglama
@RequiredArgsConstructor

// LOMBOK
// Spring Boot defaulttan gelen error'ı kendimize göre customise yapıyoruz.
@RestController
@CrossOrigin(origins = ReactFrontend.PRODUCTION_REACT_FRONTEND_PORT_URL)

// Spring Framework hata mesajları bazen yok yorucu olabilir ancak biz /error ile ilgili hatalı yakalayıp
// daha sade bir hata mesajı dönderebiliriz.
// pring Boot'ta özelleştirilmiş bir hata yönetimi (error handling) metodunu göstermektedir.
// Kod, bir HTTP isteği sırasında meydana gelen hataları yakalar ve kullanıcıya özel hata mesajları veya bilgileri döner.
// Özellikle, Spring Boot 2.3 ve sonrası için uygun bir hata yönetimi yapılandırması içerir.
// Aşağıda kodun adım adım açıklamasını bulabilirsiniz:
public class CustomErrorHandleWebRequest implements ErrorController {

    // Field Injection (1.YOL)
    /*
    @Autowired
    private ErrorAttributes errorAttributes;
    */

    // Constructor Injection (2.YOL)
    /*
    private final ErrorAttributes errorAttributes;
    // NOT: Eğer 1 tane attributes varsa @Autowired zorunda değilsiz
    @Autowired
    public CustomErrorHandleWebRequest(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }
    */

    // Lombok Constructor Injection (3.YOL)
    private final ErrorAttributes errorAttributes;

    // Field
    // sem  pvc
    // s m  pv
    private ApiResult apiResult;
    private String path;
    private String message;
    private Integer status;
    private Map<String, String> validationErrors;

    // http://localhost:4444/error
    // Spring Frameworkten gelen hataları kendimize göre hataları belirledik (ApiResult)
    @RequestMapping("/error")
    public ApiResult handleErrorMethod(WebRequest webRequest){
        // Spring >=2.3
        // getErrorAttributes: Bu metod, meydana gelen hatanın ayrıntılarını döner.
        // Örneğin, hata mesajı, hata türü, hata zamanı gibi bilgileri sağlar.
        // Bu bilgileri bir Map<String,Object> olarak döner, yani anahtar-değer çiftlerinden oluşan bir yapıdır.
        // Bu attributes haritası, meydana gelen hata ile ilgili bilgileri içerir ve daha sonra kullanıcılara gösterilecek ya da loglanacak veriler buradan elde edilir.
        Map<String,Object> attributes=errorAttributes.getErrorAttributes(
                webRequest,
                ErrorAttributeOptions
                        .of(ErrorAttributeOptions.Include.MESSAGE,ErrorAttributeOptions.Include.BINDING_ERRORS)
        ); //end attributes

        // Spring'ten verileri almak
        // smp
        status= (Integer) attributes.get("status");
        message= (String) attributes.get("message");
        path= (String) attributes.get("path");
        // Parametreli Constructor (pms)
        apiResult= new ApiResult(path,message,status);

        // attributes error varsa
        // v
        if(attributes.containsKey("errors")){
            List<FieldError> fieldErrorList= (List<FieldError>) attributes.get("errors");
            validationErrors=new HashMap<>();
            // for each dongu
            for(FieldError fieldError : fieldErrorList){
                validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            apiResult.setValidationErrors(validationErrors);
        }
        return apiResult;
    } //end handleErrorMethod
} // end CustomErrorHandleWebRequest

