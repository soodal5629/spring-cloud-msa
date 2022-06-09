package com.example.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    public static class Config {
        // Configuration 정보 넣을 수 있음
    }

    public CustomFilter() { // 생성자
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(CustomFilter.Config config) {
        // Custom Pre Filter
        return ((exchange, chain) -> {
            /* 웹플럭스 비동기 방식이기 때문에 기존의 HttpServletRequest 사용안함 */
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom PRE filter: request id --> {}", request.getId());

            // Custom Post Filter
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Custom Post filter: response code --> {}", response.getStatusCode());
            }));

        });
    }


}
