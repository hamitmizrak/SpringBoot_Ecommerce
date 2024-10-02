package com.hamitmizrak.springboot_ecommerce.business.services.impl;

import com.hamitmizrak.springboot_ecommerce.bean.ModelMapperBean;
import com.hamitmizrak.springboot_ecommerce.business.dto.AddressDto;
import com.hamitmizrak.springboot_ecommerce.business.services.IAddressServices;
import com.hamitmizrak.springboot_ecommerce.data.entity.AddressEntity;
import com.hamitmizrak.springboot_ecommerce.data.repository.IAddressRepository;
import com.hamitmizrak.springboot_ecommerce.exception.HamitMizrakException;
import com.hamitmizrak.springboot_ecommerce.exception._404_NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


// LOMBOK
@RequiredArgsConstructor
@Log4j2

// Asıl İş katmanımız
@Service
public class AddressServicesImpl implements IAddressServices<AddressDto, AddressEntity> {
    // Field
    // Lombok Constructor Parametreli Injection
    private final IAddressRepository iAddressRepository;
    private final ModelMapperBean modelMapperBean;

    // Model Mapper
    @Override
    public AddressDto entityToDto(AddressEntity addressEntity) {
        return modelMapperBean.getModelMapperBeanMethod().map(addressEntity, AddressDto.class);
    }

    @Override
    public AddressEntity dtoToEntity(AddressDto addressDto) {
        return modelMapperBean.getModelMapperBeanMethod().map(addressDto, AddressEntity.class);
    }

    // CREATE
    @Transactional
    @Override
    public AddressDto addressServiceCreate(AddressDto addressDto) {
        if (addressDto == null) {
            throw new NullPointerException("Address is null");
        }
        AddressEntity addressEntity = dtoToEntity(addressDto);
        iAddressRepository.save(addressEntity);
        // addressDto.setCustomerId(addressEntity.getCustomerId());
        // addressDto.setCreatedDate(addressEntity.getCreatedDate());
        return addressDto;
    }

    // LIST
    @Override
    public List<AddressDto> addressServiceList() {
        Iterable<AddressEntity> iterableCustomerList = iAddressRepository.findAll();
        List<AddressDto> dtoArrayList = new ArrayList<>();
        for (AddressEntity temp : iterableCustomerList) {
            AddressDto addressDto = entityToDto(temp);
            dtoArrayList.add(addressDto);
        }
        log.info("Address Listesi: " + dtoArrayList.size() + " tanedir");
        return dtoArrayList;

    }

    // FIND
    @Override
    public AddressDto addressServiceFindById(Long id) {
        // FIND 1.YOL
        /*
        Optional<AddressEntity> findOptionalById= iAddressRepository.findById(id);
        AddressDto AddressDto= entityToDto(findOptionalById.get());
        if(findOptionalById.isPresent()){
            return AddressDto;
        }
        */

        // FIND 2.YOL
        // 2.YOL (FIND)
        AddressEntity addressEntity = null;
        if (id != null) {
            addressEntity = iAddressRepository.findById(id)
                    .orElseThrow(() -> new _404_NotFoundException(id + " nolu id yoktur"));
        } else if (id == null) {
            throw new HamitMizrakException("İd null olarak geldi");
        }
        return entityToDto(addressEntity);
    }

    // UPDATE
    @Transactional
    @Override
    public AddressDto addressServiceUpdateFindById(Long id, AddressDto addressDto) {
        // Önce Bulmam Lazım
        AddressDto addressUpdateFindById = addressServiceFindById(id);
        if (addressUpdateFindById != null) {
            AddressEntity addressEntity = dtoToEntity(addressDto);
            addressEntity.setCity(addressDto.getCity());
            addressEntity.setCountry(addressDto.getCountry());
            addressEntity.setStreet(addressDto.getStreet());
            addressEntity.setPostalCode(addressDto.getPostalCode());
            iAddressRepository.save(addressEntity);
        }
        return addressDto;
    }

    // DELETE
    @Transactional
    @Override
    public AddressDto addressServiceDeleteFindById(Long id) {
        // Önce Bulmam Lazım
        AddressDto addressDtoDeleteFindById = addressServiceFindById(id);
        if (addressDtoDeleteFindById != null) {
            iAddressRepository.deleteById(id);
        }
        return addressDtoDeleteFindById;
    }
} //end  AddressServicesImpl
