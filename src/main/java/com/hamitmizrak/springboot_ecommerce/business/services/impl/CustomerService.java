package com.hamitmizrak.springboot_ecommerce.business.services.impl;

import com.hamitmizrak.springboot_ecommerce.business.dto.AddressDto;
import com.hamitmizrak.springboot_ecommerce.business.dto.CustomerDto;
import com.hamitmizrak.springboot_ecommerce.business.dto.OrderDto;
import com.hamitmizrak.springboot_ecommerce.data.entity.AddressEntity;
import com.hamitmizrak.springboot_ecommerce.data.entity.CustomerEntity;
import com.hamitmizrak.springboot_ecommerce.data.entity.OrderEntity;
//import com.hamitmizrak.springboot_ecommerce.data.repository.AddressRepository;
import com.hamitmizrak.springboot_ecommerce.data.repository.CustomerRepository;
import com.hamitmizrak.springboot_ecommerce.exception._404_NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Create a new customer
    public CustomerDto createCustomer(CustomerDto customerDto) {
        CustomerEntity customerEntity = convertToEntity(customerDto);
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        return convertToDto(savedCustomer);
    }

    // Get a customer by ID
    public CustomerDto getCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Customer not found with id: " + id));
        return convertToDto(customerEntity);
    }

    // Update an existing customer
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        CustomerEntity existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Customer not found with id: " + id));

        existingCustomer.setFirstName(customerDto.getFirstName());
        existingCustomer.setLastName(customerDto.getLastName());
        existingCustomer.setAddress(convertToEntity(customerDto.getAddress()));
        existingCustomer.setOrders(customerDto.getOrders().stream()
                .map(this::convertToEntity).collect(Collectors.toList()));

        CustomerEntity updatedCustomer = customerRepository.save(existingCustomer);
        return convertToDto(updatedCustomer);
    }

    // Delete a customer by ID
    public void deleteCustomer(Long id) {
        CustomerEntity existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Customer not found with id: " + id));
        customerRepository.delete(existingCustomer);
    }

    // Get all customers
    public List<CustomerDto> getAllCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();
        return customers.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // DTO to Entity and Entity to DTO conversion methods (as written previously)
    // ...
    // CustomerDto to CustomerEntity
    private CustomerEntity convertToEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerDto.getFirstName());
        customerEntity.setLastName(customerDto.getLastName());

        // AddressDto to AddressEntity dönüşümü
        AddressEntity addressEntity = convertToEntity(customerDto.getAddress());
        customerEntity.setAddress(addressEntity);

        // OrderDto to OrderEntity dönüşümü
        List<OrderEntity> orderEntities = customerDto.getOrders().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        customerEntity.setOrders(orderEntities);

        // Siparişlere müşteri set edilir
        orderEntities.forEach(order -> order.setCustomer(customerEntity));

        return customerEntity;
    }

    // AddressDto to AddressEntity
    private AddressEntity convertToEntity(AddressDto addressDto) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setCity(addressDto.getCity());
        addressEntity.setState(addressDto.getState());
        addressEntity.setPostalCode(addressDto.getPostalCode());
        return addressEntity;
    }

    // OrderDto to OrderEntity
    private OrderEntity convertToEntity(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNumber(orderDto.getOrderNumber());
        orderEntity.setTotalAmount(orderDto.getTotalAmount());
        return orderEntity;
    }

    // CustomerEntity to CustomerDto
    private CustomerDto convertToDto(CustomerEntity customerEntity) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerEntity.getId());
        customerDto.setFirstName(customerEntity.getFirstName());
        customerDto.setLastName(customerEntity.getLastName());

        // AddressEntity to AddressDto dönüşümü
        AddressDto addressDto = convertToDto(customerEntity.getAddress());
        customerDto.setAddress(addressDto);

        // OrderEntity to OrderDto dönüşümü
        List<OrderDto> orderDtos = customerEntity.getOrders().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        customerDto.setOrders(orderDtos);

        return customerDto;
    }

    // AddressEntity to AddressDto
    private AddressDto convertToDto(AddressEntity addressEntity) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(addressEntity.getId());
        addressDto.setStreet(addressEntity.getStreet());
        addressDto.setCity(addressEntity.getCity());
        addressDto.setState(addressEntity.getState());
        addressDto.setPostalCode(addressEntity.getPostalCode());
        return addressDto;
    }

    // OrderEntity to OrderDto
    private OrderDto convertToDto(OrderEntity orderEntity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setOrderNumber(orderEntity.getOrderNumber());
        orderDto.setTotalAmount(orderEntity.getTotalAmount());
        return orderDto;
    }


}
