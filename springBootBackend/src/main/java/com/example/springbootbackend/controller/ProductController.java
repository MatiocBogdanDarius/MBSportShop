package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Product;
import com.example.springbootbackend.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    //get all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{category}/{subcategory}")
    public List<Product> getProductsByCategoryAndSubcategory(@PathVariable String category, @PathVariable String subcategory) {
        return productService.getProductsByCategoryAndSubcategory(category, subcategory);
    }

    @GetMapping("/products/sport/{sportCategory}")
    public List<Product> getProductsBySportCategory(@PathVariable String sportCategory) {
        return productService.getProductsBySportCategory(sportCategory);
    }

    @GetMapping("/products/brand/{brand}")
    public List<Product> getProductsBySportBrand(@PathVariable String brand) {
       return productService.getProductsBySportBrand(brand);
    }

    @GetMapping("/products-search/{searchContent}")
    public List<Product> getProductsByPartOfName(@PathVariable String searchContent) {
        return productService.getProductsByPartOfName(searchContent);
    }

    @PostMapping("/products/")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
