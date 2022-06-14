package com.example.olderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OlderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OlderServiceApplication.class, args);
    }

}
