package com.example.springbootbackend.services;

import com.example.springbootbackend.model.*;
import com.example.springbootbackend.model.requests.OrderRequest;
import com.example.springbootbackend.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServices {
    private OrderRepository orderRepository;
    private ShoppingCartProductRepository shoppingCartProductRepository;
    private PurchasedProductRepository purchasedProductRepository;
    private UserRepository userRepository;
    private BillDataRepository billDataRepository;
    private BankCardRepository bankCardRepository;
    private BillServices billServices;

    public Order add(OrderRequest orderRequest) {
        System.out.println(orderRequest);
//        BillServices.bill = createBill(orderRequest);
//        System.out.println(billServices.bill);

        orderRequest.getProducts()
                .forEach(shoppingCartProduct -> {
                    System.out.println(shoppingCartProduct);
                    shoppingCartProductRepository.deleteById(shoppingCartProduct.getId());
                    PurchasedProduct purchasedProduct = new PurchasedProduct(shoppingCartProduct.getProductId(),
                            shoppingCartProduct.getUserId(), shoppingCartProduct.getColor(), shoppingCartProduct.getSize(), shoppingCartProduct.getQuantity());
                    purchasedProductRepository.save(purchasedProduct);
                });

        Order.OrderBuilder orderBuilder = new Order.OrderBuilder()
                .userId(orderRequest.getUserId())
                .billingDataId(orderRequest.getBillingDataId())
                .deliveryAddressId(orderRequest.getDeliveryAddressId())
                .paymentMethod(orderRequest.getPaymentMethod());

        if(orderRequest.getPaymentMethod().equals("Plata cu cardul"))
                 orderBuilder.creditCardId(orderRequest.getCreditCardId());

        Order order = orderBuilder.build();
        return orderRepository.save(order);

    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    private Bill createBill(OrderRequest orderRequest) {
        User user = userRepository.getById(orderRequest.getUserId());
        BillData billData = billDataRepository.getById(orderRequest.getBillingDataId());
        BankCard card = null;
        if (orderRequest.getPaymentMethod().equals("Plata cu cardul")) {
            card = bankCardRepository.getById(orderRequest.getCreditCardId());
        }
        Bill.BillBuilder billBuilder = new Bill.BillBuilder()
                .user(user).billingData(billData)
                .paymentMethod(orderRequest.getPaymentMethod())
                .products(orderRequest.getProducts());
        if(orderRequest.getPaymentMethod().equals("Plata cu cardul")){
            billBuilder.creditCard(card);
        }
        Bill bill = billBuilder.build();
        return bill;
    }
}
