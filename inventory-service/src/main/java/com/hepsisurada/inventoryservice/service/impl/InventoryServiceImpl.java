package com.hepsisurada.inventoryservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hepsisurada.inventoryservice.dto.InventoryResponse;
import com.hepsisurada.inventoryservice.repository.InventoryRepository;
import com.hepsisurada.inventoryservice.service.InventoryService;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryServiceImpl  implements InventoryService{


    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository){
        this.inventoryRepository=inventoryRepository;
    }
    @Override
    @Transactional(readOnly = true)
    @SneakyThrows//exceptionu görmezden gel
    public List<InventoryResponse> isInStock(List<String> skuCode)  {

        log.info("wait started");
            Thread.sleep(100);
         log.info("wait ended");
       return  inventoryRepository.findBySkuCodeIn(skuCode)
       .stream()
       .map(inventory->
           InventoryResponse.builder()
        .skuCode(inventory.getSkuCode())
        .isInStock(inventory.getQuantity()>0)
        .build()
        ).toList();
    }
    
}
