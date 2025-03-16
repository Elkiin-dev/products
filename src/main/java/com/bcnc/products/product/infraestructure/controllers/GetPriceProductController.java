package com.bcnc.products.product.infraestructure.controllers;

import com.bcnc.products.product.application.models.GetPriceProductCommand;
import com.bcnc.products.product.application.services.GetPriceProductUseCase;
import com.bcnc.products.product.domain.models.PriceProduct;
import com.bcnc.products.product.infraestructure.exceptions.PriceProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class GetPriceProductController {

    private static final Logger logger = LoggerFactory.getLogger(GetPriceProductController.class);
    private final GetPriceProductUseCase service;

    @GetMapping("/products/prices")
    public ResponseEntity<Object> handle(
            @RequestParam(name = "productId", required = true) final Integer productId,
            @RequestParam(name = "brandId", required = true) final Integer brandId,
            @RequestParam(name = "date", required = true) final  LocalDateTime date) {

        logger.info("Solicitud recibida -> productId: {}, brandId: {}, date: {}", productId, brandId, date);

        GetPriceProductCommand command = new GetPriceProductCommand(productId, brandId, date);

        PriceProduct priceProduct = Optional.ofNullable(service.execute(command)).
                                    orElseThrow(() -> {
                                        logger.warn("Precio no encontrado para -> productId: {}, brandId: {}, date: {}",
                                                productId, brandId, date);
                                        return new PriceProductNotFoundException(command.getProductId());
                                    });

        logger.info("Precio encontrado -> {}", priceProduct);
        return ResponseEntity.ok(priceProduct);
    }
}
