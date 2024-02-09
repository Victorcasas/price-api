package com.vcasas.priceapi.application;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import com.vcasas.priceapi.domain.IPriceRepository;
import com.vcasas.priceapi.domain.entities.Price;

public class PriceUseCase implements  IPriceUseCase {

    private final IPriceRepository repository;

    public PriceUseCase(IPriceRepository repository) {
        this.repository = repository;
    }

    public Price getPrice(Long brandId, Long productId, LocalDateTime date) {
        List<Price> priceList = repository.findByBrandAndProduct(brandId, productId);
        return priceList
                .stream()
                .filter(price -> price.getStartDate().isBefore(date) && price.getEndDate().isAfter(date))
                .max(Comparator.comparing(Price::getPriority)).orElse(null);
    }
}
