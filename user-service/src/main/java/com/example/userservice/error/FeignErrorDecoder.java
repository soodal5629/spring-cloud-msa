package com.example.userservice.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/* FeignClient - ErrorDecoder를 이용항 예외처리 */
@Component
public class FeignErrorDecoder implements ErrorDecoder {
    private Environment env;

    public FeignErrorDecoder(Environment env) { // 이 방식 쓰려면 Component로 등록해줘야 함
        this.env = env;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                break;
            case 404:
                if (methodKey.contains("getOrders")){
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()),
                            env.getProperty("order_service.exception.order_is_empty"));
                }
                break;
            default:
                return new Exception(response.reason());
        }
        return null;
    }
}
