package com.example.olderservice.service;

import com.example.olderservice.dto.OrderDto;
import com.example.olderservice.jpa.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto oderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);

}
