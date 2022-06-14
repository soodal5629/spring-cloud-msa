package com.example.olderservice.vo;

import com.example.olderservice.data.OrderData;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrder extends OrderData {

}
