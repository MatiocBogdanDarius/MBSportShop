package com.example.springbootbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Data

@Entity
@Table(name = "_order")
public class Order {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private long userId;
    private long billingDataId;
    private long deliveryAddressId;
    private String paymentMethod;
    private long creditCardId;


    public Order(OrderBuilder orderBuilder) {
        this.userId = orderBuilder.userId;
        this.billingDataId = orderBuilder.billingDataId;
        this.deliveryAddressId = orderBuilder.deliveryAddressId;
        this.paymentMethod = orderBuilder.paymentMethod;
        this.creditCardId = orderBuilder.creditCardId;
    }

    public Order() {

    }

    public static class OrderBuilder {
        private long userId;
        private long billingDataId;
        private long deliveryAddressId;
        private String paymentMethod;
        private long creditCardId;

        public OrderBuilder() {
            this.userId = 0;
            this.billingDataId = 0;
            this.deliveryAddressId = 0;
            this.paymentMethod = "Ramburs la curier";
            this.creditCardId = 0;
        }

        public OrderBuilder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public OrderBuilder billingDataId(long billingDataId) {
            this.billingDataId = billingDataId;
            return this;
        }

        public OrderBuilder deliveryAddressId(long deliveryAddressId) {
            this.deliveryAddressId = deliveryAddressId;
            return this;
        }

        public OrderBuilder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public OrderBuilder creditCardId(long creditCardId) {
            this.creditCardId = creditCardId;
            return this;
        }

        public Order build(){
            return new Order(this);
        }
    }
}

