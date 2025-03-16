package com.bcnc.products.product.application.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GetPriceProductCommand {
    private final Integer productId;
    private final Integer brandId;
    private final LocalDateTime date;
}