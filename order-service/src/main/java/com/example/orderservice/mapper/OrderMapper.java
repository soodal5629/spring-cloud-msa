package com.example.orderservice.mapper;

import com.example.orderservice.data.OrderData;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.jpa.OrderEntity;
import com.example.orderservice.vo.ResponseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", ignore = true)
    OrderEntity toEntity(OrderData orderData);
    OrderDto toDto(OrderData orderData);

    ResponseOrder toResponseOrder(OrderData orderData);
}
