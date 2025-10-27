package com.furnistore.furnistore.controller;

import com.furnistore.furnistore.model.FurnitureProduct;
import com.furnistore.furnistore.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService service;

    @Test
    void testAddProduct() throws Exception {
        FurnitureProduct p = new FurnitureProduct(null, "Silla Moderna", "Muebles", "Moderno", 120000.0, 5);
        when(service.addProduct(any(FurnitureProduct.class))).thenAnswer(invocation -> {
            FurnitureProduct prod = invocation.getArgument(0);
            prod.setId(1L);
            return prod;
        });

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Silla Moderna"));
    }

    @Test
    void testGetAllProducts() throws Exception {
        FurnitureProduct p = new FurnitureProduct(1L, "Lámpara de pie", "Iluminación", "Moderno", 120000.0, 8);
        when(service.getAllProducts()).thenReturn(List.of(p));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Lámpara de pie"));
    }

    @Test
    void testGetProductByIdFound() throws Exception {
        FurnitureProduct p = new FurnitureProduct(1L, "Lámpara de pie", "Iluminación", "Moderno", 120000.0, 8);
        when(service.findById(1L)).thenReturn(Optional.of(p));

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lámpara de pie"));
    }

    @Test
    void testGetProductByIdNotFound() throws Exception {
        when(service.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/products/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSearchProducts() throws Exception {
        FurnitureProduct p = new FurnitureProduct(1L, "Silla de oficina", "Sillas", "Moderno", 80000.0, 5);
        when(service.findByNameOrCategory("silla")).thenReturn(List.of(p));

        mockMvc.perform(get("/api/products/search").param("filtro", "silla"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Silla de oficina"));
    }
}
