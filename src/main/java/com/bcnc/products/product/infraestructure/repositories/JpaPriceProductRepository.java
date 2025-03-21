package com.bcnc.products.product.infraestructure.repositories;

import com.bcnc.products.product.infraestructure.persistence.PriceProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface JpaPriceProductRepository extends JpaRepository<PriceProductEntity, Integer> {
    Optional<PriceProductEntity> findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Integer productId, Integer brandId, LocalDateTime startDate, LocalDateTime endDate);
}
