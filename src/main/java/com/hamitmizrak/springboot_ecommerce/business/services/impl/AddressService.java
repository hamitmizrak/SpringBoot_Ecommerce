package com.hamitmizrak.springboot_ecommerce.business.services.impl;

import com.hamitmizrak.springboot_ecommerce.bean.ModelMapperBean;
import com.hamitmizrak.springboot_ecommerce.business.dto.AddressDto;
import com.hamitmizrak.springboot_ecommerce.business.services.IAddressServices;
import com.hamitmizrak.springboot_ecommerce.data.entity.AddressEntity;
import com.hamitmizrak.springboot_ecommerce.data.repository.AddressRepository;
import com.hamitmizrak.springboot_ecommerce.exception._404_NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// LOMBOK
@RequiredArgsConstructor

@Service
public class AddressService implements IAddressServices<AddressDto, AddressEntity> {

    // Field
    private final AddressRepository addressRepository;
    private final ModelMapperBean modelMapperBean;


    //////////////////////////////////////////////////////////////////////////
    @Override
    public AddressDto entityToDto(AddressEntity addressEntity) {
        return modelMapperBean.getModelMapperBeanMethod().map(addressEntity, AddressDto.class);
    }

    @Override
    public AddressEntity dtoToEntity(AddressDto addressDto) {
        return modelMapperBean.getModelMapperBeanMethod().map(addressDto, AddressEntity.class);
    }

    ///////////////////////////////////////////////////////////////
    // CREATE
    @Override
    public AddressDto addressServiceCreate(AddressDto addressDto) {
        AddressEntity addressEntity = dtoToEntity(addressDto);
        // JDBC Template save işlemi
        int rowsAffected = addressRepository.save(addressEntity);
        if (rowsAffected == 0) {
            throw new RuntimeException("Failed to save the address.");
        }
        // Yeni kaydedilen adres ID'sini alıyoruz
        Long newId = getLastInsertedId();
        addressEntity.setId(newId);
        return entityToDto(addressEntity);
    }

    // LIST
    @Override
    public List<AddressDto> addressServiceList() {
        //return List.of();
        List<AddressEntity> addresses = addressRepository.findAll();
        return addresses.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    // FIND BY ID
    @Override
    public AddressDto addressServiceFindById(Long id) {
        Optional<AddressEntity> addressEntity = addressRepository.findById(id);
        if (addressEntity.isPresent()) {
            return entityToDto(addressEntity.get());
        } else {
            throw new _404_NotFoundException("Address not found with id: " + id);
        }
    }

    // UPDATE
    @Override
    public AddressDto addressServiceUpdateFindById(Long id, AddressDto addressDto) {
        Optional<AddressEntity> addressUpdate = addressRepository.findById(id);
        if (!addressUpdate.isPresent()) {
            throw new _404_NotFoundException("Address not found with id: " + id);
        }

        AddressEntity existingAddress = addressUpdate.get();
        existingAddress.setStreet(addressDto.getStreet());
        existingAddress.setCity(addressDto.getCity());
        existingAddress.setCountry(addressDto.getCountry());
        existingAddress.setPostalCode(addressDto.getPostalCode());

        int rowsAffected = addressRepository.update(existingAddress);
        if (rowsAffected == 0) {
            throw new RuntimeException("Failed to update the address.");
        }
        return entityToDto(existingAddress);
    }

    // DELETE
    @Override
    public AddressDto addressServiceDeleteFindById(Long id) {
        Optional<AddressEntity> addressDelete = addressRepository.findById(id);
        if (!addressDelete.isPresent()) {
            throw new _404_NotFoundException("Address not found with id: " + id);
        }
        int rowsAffected = addressRepository.deleteById(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("Failed to delete the address.");
        }
        return entityToDto(addressDelete.get());
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

