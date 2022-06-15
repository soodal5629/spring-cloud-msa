package com.example.olderservice.mapper;

import com.example.olderservice.data.OrderData;
import com.example.olderservice.dto.OrderDto;
import com.example.olderservice.jpa.OrderEntity;
import com.example.olderservice.vo.ResponseOrder;
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
