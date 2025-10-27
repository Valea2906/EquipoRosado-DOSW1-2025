package com.furnistore.furnistore.service;

import com.furnistore.furnistore.model.Product;
import com.furnistore.furnistore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public List<Product> findByNameOrCategory(String filtro) {
        // Puedes implementar lógica personalizada en el repository si quieres
        return repository.findAll().stream()
                .filter(p -> (p.getName() != null && p.getName().toLowerCase().contains(filtro.toLowerCase())) ||
                        (p.getCategory() != null && p.getCategory().toLowerCase().contains(filtro.toLowerCase())))
                .toList();
    }

    public boolean deleteProduct(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
