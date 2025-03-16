package com.bcnc.products.product.infraestructure.repositories;

import com.bcnc.products.product.domain.models.PriceProduct;
import com.bcnc.products.product.domain.repositories.PriceProductRepository;
import com.bcnc.products.product.infraestructure.mappers.PriceProductRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class H2PriceProductRepository implements PriceProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final PriceProductRowMapper mapper;

    @Override
    public PriceProduct findBy( Integer productId, Integer brandId, LocalDateTime date) {
        String sql = """
            SELECT brand_id, start_date, end_date, price_list, product_id, price, curr
            FROM PRICES
            WHERE product_id = :product_id\s
              AND brand_id = :brand_id\s
              AND :date BETWEEN start_date AND end_date
            ORDER BY priority DESC
            LIMIT 1
       \s""";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("product_id", productId)
                .addValue("brand_id", brandId)
                .addValue("date", date);

        List<PriceProduct> results = jdbcTemplate.query(sql, params, mapper);

        return results.isEmpty() ? null : results.get(0);
    }
}
