package com.vcasas.priceapi.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.springframework.boot.test.context.SpringBootTest;

import com.vcasas.priceapi.domain.IPriceRepository;
import com.vcasas.priceapi.domain.entities.Price;

@SpringBootTest
class PriceUseCaseTest {

    @Mock
    IPriceRepository priceRepository;
    Price mockPrice;
    Price mockPrice2;

    LocalDateTime convertDateString(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @BeforeEach
    public void setup() {

        MonetaryAmount amount = Monetary.getDefaultAmountFactory()
                .setCurrency("EUR")
                .setNumber(35.50)
                .create();                
        mockPrice = new Price(1L, 35455L, convertDateString("2020-06-14T00:00:00"),
                convertDateString("2020-12-31T23:59:59"), 1, 0, amount);
        mockPrice2 = new Price(1L, 35455L, convertDateString("2020-06-14T00:00:00"),
                convertDateString("2020-12-31T23:59:59"), 1, 1, amount);
        // mockPriceList for mocked respository, with two prices
        List<Price> mockPriceList = new ArrayList<>();
        mockPriceList.add(mockPrice);
        mockPriceList.add(mockPrice2);                
        doReturn(mockPriceList).when(priceRepository).findByBrandAndProduct(1L, 35455L);
    }

    @Test
    void getPrice() throws Exception {
        // priceUseCase must get the priority price in a date
        PriceUseCase priceUseCase = new PriceUseCase(priceRepository);
        Price price = priceUseCase.getPrice(1L, 35455L, convertDateString("2020-06-14T10:00:00"));
        assertSame(mockPrice2, price);
    }
}
