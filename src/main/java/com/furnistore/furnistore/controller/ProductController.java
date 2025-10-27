package com.furnistore.furnistore.controller;

import com.furnistore.furnistore.model.Product;
import com.furnistore.furnistore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    // POST /muebles → agregar nuevo producto
    @PostMapping("/muebles")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product saved = productService.addProduct(product);
        return ResponseEntity.ok(saved);
    }

    // GET /muebles → consultar catálogo con filtro opcional
    @GetMapping("/muebles")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) String filtro) {
        if (filtro == null || filtro.isEmpty()) {
            return ResponseEntity.ok(productService.getAllProducts());
        }
        return ResponseEntity.ok(productService.findByNameOrCategory(filtro));
    }

    // GET /muebles/{id} → obtener producto por id
    @GetMapping("/muebles/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product found = productService.findById(id);
        if (found == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(found);
    }
}
