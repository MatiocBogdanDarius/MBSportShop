package com.example.springbootbackend.model.requests;

import com.example.springbootbackend.model.ShoppingCartProduct;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderRequest {
    private final long userId;
    private final long billingDataId;
    private final long deliveryAddressId;
    private final String paymentMethod;
    private final long creditCardId;
    private final List<ShoppingCartProduct> products;
}
