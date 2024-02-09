package com.vcasas.priceapi.infraestructure;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.vcasas.priceapi.application.PriceUseCase;
import com.vcasas.priceapi.domain.entities.Price;

@Service
public class PriceService extends PriceUseCase {

    public PriceService(PriceJpaRepository repository) {
        super(repository);
    }

    public PriceView getPriceView(Long brandId, Long productId, LocalDateTime date) {
        Price price = getPrice(brandId, productId, date);
        if (price == null) {
            return null;
        } else {
            return new PriceView(price);
        }
    }
}
