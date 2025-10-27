package com.furnistore.furnistore.service;


import com.furnistore.furnistore.model.Product;
import com.furnistore.furnistore.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductRepository repository;
    private ProductService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ProductRepository.class);
        service = new ProductService(repository);
    }

    @Test
    void addProduct_ShouldReturnSavedProduct() {
        Product p = new Product() {{}};
        p.setName("Mesa");
        p.setCategory("Muebles");
        p.setPrice(100.0);
        p.setStockQuantity(10);

        when(repository.save(p)).thenReturn(p);
        Product saved = service.addProduct(p);

        assertEquals("Mesa", saved.getName());
        verify(repository, times(1)).save(p);
    }

    @Test
    void getAllProducts_ShouldReturnList() {
        Product p = new Product() {{}};
        p.setName("Silla");
        when(repository.findAll()).thenReturn(List.of(p));

        List<Product> list = service.getAllProducts();
        assertEquals(1, list.size());
        assertEquals("Silla", list.get(0).getName());
    }

    @Test
    void findById_ShouldReturnOptional_WhenExists() {
        Product p = new Product() {{}};
        p.setId(1L);
        p.setName("Silla");
        when(repository.findById(1L)).thenReturn(Optional.of(p));

        Optional<Product> found = service.findById(1L);
        assertTrue(found.isPresent());
        assertEquals("Silla", found.get().getName());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenNotExists() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<Product> found = service.findById(99L);
        assertTrue(found.isEmpty());
    }

    @Test
    void deleteProduct_ShouldReturnTrueIfExists() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        boolean deleted = service.deleteProduct(1L);
        assertTrue(deleted);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deleteProduct_ShouldReturnFalseIfNotExists() {
        when(repository.existsById(2L)).thenReturn(false);

        boolean deleted = service.deleteProduct(2L);
        assertFalse(deleted);
        verify(repository, never()).deleteById(2L);
    }

    @Test
    void findByNameOrCategory_ShouldFilterByName() {
        Product p1 = new Product() {{}};
        p1.setName("Sofa");
        p1.setCategory("Muebles");

        Product p2 = new Product() {{}};
        p2.setName("Mesa");
        p2.setCategory("Decoración");

        when(repository.findAll()).thenReturn(List.of(p1, p2));

        List<Product> result = service.findByNameOrCategory("Sofa");
        assertEquals(1, result.size());
        assertEquals("Sofa", result.get(0).getName());
    }

    @Test
    void findByNameOrCategory_ShouldFilterByCategory() {
        Product p1 = new Product() {{}};
        p1.setName("Silla");
        p1.setCategory("Muebles");

        Product p2 = new Product() {{}};
        p2.setName("Florero");
        p2.setCategory("Decoración");

        when(repository.findAll()).thenReturn(List.of(p1, p2));

        List<Product> result = service.findByNameOrCategory("decoración");
        assertEquals(1, result.size());
        assertEquals("Florero", result.get(0).getName());
    }

    @Test
    void findByNameOrCategory_ShouldReturnEmpty_WhenNoMatch() {
        Product p1 = new Product() {{}};
        p1.setName("Silla");
        p1.setCategory("Muebles");

        when(repository.findAll()).thenReturn(List.of(p1));

        List<Product> result = service.findByNameOrCategory("Mesa");
        assertTrue(result.isEmpty());
    }
}
