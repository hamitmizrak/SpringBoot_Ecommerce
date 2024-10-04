package com.hamitmizrak.springboot_ecommerce.business.services.impl;

import com.hamitmizrak.springboot_ecommerce.business.dto.OrderDto;
import com.hamitmizrak.springboot_ecommerce.data.entity.OrderEntity;
import com.hamitmizrak.springboot_ecommerce.data.repository.OrderRepository;
import com.hamitmizrak.springboot_ecommerce.exception._404_NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto createOrder(OrderDto orderDto) {
        OrderEntity orderEntity = convertToEntity(orderDto);
        OrderEntity savedOrder = orderRepository.save(orderEntity);
        return convertToDto(savedOrder);
    }

    public OrderDto getOrderById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Order not found with id: " + id));
        return convertToDto(orderEntity);
    }

    public List<OrderDto> getAllOrders() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        OrderEntity existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Order not found with id: " + id));

        existingOrder.setOrderNumber(orderDto.getOrderNumber());
        existingOrder.setTotalAmount(orderDto.getTotalAmount());

        OrderEntity updatedOrder = orderRepository.save(existingOrder);
        return convertToDto(updatedOrder);
    }

    public void deleteOrder(Long id) {
        OrderEntity existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new _404_NotFoundException("Order not found with id: " + id));
        orderRepository.delete(existingOrder);
    }

    // Entity ve DTO dönüşüm metotları
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
