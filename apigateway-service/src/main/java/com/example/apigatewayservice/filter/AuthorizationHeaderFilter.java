package com.example.apigatewayservice.filter;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private Environment env;

    public AuthorizationHeaderFilter(Environment env) {
        this.env = env;
    }

    public static class Config{

    }

    // login -> token -> users (with token) -> header(include token)
    @Override
    public GatewayFilter apply(AuthorizationHeaderFilter.Config config) {
        return ((exchange, chain) -> {
            /* 웹플럭스 비동기 방식이기 때문에 기존의 HttpServletRequest 사용안함 */
            ServerHttpRequest request = exchange.getRequest();

            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }
            String authorizationHeader = request.getHeaders(). // 헤얻에서 토큰 가져오기
                    get(org.springframework.http.HttpHeaders.AUTHORIZATION).get(0); // 배열이라서 get(0)
            String jwt = authorizationHeader.replace("Bearer", ""); // Bearer 라는 값을 가지고 토큰 정보가 전달됨

            if(!isJwtValid(jwt)){
                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }
            
            return chain.filter(exchange);

        });
    }

    private boolean isJwtValid(String jwt) {
        boolean returnValue = true;
        String subject = null;
        try{
            subject = Jwts.parser().setSigningKey(env.getProperty("token.secret"))
                    .parseClaimsJws(jwt).getBody()
                    .getSubject();
        }catch(Exception e){
            returnValue = false;
        }
        if (subject == null || subject.isEmpty()){
            returnValue = false;
        }

        return returnValue;

    }

    /* 웹플럭스에서 데이터를 처리하는 두가지 단위 중 하나가 Mono(단일값이라고 생각하면 됨) */
    /* 단일값이 아닌 데이터의 형태의 경우에는 Flux를 쓰면 됨 */
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        log.error(err);
        return response.setComplete(); // response를 Mono타입으로 전달

    }
}
