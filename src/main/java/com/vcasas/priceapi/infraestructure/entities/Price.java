package com.vcasas.priceapi.infraestructure.entities;

import java.time.LocalDateTime;

import javax.money.MonetaryAmount;

import io.hypersistence.utils.hibernate.type.money.MonetaryAmountType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import org.hibernate.annotations.CompositeType;

@Entity
@IdClass(value = PriceId.class)
@Getter
public class Price {

    @Id
    @ManyToOne
    @JoinColumn(name = "brand_id")
    //, nullable=false)
    private Brand brandId;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;
    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate;

    private Integer priceList;
    private Integer priority;

    @AttributeOverride(
            name = "amount",
            column = @Column(name = "amount")
    )
    @AttributeOverride(
            name = "currency",
            column = @Column(name = "currency")
    )
    @CompositeType(MonetaryAmountType.class)
    private MonetaryAmount price;
}
