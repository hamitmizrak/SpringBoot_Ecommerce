package com.hamitmizrak.springboot_ecommerce.business.services.impl;

import com.hamitmizrak.springboot_ecommerce.business.dto.AddressDto;
import com.hamitmizrak.springboot_ecommerce.data.entity.AddressEntity;
import com.hamitmizrak.springboot_ecommerce.data.repository.AddressRepository;
import com.hamitmizrak.springboot_ecommerce.exception._404_NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    // Field
    private final AddressRepository addressRepository;

    // Parametreli Cosntructor
    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    // Create a new address
    public AddressDto createAddress(AddressDto addressDto) {
        AddressEntity addressEntity = convertToEntity(addressDto);
        // JDBC Template save işlemi
        int rowsAffected = addressRepository.save(addressEntity);
        if (rowsAffected == 0) {
            throw new RuntimeException("Failed to save the address.");
        }
        // Yeni kaydedilen adres ID'sini alıyoruz
        Long newId = getLastInsertedId();
        addressEntity.setId(newId);
        return convertToDto(addressEntity);
    }

    // Get an address by ID
    public AddressDto getAddressById(Long id) {
        Optional<AddressEntity> addressEntity = addressRepository.findById(id);
        if (addressEntity.isPresent()) {
            return convertToDto(addressEntity.get());
        } else {
            throw new _404_NotFoundException("Address not found with id: " + id);
        }
    }

    // Update an existing address
    public AddressDto updateAddress(Long id, AddressDto addressDto) {
        Optional<AddressEntity> existingAddressOptional = addressRepository.findById(id);
        if (!existingAddressOptional.isPresent()) {
            throw new _404_NotFoundException("Address not found with id: " + id);
        }

        AddressEntity existingAddress = existingAddressOptional.get();
        existingAddress.setStreet(addressDto.getStreet());
        existingAddress.setCity(addressDto.getCity());
        existingAddress.setState(addressDto.getState());
        existingAddress.setPostalCode(addressDto.getPostalCode());

        int rowsAffected = addressRepository.update(existingAddress);
        if (rowsAffected == 0) {
            throw new RuntimeException("Failed to update the address.");
        }
        return convertToDto(existingAddress);
    }

    // Delete an address by ID
    public void deleteAddress(Long id) {
        Optional<AddressEntity> existingAddress = addressRepository.findById(id);
        if (!existingAddress.isPresent()) {
            throw new _404_NotFoundException("Address not found with id: " + id);
        }
        int rowsAffected = addressRepository.deleteById(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("Failed to delete the address.");
        }
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

    // JDBC Template için yeni eklenen adresin ID'sini almak için bir metot
    private Long getLastInsertedId() {
        return addressRepository.getLastInsertedId();
    }
}


/*
 Açıklamalar:
getLastInsertedId(): Bu metot, veritabanında son eklenen kaydın ID'sini almak için kullanılır. SQL'de LAST_INSERT_ID() fonksiyonu, son eklenen satırın otomatik artan birincil anahtarını döndürür.
save(AddressEntity address): Veritabanına yeni bir adres eklemek için kullanılan SQL sorgusu.
findById(Long id): Veritabanından belirli bir ID'ye sahip adresi bulmak için kullanılır.
update(AddressEntity address): Var olan bir adresi güncellemek için SQL sorgusu.
deleteById(Long id): Belirli bir ID'ye sahip adresi silmek için kullanılan SQL sorgusu.
findAll(): Tüm adresleri listelemek için kullanılan SQL sorgusu.
Bu getLastInsertedId() metodu sayesinde, veritabanına yeni bir kayıt ekledikten sonra son eklenen kaydın ID'sine ulaşabiliyoruz. Bu özellikle, yeni oluşturulan nesneyi tekrar geri döndürmek için kullanışlıdır.
 */


/*
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
     */

