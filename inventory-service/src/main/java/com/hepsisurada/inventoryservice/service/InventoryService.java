package com.hepsisurada.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hepsisurada.inventoryservice.dto.InventoryResponse;

@Component
public interface InventoryService {

    public List<InventoryResponse> isInStock(List<String> skuCode);
}
