package com.furnistore.furnistore.controller;

import com.furnistore.furnistore.model.FurnitureProduct;
import com.furnistore.furnistore.model.Product;
import com.furnistore.furnistore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<FurnitureProduct> addProduct(@RequestBody FurnitureProduct product) {
        FurnitureProduct saved = (FurnitureProduct) productService.addProduct(product);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)          // Si existe, devuelve 200 OK con el producto
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si no, 404
    }
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String filtro) {
        List<Product> result = productService.findByNameOrCategory(filtro);
        return ResponseEntity.ok(result);
    }
}
