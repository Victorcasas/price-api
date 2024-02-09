package com.vcasas.priceapi.infraestructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Product {    
    
    @Id
    private final Long productId;
    
    public Product(Long productId) {
        this.productId = productId;
    }

}