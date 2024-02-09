package com.vcasas.priceapi.application;

import java.time.LocalDateTime;

import com.vcasas.priceapi.domain.entities.Price;

public interface IPriceUseCase {

    public Price getPrice(Long brandId, Long productId, LocalDateTime date);
}
