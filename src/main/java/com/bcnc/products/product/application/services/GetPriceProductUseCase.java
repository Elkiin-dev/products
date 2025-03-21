package com.bcnc.products.product.application.services;

import com.bcnc.products.product.application.dto.PriceProductDTO;
import com.bcnc.products.product.application.models.GetPriceProductCommand;

public interface GetPriceProductUseCase {
    PriceProductDTO execute(final GetPriceProductCommand command);
}
