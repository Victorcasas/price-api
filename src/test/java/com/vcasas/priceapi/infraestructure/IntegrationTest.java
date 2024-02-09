package com.vcasas.priceapi.infraestructure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.vcasas.priceapi.domain.entities.Price;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private ResponseEntity<PriceView> response;
    private MonetaryAmount amount;
    private PriceView expected;

    LocalDateTime convertDateString(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Test
    void getPrice() throws Exception {
        //1
        amount = Monetary.getDefaultAmountFactory()
                .setCurrency("EUR")
                .setNumber(35.50)
                .create();
        response = restTemplate.getForEntity("/price-api/1/35455?date=2020-06-14T10:00:00", PriceView.class);
        expected = new PriceView(new Price(1L, 35455L, convertDateString("2020-06-14T00:00:00"),
                convertDateString("2020-12-31T23:59:59"), 1, 0, amount));
        assertEquals(expected, response.getBody());

        //2
        amount = Monetary.getDefaultAmountFactory()
                .setCurrency("EUR")
                .setNumber(25.45)
                .create();
        response = restTemplate.getForEntity("/price-api/1/35455?date=2020-06-14T16:00:00", PriceView.class);
        expected = new PriceView(new Price(1L, 35455L, convertDateString("2020-06-14T15:00:00"),
                convertDateString("2020-06-14T18:30:00"), 2, 1, amount));
        assertEquals(expected, response.getBody());

        //3
        amount = Monetary.getDefaultAmountFactory()
                .setCurrency("EUR")
                .setNumber(35.50)
                .create();
        response = restTemplate.getForEntity("/price-api/1/35455?date=2020-06-14T21:00:00", PriceView.class);
        expected = new PriceView(new Price(1L, 35455L, convertDateString("2020-06-14T00:00:00"),
                convertDateString("2020-12-31T23:59:59"), 1, 0, amount));
        assertEquals(expected, response.getBody());

        //4
        amount = Monetary.getDefaultAmountFactory()
                .setCurrency("EUR")
                .setNumber(30.50)
                .create();
        response = restTemplate.getForEntity("/price-api/1/35455?date=2020-06-15T10:00:00", PriceView.class);
        expected = new PriceView(new Price(1L, 35455L, convertDateString("2020-06-15T00:00:00"),
                convertDateString("2020-06-15T11:00:00"), 3, 1, amount));
        assertEquals(expected, response.getBody());

        //5
        amount = Monetary.getDefaultAmountFactory()
                .setCurrency("EUR")
                .setNumber(38.95)
                .create();
        response = restTemplate.getForEntity("/price-api/1/35455?date=2020-06-16T21:00:00", PriceView.class);
        expected = new PriceView(new Price(1L, 35455L, convertDateString("2020-06-15T16:00:00"),
                convertDateString("2020-12-31T23:59:59"), 4, 1, amount));
        assertEquals(expected, response.getBody());
    }
}
