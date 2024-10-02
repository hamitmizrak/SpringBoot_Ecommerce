package com.hamitmizrak.springboot_ecommerce.business.services.impl;

import com.hamitmizrak.springboot_ecommerce.bean.ModelMapperBean;
import com.hamitmizrak.springboot_ecommerce.business.dto.OrderDto;
import com.hamitmizrak.springboot_ecommerce.business.services.IOrderServices;
import com.hamitmizrak.springboot_ecommerce.data.entity.OrderEntity;
import com.hamitmizrak.springboot_ecommerce.data.repository.IOrderRepository;
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
public class OrderServicesImpl implements IOrderServices<OrderDto, OrderEntity>  {
    // Field
    // Lombok Constructor Parametreli Injection
    private final IOrderRepository iOrderRepository;
    private final ModelMapperBean modelMapperBean;

    // Model Mapper
    @Override
    public OrderDto entityToDto(OrderEntity orderEntity) {
        return modelMapperBean.getModelMapperBeanMethod().map(orderEntity, OrderDto.class);
    }

    @Override
    public OrderEntity dtoToEntity(OrderDto orderDto) {
        return modelMapperBean.getModelMapperBeanMethod().map(orderDto, OrderEntity.class);
    }

    // CREATE
    @Transactional
    @Override
    public OrderDto orderServiceCreate(OrderDto orderDto) {
        if (orderDto == null) {
            throw new NullPointerException("Order is null");
        }
        OrderEntity orderEntity = dtoToEntity(orderDto);
        iOrderRepository.save(orderEntity);
        // orderDto.setCustomerId(orderEntity.getCustomerId());
        // orderDto.setCreatedDate(orderEntity.getCreatedDate());
        return orderDto;
    }

    // LIST
    @Override
    public List<OrderDto> orderServiceList() {
        Iterable<OrderEntity> iterableOrderList = iOrderRepository.findAll();
        List<OrderDto> dtoArrayList = new ArrayList<>();
        for (OrderEntity temp : iterableOrderList) {
            OrderDto addressDto = entityToDto(temp);
            dtoArrayList.add(addressDto);
        }
        log.info("Order Listesi: " + dtoArrayList.size() + " tanedir");
        return dtoArrayList;
    }

    // FIND
    @Override
    public OrderDto orderServiceFindById(Long id) {
        // FIND 1.YOL
        /*
        Optional<OrderEntity> findOptionalById= iAddressRepository.findById(id);
        OrderDto AddressDto= entityToDto(findOptionalById.get());
        if(findOptionalById.isPresent()){
            return AddressDto;
        }
        */

        // FIND 2.YOL
        // 2.YOL (FIND)
        OrderEntity orderEntity = null;
        if (id != null) {
            orderEntity = iOrderRepository.findById(id)
                    .orElseThrow(() -> new _404_NotFoundException(id + " nolu id yoktur"));
        } else if (id == null) {
            throw new HamitMizrakException("İd null olarak geldi");
        }
        return entityToDto(orderEntity);
    }

    // UPDATE
    @Transactional
    @Override
    public OrderDto orderServiceUpdateFindById(Long id, OrderDto orderDto) {
        // Önce Bulmam Lazım
        OrderDto addressUpdateFindById = orderServiceFindById(id);
        if (addressUpdateFindById != null) {
            OrderEntity orderEntity = dtoToEntity(orderDto);
            orderEntity.setOrderName(orderDto.getOrderName());
            orderEntity.setOrderPrice(orderDto.getOrderPrice());
            orderEntity.setOrderCode(orderDto.getOrderCode());
            iOrderRepository.save(orderEntity);
        }
        return orderDto;
    }

    // DELETE
    @Transactional
    @Override
    public OrderDto orderServiceDeleteFindById(Long id) {
        // Önce Bulmam Lazım
        OrderDto orderDtoDeleteFindById = orderServiceFindById(id);
        if (orderDtoDeleteFindById != null) {
            iOrderRepository.deleteById(id);
        }
        return orderDtoDeleteFindById;
    }
} //end  AddressServicesImpl
