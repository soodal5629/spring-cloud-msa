package com.example.userservice.client;

import com.example.userservice.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "order-service") // 호출하려는 MS 서비스 이름
public interface OrderServiceClient {

    @GetMapping("/order-service/{userId}/orders_ng")
    List<ResponseOrder>  getOrders(@PathVariable("userId") String userId); // @PathValiable 의 ("userId") 생략하면 에러 발생

}
