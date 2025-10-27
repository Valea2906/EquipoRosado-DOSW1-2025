package com.furnistore.furnistore.service;

import com.furnistore.furnistore.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    // Crear producto
    public Product addProduct(Product product) {
        if (product.getId() == null) {
            product.setId(nextId++);
        }
        products.add(product);
        return product;
    }

    // Listar todos los productos
    public List<Product> getAllProducts() {
        return products;
    }

    // Buscar por ID
    public Product findById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Buscar por nombre o categoría (para GET /muebles?filtro=)
    public List<Product> findByNameOrCategory(String filtro) {
        String lower = filtro.toLowerCase();
        return products.stream()
                .filter(p -> (p.getName() != null && p.getName().toLowerCase().contains(lower)) ||
                        (p.getCategory() != null && p.getCategory().toLowerCase().contains(lower)))
                .collect(Collectors.toList());
    }

    // Eliminar producto por ID
    public boolean deleteProduct(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }
}
