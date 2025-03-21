package com.bcnc.products.product.application.services.impl;

import com.bcnc.products.product.application.dto.PriceProductDTO;
import com.bcnc.products.product.application.models.GetPriceProductCommand;
import com.bcnc.products.product.application.services.GetPriceProductUseCase;
import com.bcnc.products.product.domain.models.PriceProduct;
import com.bcnc.products.product.domain.repositories.PriceProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPriceProductUseCaseImpl implements GetPriceProductUseCase {

    private final PriceProductRepository repository;

    @Cacheable(value = "priceProductCache", key = "#command.productId + '_' + #command.brandId + '_' + #command.date")
    public PriceProductDTO execute(final GetPriceProductCommand command) {
        PriceProduct priceProduct = repository.findBy(command.getProductId(), command.getBrandId(), command.getDate());
        return (priceProduct != null) ? toDTO(priceProduct) : null;
    }

    private PriceProductDTO toDTO(PriceProduct priceProduct) {
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

