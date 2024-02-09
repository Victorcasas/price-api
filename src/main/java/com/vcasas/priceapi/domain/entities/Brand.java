package com.vcasas.priceapi.domain.entities;

public class Brand {

    private final Long brandId;

    public Brand(Long brandId) {
        this.brandId = brandId;
    }

    public Long getBrandId() {
        return this.brandId;
    }
}
