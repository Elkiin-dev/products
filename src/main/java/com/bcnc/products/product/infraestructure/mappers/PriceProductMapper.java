package com.bcnc.products.product.infraestructure.mappers;

import com.bcnc.products.product.application.dto.PriceProductDTO;
import com.bcnc.products.product.domain.models.PriceProduct;
import org.springframework.stereotype.Component;

@Component
public class PriceProductMapper {
    public PriceProductDTO map(PriceProduct priceProduct) {
        return new PriceProductDTO(
                priceProduct.getProductId(),
                priceProduct.getBrandId(),
                priceProduct.getPriceList(),
                priceProduct.getStartDate(),
                priceProduct.getEndDate(),
                priceProduct.getPrice(),
                priceProduct.getCurr()
        );
    }
}
