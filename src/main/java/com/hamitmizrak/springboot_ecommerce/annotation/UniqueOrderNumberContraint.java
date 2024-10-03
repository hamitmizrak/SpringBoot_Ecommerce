package com.hamitmizrak.springboot_ecommerce.annotation;

import com.hamitmizrak.springboot_ecommerce.data.repository.OrderRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

// lombok
@RequiredArgsConstructor

public class UniqueOrderNumberContraint implements ConstraintValidator<UniqueOrderNumber, String> {

    // INJECTION
    private final OrderRepository orderRepository;

    @Override
    public void initialize(UniqueOrderNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String orderNumber, ConstraintValidatorContext constraintValidatorContext) {
        Boolean isOrderNumber=orderRepository.findByOrderNumber(orderNumber).isPresent();
        System.out.println(isOrderNumber);
        System.out.println(orderRepository.findByOrderNumber(orderNumber).isPresent());
        if(isOrderNumber){
            return false;
        }
        return true;
    }
}
