package com.hamitmizrak.springboot_ecommerce.business.services.impl;

import com.hamitmizrak.springboot_ecommerce.business.dto.AddressDto;
import com.hamitmizrak.springboot_ecommerce.data.entity.AddressEntity;
import com.hamitmizrak.springboot_ecommerce.data.repository.AddressRepository;
import com.hamitmizrak.springboot_ecommerce.exception._404_NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    // Create a new address
    public AddressDto createAddress(AddressDto addressDto) {
        AddressEntity addressEntity = convertToEntity(addressDto);
        AddressEntity savedAddress = addressRepository.save(addressEntity);
        return convertToDto(savedAddress);
    }

    // Get an address by ID
    public AddressDto getAddressById(Long id) {
        AddressEntity addressEntity = addressRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Address not found with id: " + id));
        return convertToDto(addressEntity);
    }

    // Update an existing address
    public AddressDto updateAddress(Long id, AddressDto addressDto) {
        AddressEntity existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Address not found with id: " + id));

        existingAddress.setStreet(addressDto.getStreet());
        existingAddress.setCity(addressDto.getCity());
        existingAddress.setState(addressDto.getState());
        existingAddress.setPostalCode(addressDto.getPostalCode());

        AddressEntity updatedAddress = addressRepository.save(existingAddress);
        return convertToDto(updatedAddress);
    }

    // Delete an address by ID
    public void deleteAddress(Long id) {
        AddressEntity existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Address not found with id: " + id));
        addressRepository.delete(existingAddress);
    }

    // Get all addresses
    public List<AddressDto> getAllAddresses() {
        List<AddressEntity> addresses = addressRepository.findAll();
        return addresses.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // DTO to Entity conversion
    private AddressEntity convertToEntity(AddressDto addressDto) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setCity(addressDto.getCity());
        addressEntity.setState(addressDto.getState());
        addressEntity.setPostalCode(addressDto.getPostalCode());
        return addressEntity;
    }

    // Entity to DTO conversion
    private AddressDto convertToDto(AddressEntity addressEntity) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(addressEntity.getId());
        addressDto.setStreet(addressEntity.getStreet());
        addressDto.setCity(addressEntity.getCity());
        addressDto.setState(addressEntity.getState());
        addressDto.setPostalCode(addressEntity.getPostalCode());
        return addressDto;
    }
}
