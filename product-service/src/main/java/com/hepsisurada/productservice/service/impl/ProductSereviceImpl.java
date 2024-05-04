package com.hepsisurada.productservice.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hepsisurada.productservice.dto.ProductRequest;
import com.hepsisurada.productservice.dto.ProductResponse;
import com.hepsisurada.productservice.model.Product;
import com.hepsisurada.productservice.repository.ProductRepository;
import com.hepsisurada.productservice.service.ProductService;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductSereviceImpl implements ProductService {

    
    private final ProductRepository productRepository;
    
    @Autowired
    public ProductSereviceImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    

    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product =Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .build();
                productRepository.save(product);
                log.info("Product {} is saved",product.getId());
    }



    @Override
    public List<ProductResponse> getAllProducts() {
       List<Product>  products=productRepository.findAll();

       return  products.stream().map(this::mapToProductResponse).toList();
    }
    
    private ProductResponse mapToProductResponse(Product product ){
        return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .build();
    }
}
