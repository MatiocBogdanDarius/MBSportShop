package com.example.springbootbackend.services;

import com.example.springbootbackend.exceptions.ResourceNotFoundException;
import com.example.springbootbackend.model.ShoppingCartProduct;
import com.example.springbootbackend.repository.ShoppingCartProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShoppingCartProductService {
    private ShoppingCartProductRepository shoppingCartProductRepository;

    public ShoppingCartProduct save(ShoppingCartProduct shoppingCartProduct) {
        return shoppingCartProductRepository.save(shoppingCartProduct);
    }

    public ResponseEntity<ShoppingCartProduct> update(ShoppingCartProduct shoppingCartProductDetails) {
        System.out.println(shoppingCartProductDetails);
        List<ShoppingCartProduct> shoppingCartProducts = shoppingCartProductRepository.findAll();
        if(shoppingCartProductDetails.getUserId() != 0)
            shoppingCartProducts = shoppingCartProducts.stream()
                    .filter(product -> product.getUserId() == shoppingCartProductDetails.getUserId())
                    .collect(Collectors.toList());
        Optional<ShoppingCartProduct> shoppingCartProduct = shoppingCartProducts.stream()
                .filter(product -> product.getSize().equals(shoppingCartProductDetails.getSize()) &&
                        product.getColor().equals(shoppingCartProductDetails.getColor())).findFirst();
        if(shoppingCartProduct.isPresent()){
            ShoppingCartProduct updateShoppingCartProduct = shoppingCartProduct.get();
            updateShoppingCartProduct.setQuantity(shoppingCartProductDetails.getQuantity());
            return ResponseEntity.ok(shoppingCartProductRepository.save(updateShoppingCartProduct));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ShoppingCartProduct> updateOrAddProduct(ShoppingCartProduct shoppingCartProductDetails) {
        System.out.println(shoppingCartProductDetails);
        if(shoppingCartProductDetails.getId() == 0){
            return ResponseEntity.ok(shoppingCartProductRepository.save(shoppingCartProductDetails));
        }
        else {
            return update(shoppingCartProductDetails);
        }
    }

    public List<ShoppingCartProduct> findByUserId(Long userId) {
        return shoppingCartProductRepository.findAll().stream()
                .filter(product -> product.getUserId() == userId)
                .collect(Collectors.toList());
    }

    public Optional<ShoppingCartProduct> findById(long id) {
        return shoppingCartProductRepository.findById(id);
    }
}
