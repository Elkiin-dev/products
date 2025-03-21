package com.bcnc.products.product.infraestructure.exceptions;

public class PriceProductNotFoundException extends RuntimeException {
    private static final String PRICE_PRODUCT_NOT_FOUND_MESSAGE = "No se encontró el precio del producto con ID: ";

    public PriceProductNotFoundException(Integer productId) {
        super(PRICE_PRODUCT_NOT_FOUND_MESSAGE + productId);
    }
}
