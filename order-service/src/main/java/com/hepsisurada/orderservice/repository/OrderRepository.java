package com.hepsisurada.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hepsisurada.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
    
}
