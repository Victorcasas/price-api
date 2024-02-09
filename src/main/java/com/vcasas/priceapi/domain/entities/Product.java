package com.vcasas.priceapi.domain.entities;

import java.util.Objects;

public class Product {

    private final Long productId;

    public Product(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
