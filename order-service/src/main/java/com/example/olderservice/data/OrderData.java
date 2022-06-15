package com.example.olderservice.data;

import lombok.Data;

import java.util.Date;

@Data
public class OrderData {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;

    private Date createdAt;
}
