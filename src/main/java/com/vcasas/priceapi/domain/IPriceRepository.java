package com.vcasas.priceapi.domain;

import java.util.List;

import com.vcasas.priceapi.domain.entities.Price;

public interface IPriceRepository {
    public List<Price> findByBrandAndProduct(Long brandId, Long productId);
}
