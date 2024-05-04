package com.hepsisurada.productservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hepsisurada.productservice.dto.ProductRequest;
import com.hepsisurada.productservice.dto.ProductResponse;
import com.hepsisurada.productservice.service.ProductService;



@RestController
@RequestMapping("/api/product")

public class ProductController{

        
        private final ProductService productservice;

        @Autowired
        public  ProductController(ProductService productservice){
                this.productservice=productservice;

        }
       

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public void createProduct(@RequestBody ProductRequest productRequest ){
                productservice.createProduct(productRequest);
        }

        @GetMapping
        @ResponseStatus(HttpStatus.OK)
        public List<ProductResponse> getAllProducts(){
              return   productservice.getAllProducts();
        }


}