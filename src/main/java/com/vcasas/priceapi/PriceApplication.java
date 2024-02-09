package com.vcasas.priceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.vcasas.priceapi.infraestructure" })
public class PriceApplication {
    public static void main(String[] args) {        
        SpringApplication.run(PriceApplication.class, args);
    }
}
