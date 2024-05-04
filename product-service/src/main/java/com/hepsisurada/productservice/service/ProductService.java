package com.hepsisurada.productservice.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hepsisurada.productservice.dto.ProductRequest;
import com.hepsisurada.productservice.dto.ProductResponse;

@Component
public interface ProductService{

        public void createProduct(ProductRequest productRequest);

        List<ProductResponse> getAllProducts();

    }
