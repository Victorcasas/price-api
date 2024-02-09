package com.vcasas.priceapi.infraestructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vcasas.priceapi.domain.IPriceRepository;
import com.vcasas.priceapi.infraestructure.entities.Price;
import com.vcasas.priceapi.infraestructure.entities.PriceId;

@Repository
// false positive 'error message' on PriceId key could be caused
// in certain versions of the the SB-tool in VC and Eclipse ides
public interface PriceJpaRepository extends IPriceRepository, JpaRepository<Price, PriceId> {

    @Query
    ("SELECT new"
    + " com.vcasas.priceapi.domain.entities.Price(brandId.brandId, productId.productId, startDate, endDate, priceList, priority, price)"
    + " from Price where brandId.brandId=:brandId and productId.productId=:productId")
    @Override
    List<com.vcasas.priceapi.domain.entities.Price> findByBrandAndProduct(Long brandId, Long productId);
}
