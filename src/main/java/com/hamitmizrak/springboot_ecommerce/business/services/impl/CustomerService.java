package com.hamitmizrak.springboot_ecommerce.business.services.impl;

import com.hamitmizrak.springboot_ecommerce.bean.PasswordEncoderBean;
import com.hamitmizrak.springboot_ecommerce.business.dto.AddressDto;
import com.hamitmizrak.springboot_ecommerce.business.dto.CustomerDto;
import com.hamitmizrak.springboot_ecommerce.business.dto.OrderDto;
import com.hamitmizrak.springboot_ecommerce.business.dto.PersonalInfoDto;
import com.hamitmizrak.springboot_ecommerce.data.embedded.EmbeddableCustomer;
import com.hamitmizrak.springboot_ecommerce.data.entity.AddressEntity;
import com.hamitmizrak.springboot_ecommerce.data.entity.CustomerEntity;
import com.hamitmizrak.springboot_ecommerce.data.entity.OrderEntity;
import com.hamitmizrak.springboot_ecommerce.data.repository.ICustomerRepository;
import com.hamitmizrak.springboot_ecommerce.exception._404_NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// LOMBOK
@RequiredArgsConstructor

@Service
public class CustomerService {

    private final ICustomerRepository  customerRepository;
    private final PasswordEncoderBean passwordEncoderBean;

    // Email Masking
    public CustomerDto createCustomer(CustomerDto customerDto) {
        // Embedded: getPersonalInfo
        // Bcrypted
        customerDto.getPersonalInfo().setEmail(passwordEncoderBean.getPasswordEncoderBeanMethod().encode(customerDto.getPersonalInfo().getEmail()));
        CustomerEntity customerEntity = convertToEntity(customerDto);
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        return convertToDto(savedCustomer);
    }

    public CustomerDto getCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Customer not found with id: " + id));
        //
        //OrderEntity orderEntity= new OrderEntity();
        //orderEntity.setCustomerEntity(customerEntity);
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
        existingCustomer.setEmbeddableCustomer(convertToEntity(customerDto.getPersonalInfo()));
        // Adres güncelleme
        existingCustomer.setAddressEntity(convertToEntity(customerDto.getAddress()));

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
        customerEntity.setEmbeddableCustomer(convertToEntity(customerDto.getPersonalInfo()));
        customerEntity.setAddressEntity(convertToEntity(customerDto.getAddress()));

        List<OrderEntity> orderEntities = customerDto.getOrders().stream()
                .map(this::convertToEntity).collect(Collectors.toList());
        customerEntity.setOrders(orderEntities);
        orderEntities.forEach(order -> order.setCustomerEntity(customerEntity));

        return customerEntity;
    }

    private CustomerDto convertToDto(CustomerEntity customerEntity) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerEntity.getId());
        customerDto.setPersonalInfo(convertToDto(customerEntity.getEmbeddableCustomer()));
        customerDto.setAddress(convertToDto(customerEntity.getAddressEntity()));

        List<OrderDto> orderDtos = customerEntity.getOrders().stream()
                .map(this::convertToDto).collect(Collectors.toList());
        customerDto.setOrders(orderDtos);

        return customerDto;
    }

    private EmbeddableCustomer convertToEntity(PersonalInfoDto personalInfoDto) {
        EmbeddableCustomer personalInfo = new EmbeddableCustomer();
        personalInfo.setName(personalInfoDto.getName());
        personalInfo.setSurname(personalInfoDto.getSurname());
        personalInfo.setNotes(personalInfoDto.getNotes());
        personalInfo.setVatNumber(personalInfoDto.getVatNumber());
        personalInfo.setTcNumber(personalInfoDto.getTcNumber());
        personalInfo.setEmail(personalInfoDto.getEmail());
        return personalInfo;
    }

    private PersonalInfoDto convertToDto(EmbeddableCustomer personalInfo) {
        PersonalInfoDto personalInfoDto = new PersonalInfoDto();
        personalInfoDto.setName(personalInfo.getName());
        personalInfoDto.setSurname(personalInfo.getSurname());
        personalInfoDto.setNotes(personalInfo.getNotes());
        personalInfoDto.setVatNumber(personalInfo.getVatNumber());
        personalInfoDto.setTcNumber(personalInfo.getTcNumber());
        personalInfoDto.setEmail(personalInfo.getEmail());
        return personalInfoDto;
    }

    private AddressEntity convertToEntity(AddressDto addressDto) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setCity(addressDto.getCity());
        addressEntity.setCountry(addressDto.getCountry());
        addressEntity.setPostalCode(addressDto.getPostalCode());
        return addressEntity;
    }

    private AddressDto convertToDto(AddressEntity addressEntity) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(addressEntity.getId());
        addressDto.setStreet(addressEntity.getStreet());
        addressDto.setCity(addressEntity.getCity());
        addressDto.setCountry(addressEntity.getCountry());
        addressDto.setPostalCode(addressEntity.getPostalCode());
        return addressDto;
    }

    private OrderEntity convertToEntity(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName(orderDto.getName());
        orderEntity.setNumber(orderDto.getNumber());
        orderEntity.setPrice(orderDto.getPrice());
        orderEntity.setTotalAmount(orderDto.getTotalAmount());
        return orderEntity;
    }

    private OrderDto convertToDto(OrderEntity orderEntity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setName(orderEntity.getName());
        orderDto.setPrice(orderEntity.getPrice());
        orderDto.setNumber(orderEntity.getNumber());
        orderDto.setTotalAmount(orderEntity.getTotalAmount());
        return orderDto;
    }
}