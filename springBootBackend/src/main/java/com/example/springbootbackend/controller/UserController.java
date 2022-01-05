package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.ShoppingCartProduct;
import com.example.springbootbackend.model.User;
import com.example.springbootbackend.model.requests.AddToCartRequest;
import com.example.springbootbackend.services.ShoppingCartProductService;
import com.example.springbootbackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private ShoppingCartProductService shoppingCartProductService;

    @PutMapping("/updateFavoriteProducts/{id}")
    public ResponseEntity<User> updateFavoriteProducts(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.updateFavoriteProducts(id, userDetails);
    }

//    @PutMapping("/updateShoppingCart/{id}")
//    public ResponseEntity<User> updateShoppingCart(@PathVariable Long id, @RequestBody AddToCartRequest addToCartRequest) {
//        ResponseEntity<ShoppingCartProduct> shoppingCartProduct =
//                shoppingCartProductService.add(addToCartRequest.getShoppingCartProduct());
//        return userService.updateShoppingCart(id, addToCartRequest.getUser());
//    }



}
