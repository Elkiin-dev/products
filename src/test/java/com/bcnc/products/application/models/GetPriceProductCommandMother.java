package com.bcnc.products.application.models;

import com.bcnc.products.product.application.models.GetPriceProductCommand;
import com.bcnc.products.product.domain.models.PriceProduct;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GetPriceProductCommandMother {
    public static PriceProduct createPriceProduct( int productId, int brandId, int priceList, LocalDateTime startDate, LocalDateTime endDate,
                                                   BigDecimal price, String currency) {
        return new PriceProduct(productId, brandId, priceList, startDate, endDate,  price, currency);
    }

    public static PriceProduct defaultPrice() {
        return createPriceProduct(1, 35455,1,
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                 new BigDecimal("35.50"), "EUR");
    }

    public static PriceProduct highPriorityPrice() {
        return createPriceProduct(1,35455,2,
                LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                LocalDateTime.of(2020, 6, 14, 18, 30, 0),
                new BigDecimal("25.45"), "EUR");
    }

    public static PriceProduct anotherProductPrice() {
        return createPriceProduct(2,35455,5,
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                 new BigDecimal("50.00"), "USD");
    }

    public static GetPriceProductCommand createCommand(Integer productId, Integer brandId, LocalDateTime date) {
        return new GetPriceProductCommand(productId, brandId, date);
    }

    public static GetPriceProductCommand defaultCommand() {
        return createCommand(
                35455,
                1,
                LocalDateTime.of(2020, 6, 14, 10, 0)
        );
    }

    public static GetPriceProductCommand highPriorityCommand() {
        return createCommand(
                35455,
                1,
                LocalDateTime.of(2020, 6, 14, 16, 0)
        );
    }

    public static GetPriceProductCommand ControllerRequest3Command() {
        return createCommand(
                35455,
                1,
                LocalDateTime.of(2020, 6, 14, 21, 0)
        );
    }

    public static GetPriceProductCommand ControllerRequest4Command() {
        return createCommand(
                35455,
                1,
                LocalDateTime.of(2020, 6, 15, 10, 0)
        );
    }

    public static GetPriceProductCommand ControllerRequest5Command() {
        return createCommand(
                35455,
                1,
                LocalDateTime.of(2020, 6, 16, 21, 0)
        );
    }

    public static GetPriceProductCommand anotherProductCommand() {
        return createCommand(
                35455,
                1,
                LocalDateTime.of(2020, 6, 14, 10, 0)
        );
    }

    public static GetPriceProductCommand notFoundCommand() {
        return createCommand(
                99999,
                1,
                LocalDateTime.of(2020, 6, 14, 10, 0)
        );
    }

    public static GetPriceProductCommand ControllerRequest1params() {
        return createCommand(
                99999,
                1,
                LocalDateTime.of(2020, 6, 14, 10, 0)
        );
    }

}
