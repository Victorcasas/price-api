package com.vcasas.priceapi.infraestructure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.vcasas.priceapi.domain.entities.Price;

@DataJpaTest
class PriceRepositoryTest {

    @Autowired
    private PriceJpaRepository priceJpaRepository;

    private LocalDateTime convertDateString(String date) {

        return LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    MonetaryAmount amount = Monetary.getDefaultAmountFactory()
            .setCurrency("EUR")
            .setNumber(38.95)
            .create();

    Price samplePrice = new Price(1L, 35455L, convertDateString("2020-06-15T16:00:00"),
            convertDateString("2020-06-15T16:00:00"), 4, 1, amount);

    @Test
    void getPrice() {

        // pricesList from repository includes samplePrice
        List<Price> pricesList = priceJpaRepository.findByBrandAndProduct(1L, 35455L);
        assertEquals(true, pricesList.stream().anyMatch(p -> p.getPrice().equals(samplePrice.getPrice())));
    }
}
