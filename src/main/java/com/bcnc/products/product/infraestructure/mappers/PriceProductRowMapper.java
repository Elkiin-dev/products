package com.bcnc.products.product.infraestructure.mappers;

import com.bcnc.products.product.domain.models.PriceProduct;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PriceProductRowMapper implements RowMapper<PriceProduct> {
    @Override
    public PriceProduct mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new PriceProduct(
                resultSet.getInt("product_id"),
                resultSet.getInt("brand_id"),
                resultSet.getInt("price_list"),
                resultSet.getTimestamp("start_date").toLocalDateTime(),
                resultSet.getTimestamp("end_date").toLocalDateTime(),
                resultSet.getBigDecimal("price"),
                resultSet.getString("curr")
        );
    }
}
