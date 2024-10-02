package com.hamitmizrak.springboot_ecommerce.business.services.impl;

import com.hamitmizrak.springboot_ecommerce.bean.ModelMapperBean;
import com.hamitmizrak.springboot_ecommerce.business.dto.CustomerDto;
import com.hamitmizrak.springboot_ecommerce.business.services.ICustomerServices;
import com.hamitmizrak.springboot_ecommerce.data.entity.CustomerEntity;
import com.hamitmizrak.springboot_ecommerce.data.repository.ICustomerRepository;
import com.hamitmizrak.springboot_ecommerce.exception.HamitMizrakException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

 /*
     Iterable<CustomerEntity>
    Tanım: Iterable, Java'da bir interface'tir ve üzerinde for-each döngüsü çalıştırılabilecek koleksiyonlar için kullanılır.
    Bir koleksiyon üzerinde gezinmek için kullanılan en temel yapı olarak düşünülebilir.
    Neden Iterable? Spring Data JPA, findAll() metodunu çağırdığınızda veritabanındaki tüm CustomerEntity kayıtlarını döner.
    Ancak bu kayıtlar bazen çok büyük olabilir, bu yüzden daha hafif bir veri yapısı olan Iterable kullanılır.
    Iterable daha basit bir arayüzdür ve verilerin bir anda hafızaya alınmasını gerektirmez; veriler ihtiyaç oldukça sırayla alınabilir.
    Örnek: Iterable<CustomerEntity> size veritabanındaki tüm CustomerEntity kayıtlarını sağlayan bir veri yapısıdır.
    Bu kayıtları döngü ile tek tek işleyebilir veya başka veri yapısına dönüştürebilirsiniz.
 */

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// Asıl İş katmanımız
@Service
public class CustomerServicesImpl implements ICustomerServices<CustomerDto, CustomerEntity> {
    // Field
    // Lombok Constructor Parametreli Injection
    private final ICustomerRepository iCustomerRepository;
    private final ModelMapperBean modelMapperBean;

    // Model Mapper
    @Override
    public CustomerDto entityToDto(CustomerEntity entity) {
        return modelMapperBean.getModelMapperBeanMethod().map(entity, CustomerDto.class);
    }

    @Override
    public CustomerEntity dtoToEntity(CustomerDto dto) {
        return modelMapperBean.getModelMapperBeanMethod().map(dto, CustomerEntity.class);
    }

    ///////////////////////////////////////////////////////////////////
    // CREATE
    @Transactional
    @Override
    public CustomerDto customerServiceCreate(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new NullPointerException("CustomerDto is null");
        }
        CustomerEntity customerEntity = dtoToEntity(customerDto);
        iCustomerRepository.save(customerEntity);
        // customerDto.setCustomerId(customerEntity.getCustomerId());
        // customerDto.setCreatedDate(customerEntity.getCreatedDate());
        return customerDto;
    }

    // LIST
    @Override
    public List<CustomerDto> customerServiceList() {
        /*
        Tanım: Iterable, Java'da bir interface'tir ve üzerinde for-each döngüsü çalıştırılabilecek koleksiyonlar için kullanılır.
        Bir koleksiyon üzerinde gezinmek için kullanılan en temel yapı olarak düşünülebilir.
        Neden Iterable? Spring Data JPA, findAll() metodunu çağırdığınızda veritabanındaki tüm CustomerEntity kayıtlarını döner.
        Ancak bu kayıtlar bazen çok büyük olabilir, bu yüzden daha hafif bir veri yapısı olan Iterable kullanılır.
        Iterable daha basit bir arayüzdür ve verilerin bir anda hafızaya alınmasını gerektirmez; veriler ihtiyaç oldukça sırayla alınabilir.
        Örnek: Iterable<CustomerEntity> size veritabanındaki tüm CustomerEntity kayıtlarını sağlayan bir veri yapısıdır.
        Bu kayıtları döngü ile tek tek işleyebilir veya başka veri yapısına dönüştürebilirsiniz.
         */
        Iterable<CustomerEntity> iterableCustomerList = iCustomerRepository.findAll();
        // 1.YOL
        /*
        StreamSupport.stream() metodunu kullanarak Iterable'i Stream'e dönüştürüyoruz.
        spliterator() metodu, Iterable üzerinde bir Spliterator döndürür,
        bu da StreamSupport.stream() tarafından bir Stream oluşturmak için kullanılır.
        false parametresi, stream'in paralel mi yoksa sıralı mı olduğunu belirtir. Burada sıralı bir stream kullanıyoruz.
         */
        /*
        return StreamSupport.stream(iterableCustomerList.spliterator(), false)
                .map(this::entityToDto)
                .collect(Collectors.toList());
         */

        // 2.YOL
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (CustomerEntity temp : iterableCustomerList) {
            CustomerDto customerDto = entityToDto(temp);
            customerDtoList.add(customerDto);
        }
        log.info("Customer Listesi: " + customerDtoList.size() + " tanedir");
        return customerDtoList;

    }

    // FIND
    @Override
    public CustomerDto customerServiceFindById(Long id) {
        // FIND 1.YOL
        /*
        Optional<CustomerEntity> findOptionalById= iCustomerRepository.findById(id);
        CustomerDto customerDto= entityToDto(findOptionalById.get());
        if(findOptionalById.isPresent()){
            return customerDto;
        }
        */

        // FIND 2.YOL
        CustomerEntity customerEntity = iCustomerRepository.findById(id).orElseThrow(() -> {
            throw new HamitMizrakException(id + "bobin yoktur");
        });
        return entityToDto(customerEntity);
    }

    // UPDATE
    @Transactional
    @Override
    public CustomerDto customerServiceUpdateFindById(Long id, CustomerDto customerDto) {
        // Önce Bulmam Lazım
        CustomerDto customerDtoUpdateFindById = customerServiceFindById(id);
        if (customerDtoUpdateFindById != null) {
            CustomerEntity customerEntity = dtoToEntity(customerDtoUpdateFindById);
            customerEntity.getEmbeddableCustomerEntity().setCustomerName(customerDto.getCustomerName());
            customerEntity.getEmbeddableCustomerEntity().setCustomerSurname(customerDto.getCustomerSurname());
            customerEntity.getEmbeddableCustomerEntity().setCustomerNotes(customerDto.getCustomerNotes());
            customerEntity.getEmbeddableCustomerEntity().setCustomerEmail(customerDto.getCustomerEmail());
            customerEntity.getEmbeddableCustomerEntity().setCustomerGender(customerDto.getCustomerGender());
            customerEntity.getEmbeddableCustomerEntity().setCustomerTcNumber(customerDto.getCustomerTcNumber());
            customerEntity.getEmbeddableCustomerEntity().setCustomerVatNumber(customerDto.getCustomerVatNumber());
            iCustomerRepository.save(customerEntity);
        }
        return customerDtoUpdateFindById;
    }

    // DELETE
    @Transactional
    @Override
    public CustomerDto customerServiceDeleteFindById(Long id) {
        // Önce Bulmam Lazım
        CustomerDto customerDtoDeleteFindById = customerServiceFindById(id);
        if (customerDtoDeleteFindById != null) {
            iCustomerRepository.deleteById(id);
        }
        return customerDtoDeleteFindById;
    }
} //end  CustomerServicesImpl
