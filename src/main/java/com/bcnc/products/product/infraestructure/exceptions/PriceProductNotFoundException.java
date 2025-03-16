package com.bcnc.products.product.infraestructure.exceptions;

public class PriceProductNotFoundException extends RuntimeException {
    public PriceProductNotFoundException(Integer productId) {
        super("No se encontró el precio del producto con ID: " + productId);
    }
}
