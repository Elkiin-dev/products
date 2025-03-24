package com.bcnc.products.application.services;

import com.bcnc.products.application.models.GetPriceProductCommandMother;
import com.bcnc.products.product.application.models.GetPriceProductCommand;
import com.bcnc.products.product.application.services.impl.GetPriceProductUseCaseImpl;
import com.bcnc.products.product.domain.models.PriceProduct;
import com.bcnc.products.product.domain.repositories.PriceProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Service
class GetPriceProductUseCaseTest {


    @Mock
    private PriceProductRepository priceRepository;

    @InjectMocks
    private GetPriceProductUseCaseImpl priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPriceForDefaultCase() {
        GetPriceProductCommand command = GetPriceProductCommandMother.defaultCommand();
        when(priceRepository.findBy(command.getProductId(), command.getBrandId(), command.getDate()))
                .thenReturn(GetPriceProductCommandMother.defaultPrice());

        PriceProduct result = priceService.execute(command);

        assertNotNull(result);
        assertEquals(new BigDecimal("35.50"), result.getPrice());
        assertEquals("EUR", result.getCurr());
    }

    @Test
    void testPriceWithHighPriority() {
        GetPriceProductCommand command = GetPriceProductCommandMother.highPriorityCommand();
        when(priceRepository.findBy(command.getProductId(), command.getBrandId(), command.getDate()))
                .thenReturn(GetPriceProductCommandMother.highPriorityPrice());

        PriceProduct result = priceService.execute(command);

        assertNotNull(result);
        assertEquals(new BigDecimal("25.45"), result.getPrice());
        assertEquals("EUR", result.getCurr());
    }

    @Test
    void testPriceForAnotherProduct() {
        GetPriceProductCommand command = GetPriceProductCommandMother.anotherProductCommand();
        when(priceRepository.findBy(command.getProductId(), command.getBrandId(), command.getDate()))
                .thenReturn(GetPriceProductCommandMother.anotherProductPrice());

        PriceProduct result = priceService.execute(command);

        assertNotNull(result);
        assertEquals(new BigDecimal("50.00"), result.getPrice());
        assertEquals("USD", result.getCurr());
    }

    @Test
    void testPriceNotFound() {
        GetPriceProductCommand command = GetPriceProductCommandMother.notFoundCommand();
        when(priceRepository.findBy(command.getProductId(), command.getBrandId(), command.getDate()))
                .thenReturn(null);

        PriceProduct result = priceService.execute(command);

        assertNull(result);
    }
}

