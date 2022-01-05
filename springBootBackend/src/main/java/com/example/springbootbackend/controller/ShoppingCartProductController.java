package com.example.springbootbackend.controller;

import com.example.springbootbackend.exceptions.ResourceNotFoundException;
import com.example.springbootbackend.model.Product;
import com.example.springbootbackend.model.ShoppingCartProduct;
import com.example.springbootbackend.services.ShoppingCartProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/shoppingCartProduct")
@AllArgsConstructor
public class ShoppingCartProductController {
    private ShoppingCartProductService shoppingCartProductService;

    @PostMapping()
    public ShoppingCartProduct add(@RequestBody ShoppingCartProduct shoppingCartProduct) {
        return shoppingCartProductService.save(shoppingCartProduct);
    }

    @PutMapping("/product")
    public ResponseEntity<ShoppingCartProduct> updateProduct
            (@RequestBody ShoppingCartProduct shoppingCartProductDetails) {
        return shoppingCartProductService.update(shoppingCartProductDetails);
    }

    @PutMapping("/products")
    public ResponseEntity<ShoppingCartProduct> updateOrAddProduct(@RequestBody ShoppingCartProduct shoppingCartProductDetails) {
        return shoppingCartProductService.updateOrAddProduct(shoppingCartProductDetails);
    }

    @GetMapping("/userId={userId}")
    public List<ShoppingCartProduct> findByUserId
            (@PathVariable Long userId) {
        return shoppingCartProductService.findByUserId(userId);
    }
}
