package com.bcnc.products.product.infraestructure.repositories;

import com.bcnc.products.product.domain.models.PriceProduct;
import com.bcnc.products.product.domain.repositories.PriceProductRepository;
import com.bcnc.products.product.infraestructure.persistence.PriceProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
@RequiredArgsConstructor
public class H2PriceProductRepository implements PriceProductRepository {

    private final JpaPriceProductRepository jpaRepository;

    @Override
    public PriceProduct findBy(Integer productId, Integer brandId, LocalDateTime date) {
        return jpaRepository.findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        productId, brandId, date, date)
                .map(this::toDomain)
                .orElse(null);
    }

    private PriceProduct toDomain(PriceProductEntity entity) {
        return new PriceProduct(
                entity.getProductId(),
                entity.getBrandId(),
                entity.getPriceList(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPrice(),
                entity.getCurrency()
        );
    }
}
