package com.vcasas.priceapi.infraestructure;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vcasas.priceapi.infraestructure.exception.MyResourceNotFoundException;
import com.vcasas.priceapi.infraestructure.exception.RestPreconditions;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@RestController
@RequestMapping("/price-api")
@AllArgsConstructor
@Getter
public class PriceController {

    // Eclipse IDE java plugin could mark error due to fail to pick up this bean injection...
    private final PriceService priceService;

    @GetMapping("/{brandId}/{productId}")
    @Operation(summary = "To get priority price for brand/product IDs in a date")
    public PriceView getPrice(
            @Parameter(description = "Brand ID")
            @PathVariable("brandId") Long brandId,
            @Parameter(description = "Product ID")
            @PathVariable("productId") Long productId,
            @Parameter(description = "Price date")
            @RequestParam("date") LocalDateTime date) {
        try {
            return RestPreconditions.checkFound(priceService.getPriceView(brandId, productId, date));
        } catch (MyResourceNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Price Not Found", exc);
        }
    }
}
