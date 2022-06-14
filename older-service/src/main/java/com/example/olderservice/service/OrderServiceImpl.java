package com.example.olderservice.service;

import com.example.olderservice.dto.OrderDto;
import com.example.olderservice.jpa.OrderEntity;
import com.example.olderservice.jpa.OrderRepository;
import com.example.olderservice.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        orderDto.setOrderId(UUID.randomUUID().toString()); // uuid 생성
        orderDto.setTotalPrice(orderDto.getUnitPrice() * orderDto.getQty());
        OrderEntity orderEntity = OrderMapper.INSTANCE.toEntity(orderDto);
        orderRepository.save(orderEntity);
        OrderDto returnValue = OrderMapper.INSTANCE.toDto(orderEntity);
        return returnValue;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        OrderDto orderDto = OrderMapper.INSTANCE.toDto(orderEntity);
        return orderDto;
    }

    @Override
    public Iterable<OrderEntity> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
