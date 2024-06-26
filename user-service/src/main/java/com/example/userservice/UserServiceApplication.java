package com.example.userservice;

import com.example.userservice.error.FeignErrorDecoder;
import com.example.userservice.service.UserService;
import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient // @EnableEurekaClient는 @EnableDiscoveryClient 를 좀더 구현해서 상품화 시켜놓은 것
@EnableFeignClients
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced // 유레카에 등록된 MS 이름을 찾아서 바로 해당 MS에 접근
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /* Logger 관련된 빈 등록  - Feign client 가 호출되면 관련 정보 확인 가능 */
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    /* @Component 로 등록했기 때문에 빈으로 등록할 필요 없음 */
//    @Bean
//    public FeignErrorDecoder getFeignErrorDecoder(){
//        return new FeignErrorDecoder();
//    }

    @Bean
    public TestDataInit testDataInit(UserService userService) {
        return new TestDataInit(userService);
    }
}
