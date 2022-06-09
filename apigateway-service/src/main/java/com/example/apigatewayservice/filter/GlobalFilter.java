package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    public GlobalFilter() { // 생성자
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(GlobalFilter.Config config) {
        // Custom Pre Filter
        return ((exchange, chain) -> {
            /* 웹플럭스 비동기 방식이기 때문에 기존의 HttpServletRequest 사용안함 */
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Global filter baseMessage: --> {}", config.getBaseMessage());
            if (config.isPreLogger()){
                log.info("Global filter Start : request id --> {}", request.getId());
            }
            // Custom Post Filter
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if (config.isPostLogger()){
                    log.info("Global filter End : response code --> {}", response.getStatusCode());
                }

            }));

        });
    }

    @Data
    public static class Config {
        // Configuration 정보 넣을 수 있음
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
