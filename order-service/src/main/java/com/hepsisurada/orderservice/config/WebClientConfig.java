package com.hepsisurada.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced//clientside loadbalancing bbirden fazla instance var ise sırasıyla gidecek hangisinie gticeğini 
    // bilmediği için aldığımız hata ortaya çıkmicak
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }
    
}
