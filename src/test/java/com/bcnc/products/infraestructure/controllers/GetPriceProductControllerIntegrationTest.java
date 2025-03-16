package com.bcnc.products.infraestructure.controllers;

import com.bcnc.products.application.models.GetPriceProductCommandMother;
import com.bcnc.products.product.application.models.GetPriceProductCommand;
import com.bcnc.products.product.infraestructure.repositories.H2PriceProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetPriceProductControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private H2PriceProductRepository repository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetPriceProductRequest1() throws Exception {
        GetPriceProductCommand command = GetPriceProductCommandMother.defaultCommand();
        mockMvc.perform(get("/products/prices")
                        .param("productId", String.valueOf(command.getProductId()))
                        .param("brandId", String.valueOf(command.getBrandId()))
                        .param("date", String.valueOf(command.getDate())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void testGetPriceProductRequest2() throws Exception {
        GetPriceProductCommand command = GetPriceProductCommandMother.highPriorityCommand();

        mockMvc.perform(get("/products/prices")
                        .param("productId", String.valueOf(command.getProductId()))
                        .param("brandId", String.valueOf(command.getBrandId()))
                        .param("date", String.valueOf(command.getDate())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    public void testGetPriceProductRequest3() throws Exception {
        GetPriceProductCommand command = GetPriceProductCommandMother.ControllerRequest3Command();

        mockMvc.perform(get("/products/prices")
                        .param("productId", String.valueOf(command.getProductId()))
                        .param("brandId", String.valueOf(command.getBrandId()))
                        .param("date", String.valueOf(command.getDate())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void testGetPriceProductRequest4() throws Exception {
        GetPriceProductCommand command = GetPriceProductCommandMother.ControllerRequest4Command();

        mockMvc.perform(get("/products/prices")
                        .param("productId", String.valueOf(command.getProductId()))
                        .param("brandId", String.valueOf(command.getBrandId()))
                        .param("date", String.valueOf(command.getDate())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    public void testGetPriceProductRequest5() throws Exception {
        GetPriceProductCommand command = GetPriceProductCommandMother.ControllerRequest5Command();

        mockMvc.perform(get("/products/prices")
                        .param("productId", String.valueOf(command.getProductId()))
                        .param("brandId", String.valueOf(command.getBrandId()))
                        .param("date", String.valueOf(command.getDate())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(38.95));
    }

    @Test
    public void testPriceNotFound() throws Exception {
        GetPriceProductCommand command = GetPriceProductCommandMother.notFoundCommand();

        mockMvc.perform(get("/products/prices")
                        .param("productId", String.valueOf(command.getProductId()))
                        .param("brandId", String.valueOf(command.getBrandId()))
                        .param("date", String.valueOf(command.getDate())))
                .andExpect(status().isNotFound());
    }
}
