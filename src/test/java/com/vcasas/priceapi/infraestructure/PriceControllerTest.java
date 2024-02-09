package com.vcasas.priceapi.infraestructure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.vcasas.priceapi.domain.entities.Price;

@WebMvcTest
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceController priceController;

    LocalDateTime convertDateString(String date) {

        return LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @BeforeEach
    public void setup() {

        MonetaryAmount amount = Monetary.getDefaultAmountFactory()
                .setCurrency("EUR")
                .setNumber(38.95)
                .create();
        PriceView mockPrice = new PriceView(new Price(1L, 35455L, convertDateString("2020-06-15T16:00:00"),
                convertDateString("2020-06-15T16:00:00"), 4, 1, amount));

        when(priceController.getPrice(anyLong(), anyLong(), any(LocalDateTime.class))).thenReturn(mockPrice);
    }

    // Test the correct controller urlTemplate
    @Test
    void getPrice() throws Exception {

        String urlTemplate = "/price-api/1/35455?date=2020-06-15T16:00:00";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(urlTemplate);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"brandId\":1,\"productId\":35455,\"startDate\":\"2020-06-15T16:00:00\","
                + "\"endDate\":\"2020-06-15T16:00:00\",\"priceList\":4,\"amount\":38.95,\"currency\":\"EUR\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
