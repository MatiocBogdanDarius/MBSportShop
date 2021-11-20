package com.example.springbootbackend.controller;

import com.example.springbootbackend.exceptions.*;
import com.example.springbootbackend.model.Product;
import com.example.springbootbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    //get all products
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        return ResponseEntity.ok(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));

        // todo valideaza datele
        product.setBrand(product.getBrand());
        product.setCategory(product.getCategory());
        product.setSubcategory(product.getSubcategory());
        product.setColors(product.getColors());
        product.setDescription(product.getDescription());
        product.setName(product.getName());
        product.setOldPrice(productDetails.getOldPrice());
        product.setPrice(product.getPrice());
        product.setSizes(productDetails.getSizes());
        product.setStock(productDetails.getStock());
        product.setGenre(productDetails.getGenre());
        product.setImagerUrl((productDetails.getImagerUrl()));


        Product updateProduct = productRepository.save(product);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));

        productRepository.delete(product);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
