package com.hamitmizrak.springboot_ecommerce.business.services.impl;

import com.hamitmizrak.springboot_ecommerce.bean.ModelMapperBean;
import com.hamitmizrak.springboot_ecommerce.business.dto.OrderDto;
import com.hamitmizrak.springboot_ecommerce.business.services.IOrderServices;
import com.hamitmizrak.springboot_ecommerce.data.entity.OrderEntity;
import com.hamitmizrak.springboot_ecommerce.data.repository.IOrderRepository;
import com.hamitmizrak.springboot_ecommerce.exception._404_NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// LOMBOK
@RequiredArgsConstructor

@Service
public class OrderService implements IOrderServices<OrderDto, OrderEntity> {

    // INJECTION
    private final IOrderRepository iOrderRepository;
    private final ModelMapperBean modelMapperBean;

    /////////////////////////////////////////////////////////////////////////////////////
    // MODEL MAPPER
    @Override
    public OrderDto entityToDto(OrderEntity orderEntity) {
        return modelMapperBean.getModelMapperBeanMethod().map(orderEntity, OrderDto.class);
    }

    @Override
    public OrderEntity dtoToEntity(OrderDto orderDto) {
        return modelMapperBean.getModelMapperBeanMethod().map(orderDto, OrderEntity.class);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // CREATE (ORDER)
    @Override
    public OrderDto orderServiceCreate(OrderDto orderDto) {
        OrderEntity orderEntity = dtoToEntity(orderDto);
        OrderEntity savedOrder = iOrderRepository.save(orderEntity);
        return entityToDto(savedOrder);
    }

    // LIST (ORDER)
    @Override
    public List<OrderDto> orderServiceList() {
        List<OrderEntity> orderEntities = iOrderRepository.findAll();
        return orderEntities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    // FIND BY ID (ORDER)
    @Override
    public OrderDto orderServiceFindById(Long id) {
        OrderEntity orderEntity = iOrderRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Order not found with id: " + id));
        return entityToDto(orderEntity);
    }

    // UPDATE (ORDER)
    @Override
    public OrderDto orderServiceUpdateFindById(Long id, OrderDto orderDto) {
        OrderEntity orderEntityUpdate = iOrderRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Order not found with id: " + id));
        orderEntityUpdate.setName(orderDto.getName());
        orderEntityUpdate.setNumber(orderDto.getNumber());
        orderEntityUpdate.setPrice(orderDto.getPrice());
        orderEntityUpdate.setTotalAmount(orderDto.getTotalAmount());
        OrderEntity updatedOrder = iOrderRepository.save(orderEntityUpdate);
        return entityToDto(updatedOrder);
    }

    // DELETE (ORDER)
    @Override
    public OrderDto orderServiceDeleteFindById(Long id) {
        OrderEntity orderEntityDelete = iOrderRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Order not found with id: " + id));
        iOrderRepository.delete(orderEntityDelete);
        return entityToDto(orderEntityDelete);
    }

}
