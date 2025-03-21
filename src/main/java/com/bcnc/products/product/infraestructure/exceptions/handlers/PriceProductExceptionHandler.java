package com.bcnc.products.product.infraestructure.exceptions.handlers;

import com.bcnc.products.product.infraestructure.exceptions.PriceProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class PriceProductExceptionHandler {
    private static final String BAD_REQUEST_MESSAGE = "Parámetros inválidos o no existentes: ";
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Ocurrió un error inesperado: ";

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleBadRequest(MethodArgumentTypeMismatchException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE + ex.getName());
    }

    @ExceptionHandler(PriceProductNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(PriceProductNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralError(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE + ex.getMessage());
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", message
        );
        return ResponseEntity.status(status).body(body);
    }
}
