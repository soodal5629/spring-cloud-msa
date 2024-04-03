package com.example.orderservice.messagequeue;

import com.example.orderservice.dto.*;
import com.example.orderservice.jpa.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    // 토픽에 전달할 내용
    List<Field> fields = Arrays.asList(
            new Field("string", true, "order_id"),
            new Field("string", true, "user_id"),
            new Field("string", true, "product_id"),
            new Field("int32", true, "qty"),
            new Field("int32", true, "unit_price"),
            new Field("int32", true, "total_price")
    );
    Schema schema = Schema.builder()
                        .type("struct")
                        .fields(fields)
                        .optional(false)
                        .name("orders")
                        .build();

    public OrderDto send(String topic, OrderDto orderDto) {
        Payload payload = Payload.builder()
                            .order_id(orderDto.getOrderId())
                            .user_id(orderDto.getUserId())
                            .product_id(orderDto.getProductId())
                            .qty(orderDto.getQty())
                            .unit_price(orderDto.getUnitPrice())
                            .total_price(orderDto.getTotalPrice())
                            .build();
        KafkaOrderDto kafkaOrderDto = new KafkaOrderDto(schema, payload);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            //jsonInString = mapper.writeValueAsString(orderDto);
            jsonInString = mapper.writeValueAsString(kafkaOrderDto);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

        kafkaTemplate.send(topic, jsonInString);
        //log.info("Kafka Producer sent data from the Order micro service: {}", orderDto);
        log.info("Order Producer sent data from th Order micro service: {}", kafkaOrderDto);
        return orderDto;
    }
}
