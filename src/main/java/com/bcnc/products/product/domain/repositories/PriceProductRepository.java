package com.bcnc.products.product.domain.repositories;

import com.bcnc.products.product.domain.models.PriceProduct;

import java.time.LocalDateTime;

public interface PriceProductRepository {

    PriceProduct findBy(
            final Integer productId,
            final Integer brandId,
            final LocalDateTime date
    );

}
