package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    public LoggingFilter() { // 생성자
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(LoggingFilter.Config config) {
        // Custom Pre Filter
//        return ((exchange, chain) -> {
//            /* 웹플럭스 비동기 방식이기 때문에 기존의 HttpServletRequest 사용안함 */
//            ServerHttpRequest request = exchange.getRequest();
//            ServerHttpResponse response = exchange.getResponse();
//
//            log.info("Global filter baseMessage: --> {}", config.getBaseMessage());
//            if (config.isPreLogger()){
//                log.info("Global filter Start : request id --> {}", request.getId());
//            }
//            // Custom Post Filter
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                if (config.isPostLogger()){
//                    log.info("Global filter End : response code --> {}", response.getStatusCode());
//                }
//
//            }));
//
//        });
        /* 위에 주석한 람다표현식 코드랑 같은 코드임 */
        GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Logging filter baseMessage: --> {}", config.getBaseMessage());
            if (config.isPreLogger()){
                log.info("Logging filter Start : request id --> {}", request.getId());
            }
            // Custom Post Filter
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if (config.isPostLogger()){
                    log.info("Logging filter End : response code --> {}", response.getStatusCode());
                }

            }));
        }, Ordered.HIGHEST_PRECEDENCE); // --> Global Filter보다 먼저 실행됨
        return filter;
    }

    @Data
    public static class Config {
        // Configuration 정보 넣을 수 있음
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
