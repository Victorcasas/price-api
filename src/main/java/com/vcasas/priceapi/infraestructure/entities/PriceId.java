package com.vcasas.priceapi.infraestructure.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


import lombok.Getter;

@Getter
public class PriceId implements Serializable {
    private Brand brandId;
    private Product productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PriceId that = (PriceId) o;
        return brandId.equals(that.brandId)
                && productId.equals(that.productId)
                && startDate.equals(that.startDate)
                && endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, productId, startDate, endDate);
    }
}
