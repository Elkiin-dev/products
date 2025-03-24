package com.bcnc.products.product.application.services;

import com.bcnc.products.product.application.models.GetPriceProductCommand;
import com.bcnc.products.product.domain.models.PriceProduct;

public interface GetPriceProductUseCase {
    PriceProduct execute(final GetPriceProductCommand command);
}
