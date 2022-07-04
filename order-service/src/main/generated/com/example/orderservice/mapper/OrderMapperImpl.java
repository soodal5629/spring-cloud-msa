package com.example.orderservice.mapper;

import com.example.orderservice.data.OrderData;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.jpa.OrderEntity;
import com.example.orderservice.vo.ResponseOrder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-04T21:07:04+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderEntity toEntity(OrderData orderData) {
        if ( orderData == null ) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setProductId( orderData.getProductId() );
        orderEntity.setQty( orderData.getQty() );
        orderEntity.setUnitPrice( orderData.getUnitPrice() );
        orderEntity.setTotalPrice( orderData.getTotalPrice() );
        orderEntity.setUserId( orderData.getUserId() );
        orderEntity.setOrderId( orderData.getOrderId() );
        orderEntity.setCreatedAt( orderData.getCreatedAt() );

        return orderEntity;
    }

    @Override
    public OrderDto toDto(OrderData orderData) {
        if ( orderData == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setProductId( orderData.getProductId() );
        orderDto.setQty( orderData.getQty() );
        orderDto.setUnitPrice( orderData.getUnitPrice() );
        orderDto.setTotalPrice( orderData.getTotalPrice() );
        orderDto.setOrderId( orderData.getOrderId() );
        orderDto.setUserId( orderData.getUserId() );
        orderDto.setCreatedAt( orderData.getCreatedAt() );

        return orderDto;
    }

    @Override
    public ResponseOrder toResponseOrder(OrderData orderData) {
        if ( orderData == null ) {
            return null;
        }

        ResponseOrder responseOrder = new ResponseOrder();

        responseOrder.setProductId( orderData.getProductId() );
        responseOrder.setQty( orderData.getQty() );
        responseOrder.setUnitPrice( orderData.getUnitPrice() );
        responseOrder.setTotalPrice( orderData.getTotalPrice() );
        responseOrder.setOrderId( orderData.getOrderId() );
        responseOrder.setUserId( orderData.getUserId() );
        responseOrder.setCreatedAt( orderData.getCreatedAt() );

        return responseOrder;
    }
}
