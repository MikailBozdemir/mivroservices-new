package com.hepsisurada.productservice.model;

import java.math.BigDecimal;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table( name="product")
@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_id_seq")
    private Long id ;
    private String name;
    private String description;
    private BigDecimal price;
}
