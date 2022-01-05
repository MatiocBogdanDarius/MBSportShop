package com.example.springbootbackend.model;

import java.util.List;

public class Bill {
    private final long id;
    private final User user;
    private final BillData billingData;
    private final String paymentMethod;
    private final BankCard creditCard;
    private final List<ShoppingCartProduct> products;

    public Bill(BillBuilder billBuilder) {
        this.id = billBuilder.id;
        this.user = billBuilder.user;
        this.billingData = billBuilder.billingData;
        this.paymentMethod = billBuilder.paymentMethod;
        this.creditCard = billBuilder.creditCard;
        this.products = billBuilder.products;
    }

    public long getId() {
        return id;
    }

    public User getUserId() {
        return user;
    }

    public BillData getBillingData() {
        return billingData;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public BankCard getCreditCard() {
        return creditCard;
    }

    public List<ShoppingCartProduct> getProducts() {
        return products;
    }

    public static class BillBuilder {
        private long id;
        private User user;
        private BillData billingData;
        private String paymentMethod;
        private BankCard creditCard;
        private  List<ShoppingCartProduct> products;

        public BillBuilder id(long id) {
            this.id = id;
            return this;
        }

        public BillBuilder user(User user) {
            this.user = user;
            return this;
        }

        public BillBuilder billingData(BillData billingData) {
            this.billingData = billingData;
            return this;
        }

        public BillBuilder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public BillBuilder creditCard(BankCard creditCard) {
            this.creditCard = creditCard;
            return this;
        }

        public BillBuilder products(List<ShoppingCartProduct> products) {
            this.products = products;
            return this;
        }

        public Bill build(){
            return new Bill(  this);
        }
    }


}
