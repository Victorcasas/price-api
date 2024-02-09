package com.vcasas.priceapi.domain.entities;

import java.time.LocalDateTime;

import javax.money.MonetaryAmount;

public class Price {

    private final Long brandId;
    private final Long productId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Integer priceList;
    private final Integer priority;
    private final MonetaryAmount price;

    public Price(Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate,
            Integer priceList, Integer priority, MonetaryAmount price) {
        this.brandId = brandId;
        this.productId = productId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.priority = priority;
        this.price = price;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Long getProductId() {
        return productId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public Integer getPriority() {
        return priority;
    }

    public MonetaryAmount getPrice() {
        return price;
    }
}
