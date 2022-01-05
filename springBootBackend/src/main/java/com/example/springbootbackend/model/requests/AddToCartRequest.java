package com.example.springbootbackend.model.requests;

import com.example.springbootbackend.model.ShoppingCartProduct;
import com.example.springbootbackend.model.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AddToCartRequest {
    private User user;
    private ShoppingCartProduct shoppingCartProduct;
}
