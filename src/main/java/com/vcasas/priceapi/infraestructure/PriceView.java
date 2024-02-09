package com.vcasas.priceapi.infraestructure;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import com.vcasas.priceapi.domain.entities.Price;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PriceView {

    private final Long brandId;
    private final Long productId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Integer priceList;
    private final BigDecimal amount;
    private final String currency;

    public PriceView(Price price) {
        this.brandId = price.getBrandId();
        this.productId = price.getProductId();
        this.startDate = price.getStartDate();
        this.endDate = price.getEndDate();
        this.priceList = price.getPriceList();
        this.amount = new BigDecimal(new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US))
                .format(price.getPrice().getNumber().numberValueExact(BigDecimal.class)));

        this.currency = price.getPrice().getCurrency().getCurrencyCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PriceView that = (PriceView) o;
        return brandId.equals(that.brandId)
                && productId.equals(that.productId)
                && startDate.equals(that.startDate)
                && endDate.equals(that.endDate)
                && priceList.equals(that.priceList)
                && amount.equals(that.amount)
                && currency.equals(that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, productId, startDate, endDate, priceList, amount, currency);
    }
}
