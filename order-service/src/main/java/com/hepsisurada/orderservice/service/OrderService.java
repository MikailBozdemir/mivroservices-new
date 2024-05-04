package com.hepsisurada.orderservice.service;

import org.springframework.stereotype.Component;

import com.hepsisurada.orderservice.dto.OrderRequest;
@Component
public interface OrderService {
    
    public String placeOrder(OrderRequest orderRequest);
}
