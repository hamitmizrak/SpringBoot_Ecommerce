package com.hamitmizrak.springboot_ecommerce.business.services.impl;

import com.hamitmizrak.springboot_ecommerce.bean.PasswordEncoderBean;
import com.hamitmizrak.springboot_ecommerce.business.dto.AddressDto;
import com.hamitmizrak.springboot_ecommerce.business.dto.CustomerDto;
import com.hamitmizrak.springboot_ecommerce.business.dto.OrderDto;
import com.hamitmizrak.springboot_ecommerce.business.dto.PersonalInfoDto;
import com.hamitmizrak.springboot_ecommerce.data.embedded.PersonalInfo;
import com.hamitmizrak.springboot_ecommerce.data.entity.AddressEntity;
import com.hamitmizrak.springboot_ecommerce.data.entity.CustomerEntity;
import com.hamitmizrak.springboot_ecommerce.data.entity.OrderEntity;
import com.hamitmizrak.springboot_ecommerce.data.repository.CustomerRepository;
import com.hamitmizrak.springboot_ecommerce.exception._404_NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// LOMBOK
@RequiredArgsConstructor

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    // Password Encoder Bean
    private final PasswordEncoderBean passwordEncoderBean;

   // Email Masking
    public CustomerDto createCustomer(CustomerDto customerDto) {
        customerDto.getPersonalInfo().setEmail(passwordEncoderBean.getPasswordEncoderBeanMethod().encode(customerDto.getPersonalInfo().getEmail()));
        CustomerEntity customerEntity = convertToEntity(customerDto);
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        return convertToDto(savedCustomer);
    }

    public CustomerDto getCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Customer not found with id: " + id));
        return convertToDto(customerEntity);
    }

    public List<CustomerDto> getAllCustomers() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        return customerEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        CustomerEntity existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Customer not found with id: " + id));

        // Gömülü personalInfo güncelleme
        existingCustomer.setPersonalInfo(convertToEntity(customerDto.getPersonalInfo()));
        // Adres güncelleme
        existingCustomer.setAddress(convertToEntity(customerDto.getAddress()));

        CustomerEntity updatedCustomer = customerRepository.save(existingCustomer);
        return convertToDto(updatedCustomer);
    }

    public void deleteCustomer(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Customer not found with id: " + id));
        customerRepository.delete(customerEntity);
    }

    // DTO to Entity dönüşümleri (daha önce tanımladık)
    private CustomerEntity convertToEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setPersonalInfo(convertToEntity(customerDto.getPersonalInfo()));
        customerEntity.setAddress(convertToEntity(customerDto.getAddress()));

        List<OrderEntity> orderEntities = customerDto.getOrders().stream()
                .map(this::convertToEntity).collect(Collectors.toList());
        customerEntity.setOrders(orderEntities);
        orderEntities.forEach(order -> order.setCustomer(customerEntity));

        return customerEntity;
    }

    private CustomerDto convertToDto(CustomerEntity customerEntity) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerEntity.getId());
        customerDto.setPersonalInfo(convertToDto(customerEntity.getPersonalInfo()));
        customerDto.setAddress(convertToDto(customerEntity.getAddress()));

        List<OrderDto> orderDtos = customerEntity.getOrders().stream()
                .map(this::convertToDto).collect(Collectors.toList());
        customerDto.setOrders(orderDtos);

        return customerDto;
    }

    private PersonalInfo convertToEntity(PersonalInfoDto personalInfoDto) {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setFirstName(personalInfoDto.getFirstName());
        personalInfo.setLastName(personalInfoDto.getLastName());
        personalInfo.setEmail(personalInfoDto.getEmail());
        personalInfo.setTcNumber(personalInfoDto.getTcNumber());
        return personalInfo;
    }

    private PersonalInfoDto convertToDto(PersonalInfo personalInfo) {
        PersonalInfoDto personalInfoDto = new PersonalInfoDto();
        personalInfoDto.setFirstName(personalInfo.getFirstName());
        personalInfoDto.setLastName(personalInfo.getLastName());
        personalInfoDto.setEmail(personalInfo.getEmail());
        personalInfoDto.setTcNumber(personalInfo.getTcNumber());
        return personalInfoDto;
    }

    private AddressEntity convertToEntity(AddressDto addressDto) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setCity(addressDto.getCity());
        addressEntity.setState(addressDto.getState());
        addressEntity.setPostalCode(addressDto.getPostalCode());
        return addressEntity;
    }

    private AddressDto convertToDto(AddressEntity addressEntity) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(addressEntity.getId());
        addressDto.setStreet(addressEntity.getStreet());
        addressDto.setCity(addressEntity.getCity());
        addressDto.setState(addressEntity.getState());
        addressDto.setPostalCode(addressEntity.getPostalCode());
        return addressDto;
    }

    private OrderEntity convertToEntity(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNumber(orderDto.getOrderNumber());
        orderEntity.setTotalAmount(orderDto.getTotalAmount());
        return orderEntity;
    }

    private OrderDto convertToDto(OrderEntity orderEntity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setOrderNumber(orderEntity.getOrderNumber());
        orderDto.setTotalAmount(orderEntity.getTotalAmount());
        return orderDto;
    }
}
